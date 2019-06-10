package com.xander.comp.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import com.xander.comp.core.application.CompApplication;
import com.xander.comp.core.services.ICompService;
import com.xander.comp.core.services.IComp1Service;
import com.xander.comp.core.services.IComp2Service;
import com.xander.comp.core.services.SimpleComp1Service;
import com.xander.comp.core.services.SimpleComp2Service;
import com.xander.comp.core.services.SimpleServiceFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class AppTool {

  private static final String TAG = "AppTool";

  private static Application app;

  private static ArrayList<Class> compServiceClassList = new ArrayList<>();

  private static HashMap<Class, ICompService> compServiceMap = new HashMap<>();

  private static ArrayList<CompApplication> compApplicationList = new ArrayList<>();

  static {
    compServiceClassList.add(IComp1Service.class);
    compServiceClassList.add(IComp2Service.class);
    // 后续可以通过注解的方式来解决这个绑定的问题
    SimpleServiceFactory.register(IComp1Service.class, SimpleComp1Service.class);
    SimpleServiceFactory.register(IComp2Service.class, SimpleComp2Service.class);
  }

  public static void init() {
    if (!compApplicationList.isEmpty()) {
      Log.e(TAG, "init: ", new IllegalStateException("can not call init more than one time!!!"));
      return;
    }
    initServices();
    Log.d(TAG, "init: compServiceClassList:" + compServiceClassList);
    Log.d(TAG, "init: compApplicationList:" + compApplicationList);
  }

  private static void initServices() {
    for (Class<ICompService> klass : compServiceClassList) {
      ICompService compService = loadService(klass);
      if (null == compService) {
        compService = SimpleServiceFactory.createSimpleService(klass);
      }
      if (null == compService) {
        continue;
      }
      compServiceMap.put(klass, compService);
      loadCompApplication(compService);
    }
  }

  private static void loadCompApplication(ICompService services) {
    if (null != services && null != services.getApplication()) {
      compApplicationList.add(services.getApplication());
    }
  }

  private static ICompService loadService(Class<ICompService> serviceClass) {
    Log.d(TAG, "loadService: " + serviceClass);
    ICompService compService = null;
    ServiceLoader<ICompService> serviceLoader = ServiceLoader.load(serviceClass);
    serviceLoader.reload();
    Iterator<ICompService> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      compService = iterator.next();
    }
    return compService;
  }

  static {
    init();
  }

  public static Application Application() {
    return app;
  }

  public static<SERVICE extends ICompService> SERVICE getAppService(Class<SERVICE> serviceClass) {
    Log.d(TAG, "getAppService: " + serviceClass);
    return (SERVICE)compServiceMap.get(serviceClass);
  }

  public static IComp1Service getComp1Service() {
    ICompService compService = compServiceMap.get(IComp1Service.class);
    if (null != compService && compService instanceof IComp1Service) {
      return (IComp1Service) compService;
    }
    return null;
  }

  public static IComp2Service getComp2Service() {
    ICompService compService = compServiceMap.get(IComp2Service.class);
    if (null != compService && compService instanceof IComp2Service) {
      return (IComp2Service) compService;
    }
    return null;
  }

  public static void appAttachBaseApplication(Context context, Application application) {
    app = application;
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onAttachBaseApplication(context);
      }
    }
  }

  public static void appCreate() {
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onCreate();
      }
    }
  }

  public static void appTerminate() {
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onTerminate();
      }
    }
  }

  public static void appConfigurationChanged(Configuration newConfig) {
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onConfigurationChanged(newConfig);
      }
    }
  }

  public static void appLowMemory() {
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onLowMemory();
      }
    }
  }

  public static void appTrimMemory(int level) {
    for (CompApplication app : compApplicationList) {
      if (null != app) {
        app.onTrimMemory(level);
      }
    }
  }
}
