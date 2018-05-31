package com.zty.yisheng.common.support;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.os.Process;

import com.ainemo.sdk.otf.NemoSDK;
import com.ainemo.sdk.otf.Settings;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.crashreport.CrashReport;
import com.zty.yisheng.common.component.DaggerYiShengComponent;
import com.zty.yisheng.common.component.YiShengComponent;
import com.zty.yisheng.common.component.module.YiShengModule;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 92915 on 2018/3/26.
 */

public class YiShengApplication extends Application {

    private static YiShengApplication instance;

    public static synchronized YiShengApplication getInstance(){
        return instance;
    }

    private Set<Activity> mActivity;

    public YiShengComponent yiShengComponent;

    public YiShengComponent getComponent() {
        return yiShengComponent;
    }

    public final static String PKG_NAME = "com.zty.yisheng";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initComponent();
        CrashReport.initCrashReport(getApplicationContext(), "fc49760abd", true);
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush
        Settings settings = new Settings("08aade0df7defde55b9bf49c502c092dad8fcee1");
        int pId = Process.myPid();
        String processName = "";
        ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> ps = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo p : ps) {
            if (p.pid == pId) {
                processName = p.processName;
                break;
            }
        }

        // 避免被初始化多次
        if (processName.equals(getPackageName())) {
            NemoSDK nemoSDK = NemoSDK.getInstance();
            nemoSDK.init(this, settings);
        }
    }

    private void initComponent() {
        if (yiShengComponent == null) {
            yiShengComponent= DaggerYiShengComponent.builder().yiShengModule(new YiShengModule(instance)).build();
        }
    }

    //全局记录activity
    public void addActivity(Activity act) {
        if (mActivity == null) {
            mActivity = new HashSet<>();
        }
        mActivity.add(act);
    }

    //删除关闭activity
    public void removeActivity(Activity act) {
        if (mActivity != null) {
            mActivity.remove(act);
        }
    }

    //扫描关闭所有activity
    public void exitOneNews() {
        if (mActivity != null) {
            synchronized (mActivity) {
                for (Activity act : mActivity) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
