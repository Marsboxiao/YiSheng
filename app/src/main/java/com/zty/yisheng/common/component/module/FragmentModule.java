package com.zty.yisheng.common.component.module;

import android.app.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.zty.yisheng.common.component.scope.ContextLife;
import com.zty.yisheng.common.component.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarsBoxiao on 2017/3/31.
 */

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Fragment provideFragment() {
        return mFragment;
    }

}
