package com.xander.comp.core;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.xander.comp.core.application.IApplication;
import com.xander.comp.core.application.ICopm1Application;
import com.xander.comp.core.application.IComp2Application;
import com.xander.comp.core.services.IComp1Services;
import com.xander.comp.core.services.IComp2Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class AppTool {

  private static final String TAG = "AppTool";

  private static Application app;

  public static IComp1Services module1Services;

  public static IComp2Services module2Services;

  private static ArrayList<IApplication> iApplicationList = new ArrayList<>();

  private static <SERVICES> SERVICES getService(Class<SERVICES> servicesClass) {
    SERVICES services = null;
    ServiceLoader<SERVICES> serviceLoader = ServiceLoader.load(servicesClass);
    serviceLoader.reload();
    Iterator<SERVICES> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      services = iterator.next();
    }
    return services;
  }

  public static void initApp() {
    iApplicationList.clear();
    iApplicationList.add(getService(ICopm1Application.class));
    iApplicationList.add(getService(IComp2Application.class));
  }

  public static void initServices() {
    module1Services = getService(IComp1Services.class);
    module2Services = getService(IComp2Services.class);
  }

  public static void init() {
    initApp();
    initServices();
    Log.d(TAG, String.valueOf(iApplicationList));
    Log.d(TAG, String.valueOf(module1Services));
    Log.d(TAG, String.valueOf(module2Services));
  }

  static {
    init();
  }

  public static Application getApp() {
    return app;
  }

  public static void appAttachBaseApplication(Context context, Application application) {
    app = application;
    for (IApplication app : iApplicationList) {
      if (null != app) {
        app.attachBaseApplication(context);
      }
    }
  }

  public static void appCreate() {
    for (IApplication app : iApplicationList) {
      if (null != app) {
        app.create();
      }
    }
  }
}
