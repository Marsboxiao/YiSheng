package com.zty.yisheng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SDocTimeBean;
import com.zty.yisheng.presenter.contract.TimeContract;
import com.zty.yisheng.presenter.presenter.TimePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/5/2.
 */

public class TimeActivity extends BaseActivity<TimePresenter> implements TimeContract.View {

    @BindView(R.id.setting_cancel)
    TextView settingCancel;
    @BindView(R.id.setting_complete)
    TextView settingComplete;
    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.time_start)
    TextView timeStart;
    @BindView(R.id.time_end)
    TextView timeEnd;
    @BindView(R.id.time_timepicker)
    TimePicker timeTimepicker;
    @BindView(R.id.time1_timepicker)
    TimePicker time1Timepicker;
    private String docid;
    private int starthour;
    private int startminute;
    private int endhour;
    private int endminute;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        commonText.setText("修改时间");
        docid = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "docid", "111");
        mPersenter.getDocTimeAttribute(docid);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_time;
    }

    @OnClick(R.id.setting_cancel)
    public void setSettingCancel(){
        finish();
    }

    @OnClick(R.id.setting_complete)
    public void setSettingComplete(){
        String begintime=timeStart.getText().toString();
        String endtime = timeEnd.getText().toString();
        mPersenter.getSDocTimeAttribute(docid,begintime,endtime);
    }

    @OnClick(R.id.time_start)
    public void setTimeStart(){
        time1Timepicker.setVisibility(View.GONE);
        timeTimepicker.setVisibility(View.VISIBLE);
        String[] start=timeStart.getText().toString().split(":");
        starthour = Integer.valueOf(start[0]);
        startminute = Integer.valueOf(start[2]);
        timeTimepicker.setCurrentHour(starthour);
        timeTimepicker.setCurrentMinute(startminute);
        timeTimepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                timeStart.setText(hourOfDay+":"+minute+":00");
            }
        });
    }

    @OnClick(R.id.time_end)
    public void setTimeEnd(){
        timeTimepicker.setVisibility(View.GONE);
        time1Timepicker.setVisibility(View.VISIBLE);
        String[] end=timeEnd.getText().toString().split(":");
        endhour = Integer.valueOf(end[0]);
        endminute = Integer.valueOf(end[1]);
        time1Timepicker.setCurrentHour(endhour);
        time1Timepicker.setCurrentMinute(endminute);
        time1Timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                timeEnd.setText(hourOfDay+":"+minute+":00");
            }
        });
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(TimeActivity.this);
    }

    @Override
    public void startDocTime(DocTimeBean docTimeBean) {
        timeStart.setText(docTimeBean.getData().getBegintime());
        timeEnd.setText(docTimeBean.getData().getEndtime());
    }

    @Override
    public void againDocTime(int code) {
        ToastUtil.show(TimeActivity.this,"服务器宕机了，请稍后再拨。",Toast.LENGTH_SHORT);
    }

    @Override
    public void startSDocTime(SDocTimeBean sDocTimeBean) {
        finish();
    }

    @Override
    public void againSDocTime(int code) {

    }

}
