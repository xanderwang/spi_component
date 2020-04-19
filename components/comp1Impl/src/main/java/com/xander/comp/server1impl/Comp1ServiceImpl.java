package com.xander.comp.server1impl;


import android.content.Context;
import android.content.Intent;

import com.xander.comp.base.ICompApplication;
import com.xander.comp.server1.IComp1Service;

/**
 * @author: Xander
 * date: 2019/4/12
 */
public class Comp1ServiceImpl implements IComp1Service {

    Comp1Application comp1Application;

    @Override
    public void openComp1Activity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Comp1Activity.class);
        context.startActivity(intent);
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public ICompApplication getCompApplication() {
        if (null == comp1Application) {
            comp1Application = new Comp1Application();
        }
        return comp1Application;
    }
}
