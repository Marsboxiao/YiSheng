package com.zty.yisheng.common.component;

import com.zty.yisheng.common.component.module.YiShengModule;
import com.zty.yisheng.common.component.scope.ActivityScope;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.model.api.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 92915 on 2018/3/26.
 */

@Singleton
@Component(modules = YiShengModule.class)
public interface YiShengComponent {

    YiShengApplication getContext();

    RetrofitHelper retrofitHelper();

}
