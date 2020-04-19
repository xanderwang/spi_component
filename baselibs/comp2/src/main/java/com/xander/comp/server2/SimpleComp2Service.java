package com.xander.comp.server2;

import android.content.Context;
import android.widget.Toast;

import com.xander.comp.base.ICompApplication;

/**
 * @ProjectName: spi_component
 * @Package: com.xander.comp.server1
 * @ClassName: SimpleComp1Service
 * @Description: 模板的默认实现，用于其他模板依赖时候使用
 * @Author: xander
 * @CreateDate: 2020/4/19 15:30
 * @Version: 1.0
 */
public class SimpleComp2Service implements IComp2Service {

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public void openComp2Activity(Context context) {
        Toast.makeText(context, "正在打开 comp2", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "我是模板方法，如果需要完整功能，请依赖 Impl 库", Toast.LENGTH_SHORT).show();
    }

    @Override
    public ICompApplication getCompApplication() {
        return null;
    }
}
