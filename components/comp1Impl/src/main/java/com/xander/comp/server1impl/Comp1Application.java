package com.xander.comp.server1impl;

import android.util.Log;

import com.xander.comp.base.SimpleCompApp;

/**
 * date: 2019/4/12
 * @author xander
 */
public class Comp1Application extends SimpleCompApp {

    private static final String TAG = "Comp1Application";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }
}
