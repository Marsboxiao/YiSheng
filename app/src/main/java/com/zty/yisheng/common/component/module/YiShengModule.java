package com.zty.yisheng.common.component.module;

import com.zty.yisheng.common.component.scope.YiShengScope;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.model.api.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarsBoxiao on 2017/5/2.
 */

@YiShengScope
@Module
public class YiShengModule {

    private final YiShengApplication application;

    public YiShengModule(YiShengApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    YiShengApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

}
