package com.xander.comp.core.application;

import android.content.Context;

/**
 * author: Xander
 * date: 2019/4/12
 */
public interface IApplication {

  void attachBaseApplication(Context context);

  void create();

}
