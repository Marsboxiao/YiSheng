package com.zty.yisheng.common.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zty.yisheng.common.component.DaggerFragmentComponent;
import com.zty.yisheng.common.component.FragmentComponent;
import com.zty.yisheng.common.component.module.FragmentModule;
import com.zty.yisheng.common.support.YiShengApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 92915 on 2018/3/26.
 */

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView {

    @Inject
    protected T mPersenter;
    protected FragmentComponent mFragmentComponent;
    private Unbinder mBinder;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        initBaidu();
        View view = inflater.inflate(getLayout(), container, false);
        mBinder = ButterKnife.bind(this, view);
        initComponent();
        initInjector();
        if (mPersenter != null)
            mPersenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initBaidu();

    //注入
    protected abstract void initInjector();

    //布局加载文件
    protected abstract int getLayout();

    //初始化布局
    protected abstract void initView();

    //初始化component
    protected void initComponent(){
        mFragmentComponent= DaggerFragmentComponent.builder()
                .yiShengComponent(YiShengApplication.getInstance().getComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

}
