package com.zty.yisheng.ui.activity;


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ainemo.sdk.otf.ConnectNemoCallback;
import com.ainemo.sdk.otf.NemoSDK;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.model.LatLng;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.base.SupportActivity;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.ExampleUtil;
import com.zty.yisheng.common.utils.RandomUtil;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.common.view.NoScrollViewPager;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.presenter.contract.MainContract;
import com.zty.yisheng.presenter.presenter.MainPresenter;
import com.zty.yisheng.ui.adapter.MainFragmentAdapter;
import com.zty.yisheng.ui.fragment.KnowledgeFragment;
import com.zty.yisheng.ui.fragment.MapFragment;
import com.zty.yisheng.ui.fragment.MessageFragment;
import com.zty.yisheng.ui.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 92915 on 2018/3/22.
 * 主界面操作
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @BindView(R.id.bnve_button)
    BottomNavigationViewEx bnveButton;
    @BindView(R.id.main_viewpager)
    NoScrollViewPager mainViewpager;
    private MainFragmentAdapter mainFragmentAdapter;
    private List<Fragment> fragments;
    public static boolean isForeground = false;

    @Override
    protected void initView() {
        bnveButton.enableAnimation(false);
        bnveButton.enableShiftingMode(false);
        bnveButton.enableItemShiftingMode(false);
        //清楚默认的渲染
        bnveButton.setIconTintList(0, null);
        bnveButton.setIconTintList(1, null);
        bnveButton.setIconTintList(2, null);
        bnveButton.setIconTintList(3, null);
        //添加fragment模块
        fragments = new ArrayList<>();
        fragments.add(KnowledgeFragment.getInstance());
        fragments.add(MapFragment.getInstance());
        fragments.add(MessageFragment.getInstance());
        fragments.add(MyFragment.getInstance());
        //装载fragment模块
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        mainViewpager.setNoScroll(true);
        mainViewpager.setAdapter(mainFragmentAdapter);
        bnveButton.setupWithViewPager(mainViewpager);
        //极光推送激活广播
        registerMessageReceiver();  // used for receive msg
        BasicPushNotificationBuilder basicPushNotificationBuilder = new BasicPushNotificationBuilder(MainActivity.this);
        basicPushNotificationBuilder.statusBarDrawable = R.drawable.jpush_notification_icon;
        basicPushNotificationBuilder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, basicPushNotificationBuilder);
        //获取极光id
        String rid = JPushInterface.getRegistrationID(YiShengApplication.getInstance());
        Log.d("MarsBoxiao", rid);
        mPersenter.getgetUpdataJGAttribute(SharePreUtil.getPrefString(YiShengApplication.getInstance(), "docid", "1"), rid);
        //匿名登录
        String username = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "username", "111");
        String nimingusername = username + "5201314";
        String niminguserid = username + "5201314";
        NemoSDK nemoSDK = NemoSDK.getInstance();
        Log.d("MarsBoxiao", nimingusername);
        nemoSDK.loginExternalAccount(nimingusername, niminguserid, new ConnectNemoCallback() {
            @Override
            public void onFailed(final int i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "匿名登录失败，错误码：" + i, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSuccess(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mPersenter.getTimeAttribute();
    }

    @Override
    public void startTime(SysSettingBean sysSettingBean) {
        for (SysSettingBean.DataBean sysbean : sysSettingBean.getData()) {
            if (sysbean.getStrkey().equals("answerhanguptimeout")) {
                SharePreUtil.setPrefString(YiShengApplication.getInstance(),"time",sysbean.getStrvalue());
            }
        }
    }

    @Override
    public void againTime(int code) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(MainActivity.this);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            boolean pushvideo = intent.getBooleanExtra("PushVideo", false);
            if (pushvideo) {
                mainViewpager.setCurrentItem(1);
            }
        }
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
//        NemoSDK.getInstance().logout();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.zty.yisheng.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void startJGid(UpdataJGBean updataJGBean) {

    }

    @Override
    public void againJGid(int code) {

    }

}
