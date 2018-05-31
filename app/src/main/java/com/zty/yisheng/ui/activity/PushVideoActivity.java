package com.zty.yisheng.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ainemo.sdk.otf.NemoSDK;
import com.ainemo.sdk.otf.NemoSDKErrorCode;
import com.ainemo.sdk.otf.NemoSDKListener;
import com.ainemo.sdk.otf.RosterWrapper;
import com.ainemo.sdk.otf.VideoInfo;
import com.google.gson.Gson;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.SupportActivity;
import com.zty.yisheng.common.listener.FloatVideoWindowService;
import com.zty.yisheng.common.listener.FragmentOnClick;
import com.zty.yisheng.common.listener.VideoOnClick;
import com.zty.yisheng.model.bean.PushBean;
import com.zty.yisheng.ui.fragment.CallFragment;
import com.zty.yisheng.ui.fragment.VideoFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by 92915 on 2018/4/20.
 */

public class PushVideoActivity extends SupportActivity implements FragmentOnClick,VideoOnClick {

    private CallFragment callFragment;
    private VideoFragment videoFragment;
    public static FragmentActivity activity=null;
    private PushBean pushBean;
    private FragmentManager fm;

    @Override
    protected void initView(Bundle savedInstanceState) {
        activity=this;
        String content = null;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            }
        }
        if (content != null) {
            pushBean = new Gson().fromJson(content, PushBean.class);
        }
        hideSoftKeyboard();
        callFragment = new CallFragment();
        videoFragment = new VideoFragment();
        fm = getSupportFragmentManager();
        if (callFragment.isAdded()) {
            fm.beginTransaction().show(callFragment).commit();
        } else {
            fm.beginTransaction().add(R.id.pushvideo_frame, callFragment).commit();
        }
        initVideoView();
    }

    //视频监听
    private void initVideoView() {
        NemoSDK.getInstance().setNemoSDKListener(new NemoSDKListener() {
            @Override
            public void onContentStateChanged(ContentState contentState) {

            }

            @Override
            public void onCallFailed(int errorCode) {
                Observable.just(errorCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                if (NemoSDKErrorCode.WRONG_PASSWORD == integer) {
                                    Toast.makeText(PushVideoActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                                } else if (NemoSDKErrorCode.INVALID_PARAM == integer) {
                                    Toast.makeText(PushVideoActivity.this, "wrong param", Toast.LENGTH_SHORT).show();
                                } else if (NemoSDKErrorCode.NETWORK_UNAVAILABLE == integer) {
                                    Toast.makeText(PushVideoActivity.this, "net work unavailable", Toast.LENGTH_SHORT).show();
                                } else if (NemoSDKErrorCode.HOST_ERROR == integer) {
                                    Toast.makeText(PushVideoActivity.this, "host error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            public void onNewContentReceive(Bitmap bitmap) {

            }

            @Override
            public void onCallStateChange(final CallState state, final String reason) {
                Log.d("BBBBBBBB", state + ":" + reason);
                Observable.just(state)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<CallState>() {
                            @Override
                            public void accept(CallState callState) throws Exception {
                                switch (state) {
                                    case CONNECTING:
                                        hideSoftKeyboard();
                                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
                                        if (videoFragment.isAdded()) {
                                            fm.beginTransaction().hide(callFragment).show(videoFragment).commitAllowingStateLoss();
                                        } else {
                                            fm.beginTransaction().add(R.id.pushvideo_frame,
                                                    videoFragment).hide(callFragment).commitAllowingStateLoss();
                                        }
                                        videoFragment.showOutgoingContainer();
                                        break;
                                    case CONNECTED:
                                        hideSoftKeyboard();
                                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
                                        if (videoFragment.isAdded()) {
                                            fm.beginTransaction().hide(callFragment).show(videoFragment).commitAllowingStateLoss();
                                        } else {
                                            fm.beginTransaction().add(R.id.pushvideo_frame,
                                                    videoFragment).hide(callFragment).commitAllowingStateLoss();
                                        }
                                        videoFragment.showVideContainer();
                                        break;
                                    case DISCONNECTED:
                                        if (reason.equals("CANCEL")) {
                                            Log.d("BBBBBBBB","已经走过了222");
                                        }

                                        if (reason.equals("BUSY")) {
                                            Log.d("BBBBBBBB","已经走过了111");
                                        }
                                        Log.d("BBBBBBBB","已经走过了333");
                                        if (videoFragment.isAdded()) {
                                            videoFragment.releaseResource();
                                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                            finish();
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
            }

            @Override
            public void onVideoDataSourceChange(List<VideoInfo> videoInfos) {
                Observable.just(videoInfos)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<VideoInfo>>() {
                            @Override
                            public void accept(List<VideoInfo> videoInfos) {
                                videoFragment.onVideoDataSourceChange(videoInfos);
                            }
                        });
            }

            @Override
            public void onRosterChange(RosterWrapper rosterWrapper) {

            }

            @Override
            public void onConfMgmtStateChanged(final int callIndex,final String opearation,final boolean isMuteDisable) {

            }

            @Override
            public void onRecordStatusNotification(final int callIndex,final boolean isStart,final String displayName) {

            }

            @Override
            public void onKickOut(int i, int i1) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PushVideoActivity.this, "被踢了", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNetworkIndicatorLevel(final int level) {

            }

            @Override
            public void onVideoStatusChange(final int videoStatus) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(videoStatus==VideoStatus.VIDEO_STATUS_NORMAL.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"网络正常",Toast.LENGTH_SHORT).show();
                        }else if(videoStatus==VideoStatus.VIDEO_STATUS_LOW_AS_LOCAL_BW.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"本地网络不稳定",Toast.LENGTH_SHORT).show();
                        }else if(videoStatus==VideoStatus.VIDEO_STATUS_LOW_AS_LOCAL_HARDWARE.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"系统忙，视频质量降低",Toast.LENGTH_SHORT).show();
                        }else if(videoStatus==VideoStatus.VIDEO_STATUS_LOW_AS_REMOTE.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"对方网络不稳定",Toast.LENGTH_SHORT).show();
                        }else if(videoStatus==VideoStatus.VIDEO_STATUS_NETWORK_ERROR.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"网络不稳定，请稍候",Toast.LENGTH_SHORT).show();
                        }else if(videoStatus==VideoStatus.VIDEO_STATUS_LOCAL_WIFI_ISSUE.getStatus()){
                            Toast.makeText(PushVideoActivity.this,"WiFi信号不稳定",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        if (mVideoServiceConnection != null) {
//            unbindService(mVideoServiceConnection);
//        }
//        videoFragment.onVideoDataSourceChange(mVideoInfos);
    }

    //视频回拨检测，如果视频已存在，就直接跳入视频界面
    @Override
    protected void onResume() {
        super.onResume();
        if (videoFragment.getVideoView()) {
            Log.d("BBBBBBBB","qwertyuio");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
            if (videoFragment.isAdded()) {
                fm.beginTransaction().hide(callFragment).show(videoFragment).commitAllowingStateLoss();
            } else {
                fm.beginTransaction().add(R.id.pushvideo_frame,
                        videoFragment).hide(callFragment).commitAllowingStateLoss();
            }
        }
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(nonRoot);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pushvideo;
    }

    //call界面接听操作
    @Override
    public void startVideo() {
        Log.d("BBBBBBBB",pushBean.getData().getMeetngno()+"+"+pushBean.getData().getMeetingpwd());
            NemoSDK.getInstance().makeCall(pushBean.getData().getMeetngno(), pushBean.getData().getMeetingpwd());
    }

    //call界面挂断操作！！！这个界面是未拨通的界面，拨通之后不跳转到call界面，因此无需对视频操作。
    @Override
    public void againVideo() {
        Log.d("BBBBBBBB","已经走过了444");
        if (!videoFragment.getVideoView()) {
            finish();
        }
    }

    //关闭监听，同时关闭推送超时医生路径
    @Override
    protected void onDestroy() {
        NemoSDK.getInstance().setNemoSDKListener(null);
        super.onDestroy();
        activity = null;
    }

    //输入法关闭
    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    //跳转地图操作
    @Override
    public void moveVideo() {
//        videoFragment.releaseResource();
//        moveTaskToBack(true);
//        EventBus.getDefault().postSticky(mVideoInfos);
//        Intent intent = new Intent(this, FloatVideoWindowService.class);//开启服务显示悬浮框
//        bindService(intent, mVideoServiceConnection, Context.BIND_AUTO_CREATE);
        EventBus.getDefault().postSticky(pushBean);
        Intent intent=new Intent(PushVideoActivity.this,MainActivity.class);
        intent.putExtra("PushVideo",true);
        startActivity(intent);
    }

    @Override
    public void moveSick() {
        Intent intent = new Intent(getContext(), SickActivity.class);
        intent.putExtra("sickid", pushBean.getData().getPatientid());
        startActivity(intent);
    }

//    ServiceConnection mVideoServiceConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            // 获取服务的操作对象
//            FloatVideoWindowService.MyBinder binder = (FloatVideoWindowService.MyBinder) service;
//            binder.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//        }
//    };

    //三个状态
    public enum VideoStatus {
        VIDEO_STATUS_NORMAL(0), VIDEO_STATUS_LOW_AS_LOCAL_BW(1), VIDEO_STATUS_LOW_AS_LOCAL_HARDWARE(2), VIDEO_STATUS_LOW_AS_REMOTE(3),VIDEO_STATUS_NETWORK_ERROR(4),VIDEO_STATUS_LOCAL_WIFI_ISSUE(5);
        private int status;

        private VideoStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    public interface CallListener {

        void onVideoDataSourceChange(List<VideoInfo> videoInfos);

    }

}
