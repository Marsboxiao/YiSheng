package com.zty.yisheng.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.presenter.contract.LoginContract;
import com.zty.yisheng.presenter.presenter.LoginPresenter;

import butterknife.BindView;

/**
 * Created by 92915 on 2018/4/26.
 */

@SuppressLint("Registered")
public class SplashActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        getWindow().setBackgroundDrawableResource(R.mipmap.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转页面
                String username = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "username", "null");
                String password = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "password", "null");
                Log.d("MarsBoxiao", username + "+" + password);
                if (username.equals("null") || password.equals("null")) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    mPersenter.getLoginAttribute(username, password);
                }
            }
        }, 800);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(SplashActivity.this);
    }

    @Override
    public void startLogin(LoginBean loginBean) {
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "docid", loginBean.getData());
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void againLogin(int code) {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

}
