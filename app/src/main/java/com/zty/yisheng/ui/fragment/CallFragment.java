package com.zty.yisheng.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.YuvImage;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.common.listener.FragmentOnClick;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.model.bean.SDocIsanswerBean;
import com.zty.yisheng.model.bean.SDocOpenAppBean;
import com.zty.yisheng.presenter.contract.PushVideoContract;
import com.zty.yisheng.presenter.presenter.PushVideoPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 92915 on 2018/4/27.
 */

public class CallFragment extends BaseFragment<PushVideoPresenter> implements PushVideoContract.View {

    @BindView(R.id.call_time)
    TextView callTime;
    @BindView(R.id.call_hangup)
    FloatingActionButton callHangup;
    @BindView(R.id.call_answer)
    FloatingActionButton callAnswer;
    private String docid;
    private FragmentOnClick fragmentOnClick;
    private MediaPlayer mediaPlayer = null;
    private TimeCount time;
    private Boolean isCallAnswer=false;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(CallFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_call;
    }

    @Override
    protected void initView() {
        docid = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "docid", "");
        mPersenter.getOpenAppAttribute(docid);
        long times = Integer.valueOf(SharePreUtil.getPrefString(YiShengApplication.getInstance(), "time", "15")) * 1000;
        time = new TimeCount(times, 1000);
        time.start();
        try {
            AssetFileDescriptor fd = null;
            fd = getContext().getAssets().openFd("tishi.mp3");
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentOnClick = (FragmentOnClick) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override
    protected void initBaidu() {

    }

    @OnClick(R.id.call_hangup)
    public void setCallHangup() {
        fragmentOnClick.againVideo();
    }

    @OnClick(R.id.call_answer)
    public void setCallAnswer() {
        isCallAnswer=true;
        mediaPlayer.release();
        fragmentOnClick.startVideo();
    }

    @Override
    public void startOpenApp(SDocOpenAppBean sDocOpenAppBean) {

    }

    @Override
    public void againOpenApp(int code) {
        mPersenter.getOpenAppAttribute(docid);
    }

    @Override
    public void startState(SDocIsanswerBean sDocIsanswerBean) {

    }

    @Override
    public void againState(int code) {

    }

    class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onTick(long millisUntilFinished) {
            if (getActivity() != null) {
                if (millisUntilFinished < 10000) {
                    callTime.setText("00:0" + millisUntilFinished / 1000);
                } else {
                    callTime.setText("00:" + millisUntilFinished / 1000);
                }
            }
        }


        @Override
        public void onFinish() {
            if (!isCallAnswer) {
                setCallHangup();
            }
        }
    }


}
