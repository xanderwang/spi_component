package com.xander.comp.comp1;

import android.util.Log;
import com.xander.comp.core.application.CompApplication;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class Copm1Application extends CompApplication {

  private static final String TAG = "Copm1Application";

  @Override public void onCreate() {
    super.onCreate();
    Log.d(TAG, "onCreate ");
  }
}
