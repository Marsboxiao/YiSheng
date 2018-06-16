package com.zty.yisheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.CodeBean;
import com.zty.yisheng.model.bean.ForgetBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.presenter.contract.ForgetContract;
import com.zty.yisheng.presenter.presenter.ForgetPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/6/16.
 */

public class ForgetActivity extends BaseActivity<ForgetPresenter> implements ForgetContract.View {

    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.forget_username)
    EditText forgetUsername;
    @BindView(R.id.forget_code)
    EditText forgetCode;
    @BindView(R.id.forget_getcode)
    Button forgetGetcode;
    @BindView(R.id.forget_password)
    EditText forgetPassword;
    @BindView(R.id.forget_newpassword)
    EditText forgetNewpassword;
    @BindView(R.id.forget_button)
    Button forgetButton;
    private String username;
    private String password;
    private String newpassword;
    private String code;

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("忘记密码");
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
        return R.layout.activity_forget;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(ForgetActivity.this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void startForget(ForgetBean forgetBean) {
        mPersenter.getLoginAttribute(username, password);
    }

    @Override
    public void againForget(int code) {
        ToastUtil.showShort(ForgetActivity.this,"验证码不正确，请重新获取");
    }

    @Override
    public void startCode(CodeBean codeBean) {
        ToastUtil.showShort(ForgetActivity.this,"验证码已发送");
    }

    @Override
    public void againCode(int code) {
        ToastUtil.showShort(ForgetActivity.this,"获取验证码失败，请重新获取");
    }

    @Override
    public void startLogin(LoginBean loginBean) {
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "username", username);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "password", password);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "docid", loginBean.getData());
        LoginActivity.loginActivity.finish();
        startActivity(new Intent(ForgetActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void againLogin(int code) {
        startActivity(new Intent(ForgetActivity.this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.forget_getcode, R.id.forget_button})
    public void onViewClicked(View view) {
        username = forgetUsername.getText().toString();
        password = forgetPassword.getText().toString();
        newpassword = forgetNewpassword.getText().toString();
        code = forgetCode.getText().toString();
        switch (view.getId()) {
            case R.id.forget_getcode:
                if (username.isEmpty()) {
                    ToastUtil.showShort(ForgetActivity.this,"请输入手机号码之后获取验证码");
                } else {
                    mPersenter.getCodeAttribute(username);
                }
                break;
            case R.id.forget_button:
                boolean a = true;
                if (username.isEmpty()) {
                    a = false;
                    ToastUtil.showShort(ForgetActivity.this, "请输入手机号码");
                }
                if (password.isEmpty()) {
                    a = false;
                    ToastUtil.showShort(ForgetActivity.this, "请输入密码");
                }
                if (newpassword.isEmpty()) {
                    a = false;
                    ToastUtil.showShort(ForgetActivity.this, "请输入确认密码");
                }
                if (code.isEmpty()) {
                    a = false;
                    ToastUtil.showShort(ForgetActivity.this,"请输入验证码");
                }
                if (!password.equals(newpassword)) {
                    a = false;
                    ToastUtil.showShort(ForgetActivity.this, "密码与确认密码不相符");
                }
                if (a) {
                    mPersenter.getForgetAttribute(username, password,code);
                }
                break;
        }
    }
}
