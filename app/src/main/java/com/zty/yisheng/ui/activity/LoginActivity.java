package com.zty.yisheng.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.presenter.contract.LoginContract;
import com.zty.yisheng.presenter.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/4/22.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_forget)
    TextView loginForget;
    @BindView(R.id.login_register)
    TextView loginRegister;
    private String username;
    private String password;
    public static LoginActivity loginActivity=null;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        initPermissions();
        loginActivity = this;
    }

    private void initPermissions() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.VIBRATE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.VIBRATE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_CONTACTS);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.GET_ACCOUNTS) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.GET_ACCOUNTS);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.RECORD_AUDIO) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.RECORD_AUDIO);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LoginActivity.this, permissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            ToastUtil.showShort(LoginActivity.this, "必须同意所有权限才能使用本功能");
                            finish();
                            return;
                        }
                    }
                    initPermissions();
                } else {
                    ToastUtil.showShort(LoginActivity.this, "发生未知错误");
                    finish();
                }
                break;
            default:
        }
    }

    @OnClick(R.id.login_button)
    public void setLoginButton(){
        username = loginUsername.getText().toString();
        password = loginPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            ToastUtil.show(LoginActivity.this, "请输入账号密码", Toast.LENGTH_SHORT);
        } else {
            mPersenter.getLoginAttribute(username, password);
        }
    }

    @OnClick(R.id.login_forget)
    public void setLoginForget(){
        startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
    }

    @OnClick(R.id.login_register)
    public void setLoginRegister(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(LoginActivity.this);
    }

    @Override
    public void startLogin(LoginBean loginBean) {
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "username", username);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "password", password);
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "docid", loginBean.getData());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void againLogin(int code) {
        ToastUtil.show(LoginActivity.this, "账号密码不正确", Toast.LENGTH_SHORT);
    }

}
