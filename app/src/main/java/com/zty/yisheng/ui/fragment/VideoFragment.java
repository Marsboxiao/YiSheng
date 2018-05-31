package com.zty.yisheng.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.log.L;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ainemo.sdk.otf.NemoSDK;
import com.ainemo.sdk.otf.NemoSDKListener;
import com.ainemo.sdk.otf.RosterWrapper;
import com.ainemo.sdk.otf.VideoInfo;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.common.listener.VideoOnClick;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.view.VideoGroupView;
import com.zty.yisheng.model.bean.PushBean;
import com.zty.yisheng.model.bean.SDocIsanswerBean;
import com.zty.yisheng.model.bean.SDocOpenAppBean;
import com.zty.yisheng.presenter.contract.PushVideoContract;
import com.zty.yisheng.presenter.presenter.PushVideoPresenter;
import com.zty.yisheng.ui.activity.PushVideoActivity;
import com.zty.yisheng.ui.activity.SickActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.POWER_SERVICE;
import static android.view.View.VISIBLE;

/**
 * Created by 92915 on 2018/4/27.
 */

public class VideoFragment extends BaseFragment<PushVideoPresenter> implements PushVideoContract.View,PushVideoActivity.CallListener {

    @BindView(R.id.video_hangup)
    ImageView videoHangup;
    @BindView(R.id.video_down)
    ImageView videoDown;
    @BindView(R.id.video_sick)
    LinearLayout videoSick;
    @BindView(R.id.video_suspension)
    LinearLayout videoSuspension;
    @BindView(R.id.video_videobutton)
    Button videoVideobutton;
    @BindView(R.id.video_video)
    LinearLayout videoVideo;
    @BindView(R.id.video_view)
    VideoGroupView mVideoView;
    @BindView(R.id.view_contraller)
    LinearLayout viewContraller;
    @BindView(R.id.video_loading)
    LinearLayout videoLoading;
    private boolean videoMute = true;
    private boolean videoCont = false;
    private PowerManager.WakeLock mWakeLock;
    private VideoOnClick videoOnClick;
    private String docid;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(VideoFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        docid= SharePreUtil.getPrefString(YiShengApplication.getInstance(),"docid","111");
    }

    //视频界面不锁屏
    @Override
    public void onResume() {
        super.onResume();
        PowerManager mPowerManager = (PowerManager) getContext().getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                PowerManager.ON_AFTER_RELEASE,"TAG");
        mWakeLock.acquire();
    }

    //解除视频界面不锁屏
    @Override
    public void onPause() {
        super.onPause();
        if(null != mWakeLock){
            mWakeLock.release();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        videoOnClick= (VideoOnClick) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //跳转患者信息
    @OnClick(R.id.video_sick)
    public void setVideoSick(){
        videoOnClick.moveSick();
    }

    //开启摄像头
    @OnClick(R.id.video_video)
    public void setVideoVideo(){
        videoMute = !videoMute;
        setVideoState(videoMute);
        NemoSDK.getInstance().setVideoMute(videoMute);
    }

    //跳转地图
    @OnClick(R.id.video_suspension)
    public void setVideoSuspension(){
        videoOnClick.moveVideo();
    }

    //挂断
    @OnClick(R.id.video_hangup)
    public void setVideoHangup(){
        mPersenter.getStateAttribute(docid,"0");
        NemoSDK.getInstance().hangup();
    }

    //缩小菜单空间
    @OnClick(R.id.video_down)
    public void setVideoDown(){
        if (videoCont) {
            viewContraller.setVisibility(View.GONE);
            videoCont = false;
        } else {
            viewContraller.setVisibility(View.VISIBLE);
            videoCont = true;
        }
    }

    //显示视频
    public void showVideContainer() {
        if (videoLoading != null && mVideoView != null) {
            videoLoading.setVisibility(View.GONE);
            mVideoView.setVisibility(VISIBLE);
        }
    }

    //显示等待
    public void showOutgoingContainer() {
        if (videoLoading != null && mVideoView != null) {
            videoLoading.setVisibility(VISIBLE);
            mVideoView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (null == mVideoView) {
            return;
        }

        if (!hidden) {
            mVideoView.requestLocalFrame();
        } else {
            mVideoView.stopLocalFrameRender();
        }
    }

    @Override
    protected void initBaidu() {

    }

    @Override
    public void startOpenApp(SDocOpenAppBean sDocOpenAppBean) {

    }

    @Override
    public void againOpenApp(int code) {

    }

    @Override
    public void startState(SDocIsanswerBean sDocIsanswerBean) {

    }

    @Override
    public void againState(int code) {
        mPersenter.getStateAttribute(docid,"1");
        Log.d("BBBBBBBB","1");
    }

    //装载视频
    @Override
    public void onVideoDataSourceChange(List<VideoInfo> videoInfos) {
        if (mVideoView != null) {
            mVideoView.setLayoutInfos(videoInfos);
        }
        mPersenter.getStateAttribute(docid,"1");
    }

    //视频关闭或者开启
    private void setVideoState(boolean videoMute) {
        mVideoView.setVideoMute(videoMute);
        if (this.videoMute) {
            videoVideobutton.setBackground(getResources().getDrawable(R.drawable.off));
        } else {
            videoVideobutton.setBackground(getResources().getDrawable(R.drawable.on));
        }
    }

    //清空视频
    public void releaseResource() {
        mVideoView.stopRender();
        mVideoView.destroy();
        if (videoMute) {
            videoMute = false;
            setVideoState(videoMute);
        }
    }

    //判断视频界面是否存在，如果不存在，呼入会变成call界面
    public Boolean getVideoView(){
        if (mVideoView != null) {
            return true;
        } else {
            return false;
        }
    }

}
