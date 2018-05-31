package com.zty.yisheng.common.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.zty.yisheng.common.support.YiShengApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 92915 on 2018/3/29.
 */

public abstract class SupportActivity extends FragmentActivity {

    private Unbinder mBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mBinder = ButterKnife.bind(this);
        YiShengApplication.getInstance().addActivity(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        YiShengApplication.getInstance().removeActivity(this);
    }

    //初始化布局
    protected abstract void initView(Bundle savedInstanceState);

    //布局加载文件
    protected abstract int getLayout();

}
