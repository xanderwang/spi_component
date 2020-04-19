package com.xander.comp.base;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.base
 * @ClassName: xLog
 * @Description: log 日志工具
 * @Author: xander
 * @CreateDate: 2020/4/19 13:59
 * @Version: 1.0
 */
public class xLog {

    public static void df(String tag, String template, Object... objects) {
        d(tag, String.format(template, objects));
    }

    public static void d(String tag, String msg) {

    }
}
