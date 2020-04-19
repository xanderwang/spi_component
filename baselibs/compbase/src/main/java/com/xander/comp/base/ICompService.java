package com.xander.comp.base;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.base
 * @ClassName: ICompService
 * @Description: 模块的基类，对外暴露模块的功能
 * @Author: xander
 * @CreateDate: 2020/4/19 15:02
 * @Version: 1.0
 */
public interface ICompService {
    /**
     * 优先级，越高优先级越高
     * @return
     */
    int priority();

    ICompApplication getCompApplication();
}
