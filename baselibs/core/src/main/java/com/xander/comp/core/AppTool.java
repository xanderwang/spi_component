package com.xander.comp.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.xander.comp.base.ICompApplication;
import com.xander.comp.base.ICompService;
import com.xander.comp.server1.IComp1Service;
import com.xander.comp.server2.IComp2Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 这个类主要用来管理各个组件，包括组件的初始化和组件对外暴露的资源。
 * author: Xander
 * date: 2019/4/12
 */
public class AppTool {

    private static final String TAG = "AppTool";

    private static Application app;

    private static ArrayList<Class<? extends ICompService>> compServiceClassList = new ArrayList<>();

    private static HashMap<Class, ICompService> compServiceMap = new HashMap<>();

    private static ArrayList<ICompApplication> compApplicationList = new ArrayList<>();

    public static void init() {
        if (!compApplicationList.isEmpty()) {
            Log.e(TAG, "init: ", new IllegalStateException("can not call init more than one time!!!"));
            return;
        }
        initServices();
        Log.d(TAG, "init: compServiceClassList:" + compServiceClassList);
        Log.d(TAG, "init: compApplicationList:" + compApplicationList);
    }

    private static void initServices() {
        for (Class<? extends ICompService> serviceClass : compServiceClassList) {
            ICompService compService = loadCompService(serviceClass);
            if (null == compService) {
                continue;
            }
            compServiceMap.put(serviceClass, compService);
            loadCompApplication(compService);
        }
    }

    private static ICompService loadCompService(Class<? extends ICompService> serviceClass) {
        Log.d(TAG, "loadService: " + serviceClass);
        ICompService compService = null;
        ServiceLoader<? extends ICompService> serviceLoader = ServiceLoader.load(serviceClass);
        serviceLoader.reload();
        Iterator<? extends ICompService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            ICompService tmp = iterator.next();
            Log.d(TAG, "loadService find:" + tmp.getClass());
            if (null == compService || tmp.priority() > compService.priority()) {
                compService = tmp;
            }
        }
        return compService;
    }

    private static void loadCompApplication(ICompService services) {
        if (null != services && null != services.getCompApplication()) {
            compApplicationList.add(services.getCompApplication());
        }
    }

    static {
        // 这里最好通过 ams 或者其他的方式来动态的添加
        compServiceClassList.add(IComp1Service.class);
        compServiceClassList.add(IComp2Service.class);
        init();
    }

    public static Application Application() {
        return app;
    }

    public static <SERVICE extends ICompService> SERVICE getAppService(Class<SERVICE> serviceClass) {
        Log.d(TAG, "getAppService: " + serviceClass);
        return (SERVICE) compServiceMap.get(serviceClass);
    }

    // 这个方法最好通过 asm 自动生成
    public static IComp1Service comp1Service() {
        ICompService compService = compServiceMap.get(IComp1Service.class);
        if (compService instanceof IComp1Service) {
            return (IComp1Service) compService;
        }
        return null;
    }

    // 这个方法最好通过 asm 自动生成
    public static IComp2Service comp2Service() {
        ICompService compService = compServiceMap.get(IComp2Service.class);
        if (compService instanceof IComp2Service) {
            return (IComp2Service) compService;
        }
        return null;
    }

    public static void appAttachBaseApplication(Context context, Application application) {
        app = application;
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onAttachBaseApplication(context);
            }
        }
    }

    public static void appCreate() {
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onCreate();
            }
        }
    }

    public static void appTerminate() {
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onTerminate();
            }
        }
    }

    public static void appConfigurationChanged(Configuration newConfig) {
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onConfigurationChanged(newConfig);
            }
        }
    }

    public static void appLowMemory() {
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onLowMemory();
            }
        }
    }

    public static void appTrimMemory(int level) {
        for (ICompApplication app : compApplicationList) {
            if (null != app) {
                app.onTrimMemory(level);
            }
        }
    }
}
