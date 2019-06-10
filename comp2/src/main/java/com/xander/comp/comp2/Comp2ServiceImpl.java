package com.xander.comp.comp2;

import android.content.Context;
import android.content.Intent;
import com.xander.comp.core.application.CompApplication;
import com.xander.comp.core.services.IComp2Service;

/**
 * author: Xander
 * date: 2019/4/13
 */
public class Comp2ServiceImpl implements IComp2Service {

  @Override public CompApplication getApplication() {
    return null;
  }

  @Override public void openComp2(Context context) {
    Intent intent = new Intent();
    intent.setClass(context, Comp2Activity.class);
    context.startActivity(intent);
  }
}
