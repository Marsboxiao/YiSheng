package com.zty.yisheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.SickMessageBean;
import com.zty.yisheng.presenter.contract.SickContract;
import com.zty.yisheng.presenter.presenter.SickPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/5/2.
 */

public class SickActivity extends BaseActivity<SickPresenter> implements SickContract.View {
    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.sick_name)
    TextView sickName;
    @BindView(R.id.sick_sex)
    TextView sickSex;
    @BindView(R.id.sick_old)
    TextView sickOld;
    @BindView(R.id.sick_height)
    TextView sickHeight;
    @BindView(R.id.sick_weight)
    TextView sickWeight;
    @BindView(R.id.sick_bloodtype)
    TextView sickBloodtype;
    @BindView(R.id.sick_medicalhistory)
    LinearLayout sickMedicalhistory;
    @BindView(R.id.sick_allergens)
    LinearLayout sickAllergens;
    @BindView(R.id.sick_builtin)
    LinearLayout sickBuiltin;
    @BindView(R.id.sick_chronic)
    LinearLayout sickChronic;
    @BindView(R.id.sick_surgeryhistory)
    LinearLayout sickSurgeryhistory;
    private SickMessageBean sickMessageBean=null;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("患者资料");
        Intent intent = getIntent();
        String sickid = intent.getStringExtra("sickid");
        mPersenter.getSickAttribute(sickid);
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
        return R.layout.activity_sick;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(SickActivity.this);
    }

    @Override
    public void startSick(SickMessageBean sickMessageBean) {
        sickName.setText(sickMessageBean.getData().getName());
        sickSex.setText(sickMessageBean.getData().getGender());
        sickOld.setText(sickMessageBean.getData().getBirthday());
        sickHeight.setText(sickMessageBean.getData().getHeight());
        sickWeight.setText(sickMessageBean.getData().getWeight());
        sickBloodtype.setText(sickMessageBean.getData().getBloodtype());
        this.sickMessageBean=sickMessageBean;
    }

    @Override
    public void againSick(int code) {
        ToastUtil.showShort(SickActivity.this,"未获取到该患者数据");
    }

    @OnClick({R.id.sick_medicalhistory, R.id.sick_allergens, R.id.sick_builtin, R.id.sick_chronic, R.id.sick_surgeryhistory})
    public void onViewClicked(View view) {
        if (sickMessageBean != null) {
            switch (view.getId()) {
                case R.id.sick_medicalhistory:
                    showMessageDialog("病史",sickMessageBean.getData().getMedicalhistory());
                    break;
                case R.id.sick_allergens:
                    showMessageDialog("过敏源",sickMessageBean.getData().getAllergens());
                    break;
                case R.id.sick_builtin:
                    showMessageDialog("内置物",sickMessageBean.getData().getBuiltin());
                    break;
                case R.id.sick_chronic:
                    showMessageDialog("慢性病",sickMessageBean.getData().getChronic());
                    break;
                case R.id.sick_surgeryhistory:
                    showMessageDialog("手术史",sickMessageBean.getData().getSurgeryhistory());
                    break;
            }
        }
    }

    private void showMessageDialog(String title,String content){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("关闭", null)
                .show();
    }
}
