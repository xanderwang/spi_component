package com.xander.comp.base;

import android.content.Context;
import android.content.res.Configuration;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.base
 * @ClassName: ICompApplication
 * @Description: 模块的 applicatioin 类似 app 的 Applicaiton
 * @Author: xander
 * @CreateDate: 2020/4/19 15:07
 * @Version: 1.0
 */
public interface ICompApplication {

    public void onAttachBaseApplication(Context context);

    public void onCreate();

    public void onTerminate();

    public void onConfigurationChanged(Configuration newConfig);

    public void onLowMemory();

    public void onTrimMemory(int level);
}
