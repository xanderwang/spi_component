package com.xander.comp.server2impl;

import android.content.Context;
import android.content.Intent;

import com.xander.comp.base.ICompApplication;
import com.xander.comp.server2.IComp2Service;

/**
 * author: Xander
 * date: 2019/4/13
 *
 * @author xander
 */
public class Comp2ServiceImpl implements IComp2Service {

    Comp2Application comp2Application;

    @Override
    public void openComp2Activity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Comp2Activity.class);
        context.startActivity(intent);
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public ICompApplication getCompApplication() {
        if( null == comp2Application ) {
            comp2Application = new Comp2Application();
        }
        return comp2Application;
    }
}
