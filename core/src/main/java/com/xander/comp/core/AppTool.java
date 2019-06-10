package com.xander.comp.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import com.xander.comp.core.application.XApplication;
import com.xander.comp.core.services.IAppServices;
import com.xander.comp.core.services.IComp1Services;
import com.xander.comp.core.services.IComp2Services;
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

  private static ArrayList<Class> iAppServicesList = new ArrayList<>();

  private static HashMap<Class, IAppServices> appServicesMap = new HashMap<>();

  private static ArrayList<XApplication> xApplicationList = new ArrayList<>();

  static {
    iAppServicesList.add(IComp1Services.class);
    iAppServicesList.add(IComp2Services.class);
  }

  public static void init() {
    //initApp();
    if (!xApplicationList.isEmpty()) {
      return;
    }
    xApplicationList.clear();
    initServices();
    Log.d(TAG, String.valueOf(iAppServicesList));
    Log.d(TAG, String.valueOf(xApplicationList));
  }

  private static void initServices() {
    for (Class<IAppServices> zlass : iAppServicesList) {
      IAppServices services = loadServices(zlass);
      appServicesMap.put(zlass, services);
      initApplication(services);
    }
  }

  private static void initApplication(IAppServices services) {
    if (null != services && null != services.getApplication()) {
      xApplicationList.add(services.getApplication());
    }
  }

  private static <SERVICES> SERVICES loadServices(Class<SERVICES> servicesClass) {
    SERVICES services = null;
    ServiceLoader<SERVICES> serviceLoader = ServiceLoader.load(servicesClass);
    serviceLoader.reload();
    Iterator<SERVICES> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      services = iterator.next();
    }
    return services;
  }

  static {
    init();
  }

  public static Application getApp() {
    return app;
  }

  public static <SERVICES extends IAppServices> SERVICES getAppServices(
      Class<SERVICES> servicesClass) {
    IAppServices services = appServicesMap.get(servicesClass);
    if (null != services) {
      return (SERVICES) services;
    }
    return null;
  }

  public static IComp1Services getComp1Service() {
    IAppServices services = appServicesMap.get(IComp1Services.class);
    if (null != services) {
      return (IComp1Services) services;
    }
    return null;
  }

  public static IComp2Services getComp2Service() {
    IAppServices services = appServicesMap.get(IComp2Services.class);
    if (null != services && services instanceof IComp2Services) {
      return (IComp2Services) services;
    }
    return null;
  }

  public static void appAttachBaseApplication(Context context, Application application) {
    app = application;
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onAttachBaseApplication(context);
      }
    }
  }

  public static void appCreate() {
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onCreate();
      }
    }
  }

  public static void appTerminate() {
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onTerminate();
      }
    }
  }

  public static void appConfigurationChanged(Configuration newConfig) {
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onConfigurationChanged(newConfig);
      }
    }
  }

  public static void appLowMemory() {
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onLowMemory();
      }
    }
  }

  public static void appTrimMemory(int level) {
    for (XApplication app : xApplicationList) {
      if (null != app) {
        app.onTrimMemory(level);
      }
    }
  }
}
