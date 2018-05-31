package com.zty.yisheng.common.component;

import android.app.Activity;

import com.zty.yisheng.common.component.module.ActivityModule;
import com.zty.yisheng.common.component.scope.ActivityScope;
import com.zty.yisheng.ui.activity.BannerImageActivity;
import com.zty.yisheng.ui.activity.KnowcContentActivity;
import com.zty.yisheng.ui.activity.KnowsListActivity;
import com.zty.yisheng.ui.activity.LoginActivity;
import com.zty.yisheng.ui.activity.MainActivity;
import com.zty.yisheng.ui.activity.MySetActivity;
import com.zty.yisheng.ui.activity.SickActivity;
import com.zty.yisheng.ui.activity.SplashActivity;
import com.zty.yisheng.ui.activity.TimeActivity;

import dagger.Component;

/**
 * Created by 92915 on 2018/3/26.
 */

@ActivityScope
@Component(dependencies = YiShengComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(BannerImageActivity bannerImageActivity);

    void inject(KnowsListActivity knowsListActivity);

    void inject(KnowcContentActivity knowcContentActivity);

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(TimeActivity timeActivity);

    void inject(MySetActivity mySetActivity);

    void inject(SickActivity sickActivity);

}
