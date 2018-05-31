package com.zty.yisheng.common.component.module;

import android.app.Service;
import android.content.Context;

import com.zty.yisheng.common.component.scope.ContextLife;
import com.zty.yisheng.common.component.scope.ServiceScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarsBoxiao on 2017/3/31.
 */

@Module
public class ServiceModule {

    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @ServiceScope
    @ContextLife("Service")
    public Context ProvideServiceContext() {
        return mService;
    }

}
