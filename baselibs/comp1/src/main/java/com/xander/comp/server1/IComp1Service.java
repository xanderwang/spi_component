package com.xander.comp.server1;

import android.content.Context;

import com.xander.comp.base.ICompService;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.base.server1
 * @ClassName: IComp1Service
 * @Description: IComp1Service 的定义，暴露该组件对外暴露的部分。
 * @Author: xander
 * @CreateDate: 2020/4/19 13:59
 * @Version: 1.0
 */
public interface IComp1Service extends ICompService {

    void openComp1Activity(Context context);
}
