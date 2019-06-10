package com.xander.comp.core.services;

import android.content.Context;
import com.xander.comp.core.application.CompApplication;

public class SimpleComp1Service implements IComp1Service {

  @Override public void openComp1(Context context) {

  }

  @Override public CompApplication getApplication() {
    return new CompApplication();
  }
}
