package com.zty.yisheng.common.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zty.yisheng.common.component.ActivityComponent;
import com.zty.yisheng.common.component.DaggerActivityComponent;
import com.zty.yisheng.common.component.module.ActivityModule;
import com.zty.yisheng.common.support.YiShengApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 92915 on 2018/4/23.
 */

public abstract class BaseSupportActivity <T extends BaseContract.BasePresenter> extends FragmentActivity implements BaseContract.BaseView{

    @Inject
    protected T mPersenter;
    protected ActivityComponent mActivityComponent;
    private Unbinder mBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mBinder = ButterKnife.bind(this);
        initComponent();
        initInjector();
        if (mPersenter != null) {
            mPersenter.attachView(this);
        }
        YiShengApplication.getInstance().addActivity(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        YiShengApplication.getInstance().removeActivity(this);
    }

    //初始化布局
    protected abstract void initView();

    //布局加载文件
    protected abstract int getLayout();

    //注入
    protected abstract void initInjector();

    //初始化component
    protected void initComponent(){
        mActivityComponent= DaggerActivityComponent.builder()
                .yiShengComponent(YiShengApplication.getInstance().getComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

}
