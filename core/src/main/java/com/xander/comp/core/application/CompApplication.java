package com.xander.comp.core.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.xander.comp.core.AppTool;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class CompApplication {

  public void onAttachBaseApplication(Context context) {

  }

  public void onCreate() {

  }

  public void onTerminate() {

  }

  public void onConfigurationChanged(Configuration newConfig) {

  }

  public void onLowMemory() {

  }

  public void onTrimMemory(int level) {

  }

  public void registerActivityLifecycleCallbacks(
      Application.ActivityLifecycleCallbacks callback) {
    AppTool.Application().registerActivityLifecycleCallbacks(callback);
  }

  public void unregisterActivityLifecycleCallbacks(
      Application.ActivityLifecycleCallbacks callback) {
    AppTool.Application().unregisterActivityLifecycleCallbacks(callback);
  }
}
