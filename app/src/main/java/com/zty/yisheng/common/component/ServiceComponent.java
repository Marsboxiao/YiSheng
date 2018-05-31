package com.zty.yisheng.common.component;

import com.zty.yisheng.common.component.module.ServiceModule;
import com.zty.yisheng.common.component.scope.ServiceScope;

import dagger.Component;

/**
 * Created by 92915 on 2018/3/26.
 */

@ServiceScope
@Component(dependencies = YiShengComponent.class,modules = ServiceModule.class)
public interface ServiceComponent {
}
