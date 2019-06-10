package com.xander.comp.core.services;

import android.content.Context;
import android.widget.Toast;
import com.xander.comp.core.application.CompApplication;

public class SimpleComp1Service implements IComp1Service {

  @Override public void openComp1(Context context) {
    Toast.makeText(context, "I am SimpleComp1Service", Toast.LENGTH_SHORT).show();
  }

  @Override public CompApplication getApplication() {
    return new CompApplication();
  }
}
