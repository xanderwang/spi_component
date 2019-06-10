package com.xander.comp.core.services;

import android.content.Context;
import com.xander.comp.core.application.CompApplication;

public class SimpleComp2Service implements IComp2Service {

  @Override public void openComp2(Context context) {

  }

  @Override public CompApplication getApplication() {
    return new CompApplication();
  }
}
