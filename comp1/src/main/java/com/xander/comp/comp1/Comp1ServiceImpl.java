package com.xander.comp.comp1;

import android.content.Context;
import android.content.Intent;
import com.xander.comp.core.application.CompApplication;
import com.xander.comp.core.services.IComp1Service;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class Comp1ServiceImpl implements IComp1Service {

  @Override public CompApplication getApplication() {
    return new Copm1Application();
  }

  @Override public void openComp1(Context context) {
    Intent intent = new Intent(context, Comp1Activity.class);
    context.startActivity(intent);
  }
}
