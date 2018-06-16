package com.zty.yisheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.RegisterBean;
import com.zty.yisheng.presenter.contract.RegisterContract;
import com.zty.yisheng.presenter.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/6/16.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_newpassword)
    EditText registerNewpassword;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    private String username;
    private String password;
    private String newpassword;

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("注册");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //android.R.id.home是Android内置home按钮的id
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(RegisterActivity.this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void startRegister(RegisterBean registerBean) {
        mPersenter.getLoginAttribute(username, password);
    }

    @Override
    public void againRegister(int code) {
        ToastUtil.showShort(RegisterActivity.this, "抱歉，该用户名已被注册");
    }

    @Override
    public void startLogin(LoginBean loginBean) {
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "username", username);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "password", password);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "docid", loginBean.getData());
        LoginActivity.loginActivity.finish();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void againLogin(int code) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.register_button)
    public void onViewClicked() {
        username = registerUsername.getText().toString();
        password = registerPassword.getText().toString();
        newpassword = registerNewpassword.getText().toString();
        boolean a = true;
        if (username.isEmpty()) {
            a = false;
            ToastUtil.showShort(RegisterActivity.this, "请输入手机号码");
        }
        if (password.isEmpty()) {
            a = false;
            ToastUtil.showShort(RegisterActivity.this, "请输入密码");
        }
        if (newpassword.isEmpty()) {
            a = false;
            ToastUtil.showShort(RegisterActivity.this, "请输入确认密码");
        }
        if (!password.equals(newpassword)) {
            a = false;
            ToastUtil.showShort(RegisterActivity.this, "密码与确认密码不相符");
        }
        if (a) {
            mPersenter.getRegisterAttribute(username, password);
        }
    }

}
