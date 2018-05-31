package com.zty.yisheng.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.SDocDataBean;
import com.zty.yisheng.presenter.contract.MySetContract;
import com.zty.yisheng.presenter.presenter.MySetPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/5/2.
 */

public class MySetActivity extends BaseActivity<MySetPresenter> implements MySetContract.View {
    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.my_complete)
    TextView myComplete;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.my_name)
    EditText myName;
    @BindView(R.id.my_sex)
    EditText mySex;
    @BindView(R.id.my_old)
    EditText myOld;
    @BindView(R.id.my_phone)
    EditText myPhone;
    @BindView(R.id.my_specialty)
    EditText mySpecialty;
    @BindView(R.id.my_hospital)
    EditText myHospital;
    @BindView(R.id.my_office)
    EditText myOffice;
    private String docid;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("医生资料");
        myOld.setCursorVisible(false);
        myOld.setFocusable(false);
        myOld.setFocusableInTouchMode(false);
        docid= SharePreUtil.getPrefString(YiShengApplication.getInstance(),"docid","111");
        mPersenter.getDocDataAttribute(docid);
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
        return R.layout.activity_myset;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(MySetActivity.this);
    }

    @OnClick(R.id.my_complete)
    public void setMyComplete(){
        String name = myName.getText().toString();
        String sex = mySex.getText().toString();
        String old = myOld.getText().toString();
        String phone = myPhone.getText().toString();
        String specialty = mySpecialty.getText().toString();
        String hospital = myHospital.getText().toString();
        String office = myOffice.getText().toString();
        mPersenter.getSDocDataAttribute(docid,name,sex,old,phone,specialty,hospital,office);
    }

    @OnClick(R.id.my_old)
    public void setMyOld(){
        showDatePickDlg();
    }

    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MySetActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month=monthOfYear+1;
                myOld.setText(year+"/"+month+"/"+dayOfMonth+" 00:00:00");
                //2018/1/1 0:00:00
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void startDocData(DocDataBean docDataBean) {
        myName.setText(docDataBean.getData().getDoctor_name());
        mySex.setText(docDataBean.getData().getGender());
        myOld.setText(docDataBean.getData().getBirthday());
        myPhone.setText(docDataBean.getData().getMobile());
        mySpecialty.setText(docDataBean.getData().getProfessional());
        myHospital.setText(docDataBean.getData().getHospital());
        myOffice.setText(docDataBean.getData().getOffice());
    }

    @Override
    public void againDocData(int code) {

    }

    @Override
    public void startSDocData(SDocDataBean sDocDataBean) {
        finish();
    }

    @Override
    public void againSDocData(int code) {

    }
}
