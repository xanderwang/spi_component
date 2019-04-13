package com.xander.comp.comp2;

import android.content.Context;
import android.content.Intent;
import com.xander.comp.core.services.IComp2Services;

/**
 * author: Xander
 * date: 2019/4/13
 */
public class Comp2ServicesImpl implements IComp2Services {

  @Override public void openModule2(Context context) {
    Intent module2 = new Intent();
    module2.setClass(context, Comp2Activity.class);
    context.startActivity(module2);
  }
}
