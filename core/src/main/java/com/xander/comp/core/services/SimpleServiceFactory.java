package com.xander.comp.core.services;

import android.util.Log;
import java.util.HashMap;

public class SimpleServiceFactory {
  private static final String TAG = "SimpleServiceFactory";

  public static HashMap<Class, Class> simpleServiceHashMap = new HashMap<>();

  public static void register(Class serviceClass, Class simpleServiceClass) {
    simpleServiceHashMap.put(serviceClass, simpleServiceClass);
  }

  public static ICompService createSimpleService(Class<ICompService> compServiceClass) {
    try {
      Class s = simpleServiceHashMap.get(compServiceClass);
      if (s != null) {
        return (ICompService) s.newInstance();
      }
    } catch (Exception e) {
      Log.e(TAG, "createSimpleService: ", e);
    }
    return null;
  }
}
