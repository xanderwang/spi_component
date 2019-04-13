package com.xander.comp;

import android.app.Application;
import android.content.Context;
import com.xander.comp.core.AppTool;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class App extends Application {

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    AppTool.appAttachBaseApplication(base, this);
  }

  @Override public void onCreate() {
    super.onCreate();
    AppTool.appCreate();
  }
}
