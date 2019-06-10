package com.xander.comp.core.services;

import android.content.Context;
import android.widget.Toast;
import com.xander.comp.core.application.CompApplication;

public class SimpleComp2Service implements IComp2Service {

  @Override public void openComp2(Context context) {
    Toast.makeText(context, "I am SimpleComp2Service", Toast.LENGTH_SHORT).show();
  }

  @Override public CompApplication getApplication() {
    return new CompApplication();
  }
}
