package com.xander.comp.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * author: Xander
 * date: 2019/6/10
 */
public class BaseApplication extends Application {

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    AppTool.appAttachBaseApplication(base, this);
  }

  @Override public void onCreate() {
    super.onCreate();
    AppTool.appCreate();
  }

  @Override public void onTerminate() {
    super.onTerminate();
    AppTool.appTerminate();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    AppTool.appConfigurationChanged(newConfig);
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    AppTool.appLowMemory();
  }

  @Override public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    AppTool.appTrimMemory(level);
  }

}
