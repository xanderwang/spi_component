package com.xander.comp.comp1;

import android.content.Context;
import android.content.Intent;
import com.xander.comp.core.application.XApplication;
import com.xander.comp.core.services.IComp1Services;

/**
 * author: Xander
 * date: 2019/4/12
 */
public class Comp1ServicesImpl implements IComp1Services {

  @Override public XApplication getApplication() {
    return new Copm1Application();
  }

  @Override public void openModule1(Context context) {
    Intent module1 = new Intent(context, Comp1Activity.class);
    context.startActivity(module1);
  }
}
