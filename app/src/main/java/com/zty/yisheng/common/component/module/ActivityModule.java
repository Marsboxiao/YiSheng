package com.zty.yisheng.common.component.module;

import android.app.Activity;
import android.content.Context;


import com.zty.yisheng.common.component.scope.ActivityScope;
import com.zty.yisheng.common.component.scope.ContextLife;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarsBoxiao on 2017/3/31.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    @ContextLife("Activity")
    public Context ProvideActivityContext() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    public Activity ProvideActivity() {
        return mActivity;
    }
}
