package com.xander.comp.server2;

import android.content.Context;

import com.xander.comp.base.ICompService;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.base.server2
 * @ClassName: IComp2Service
 * @Description: IComp2Service 的定义，暴露了该组件对外暴露的资源
 * @Author: xander
 * @CreateDate: 2020/4/19 13:59
 * @Version: 1.0
 */
public interface IComp2Service extends ICompService {

    void openComp2Activity(Context context);
}
