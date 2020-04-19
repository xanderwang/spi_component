package com.xander.comp.server2impl;

import android.util.Log;

import com.xander.comp.base.SimpleCompApp;

/**
 * date: 2019/4/13
 *
 * @author xander
 */
public class Comp2Application extends SimpleCompApp {

    private static final String TAG = "Comp2Application";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
}
