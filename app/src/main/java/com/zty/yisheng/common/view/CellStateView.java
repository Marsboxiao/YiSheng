package com.zty.yisheng.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.log.L;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.yisheng.R;


/**
 * Created by lsx on 28/08/2017.
 */
public class CellStateView extends ViewGroup {

    private static final java.lang.String TAG = CellStateView.class.getSimpleName();
    private ImageView mAudioMuteImage;
    private boolean audioMute = false;
    private ImageView mAudioSmallMute;
    private Context mContext;
    private boolean mVideoMute = false;
    private boolean mIsNoVideo = false;
    private boolean mIsLocalFullScreen = true;
    private boolean mSwitchCallMode = false;//语音
    private boolean audioOnlyState = false;
    private boolean observerMode = false;
    private int ICON_SIZE_W = 35;
    private int ICON_SIZE_H = 38;
    private int ICON_SIZE_W_FS = 72;
    private int ICON_SIZE_H_FS = 58;
    private int ICON_SIZE_W_FS_AUDIO = 20;
    private int ICON_SIZE_H_FS_AUDIO = 24;
    private int ICON_SIZE_W_AUDIO = 15;
    private int ICON_SIZE_H_AUDIO = 16;
    private float TEXT_NAME_SIZE = 13;
    private float TEXT_NAME_SIZE_FS = 17;
    private CachedLayoutPosition params = null;

    private int mScreenWidth;
    private int density;
    private ImageView mCellStateBg;
    private TextView mProfileName;
    private TextView mNoVideoText;
    private ImageView mNoVideoImage;
    private String mMuteReason;
    private String mAutoCallMode;
    private String mLayoutVideoState;
    private TextView mCellVideoMuteText;
    private String mUserName;

    public CellStateView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public void setMuteAudio(boolean mute) {

        if (audioMute == mute) {
            return;
        }
        this.audioMute = mute;

        if (mAudioMuteImage != null)
            mAudioMuteImage.setVisibility(audioMute ? VISIBLE : GONE);

        if (mAudioSmallMute != null)
            mAudioSmallMute.setVisibility(audioMute ? VISIBLE : GONE);
    }


    //语音模式
    public void setSwitchCallMode(boolean switchCallMode,String autoCallMode,String UserName) {
        this.mSwitchCallMode = switchCallMode;
        this.mMuteReason = autoCallMode;
        this.mUserName=UserName;
        Log.i(TAG, "CellStateView  setSwitchCallMode: switchCallMode:" + switchCallMode+"==:autoCallMode"+autoCallMode);
        mCellStateBg.setVisibility(mSwitchCallMode ? VISIBLE : GONE);
        mNoVideoText.setVisibility(mSwitchCallMode ? VISIBLE : GONE);

        mNoVideoText.measure(0, 0);
        mProfileName.setVisibility(mSwitchCallMode ? VISIBLE : GONE);
        mProfileName.measure(0, 0);
        mNoVideoImage.setVisibility(mSwitchCallMode?VISIBLE:GONE);

    }

    public boolean getSwitchMode() {
        return mSwitchCallMode;
    }

    public void setVideoMute(boolean videoMute, String videoMuteReason,String UserName) {
        this.mMuteReason = videoMuteReason;
        this.mVideoMute = videoMute;
        this.mUserName= UserName;
        L.i(TAG, "setVideoMute mVideoMute==" + mVideoMute + "==:videoMuteReason:" + videoMuteReason);
        mCellStateBg.setVisibility(mVideoMute ? VISIBLE : GONE);
        mNoVideoText.setVisibility(mVideoMute ? VISIBLE : GONE);
        mNoVideoText.measure(0, 0);
        mNoVideoImage.setVisibility(mVideoMute?VISIBLE:GONE);

    }

    public void setNoVideMute(boolean isNoVideo, String layoutVideoState, String videoMuteReason,String UserName) {
        this.mIsNoVideo = isNoVideo;
        this.mLayoutVideoState = layoutVideoState;
        this.mMuteReason = videoMuteReason;
        this.mUserName= UserName;
        mCellStateBg.setVisibility(isNoVideo ? VISIBLE : GONE);
        mNoVideoText.setVisibility(isNoVideo ? VISIBLE : GONE);
        mNoVideoText.measure(0, 0);

        mNoVideoImage.setVisibility(isNoVideo?VISIBLE:GONE);
    }

    @SuppressLint("WrongCall")
    protected void layoutSelf() {
        if (params != null) {
            onLayout(true, params.getL(), params.getT(), params.getR(), params.getB());
            invalidate();
        } else {
            requestLayout();
        }
    }

    public void setLocalFullScreen(boolean isLocalFullScreen) {
        mIsLocalFullScreen = isLocalFullScreen;
    }

    public CellStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public CellStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (params == null) {
            params = new CachedLayoutPosition(l, t, r, b);
        } else {
            params.setVals(l, t, r, b);
        }

        int width = r - l;
        int height = b - t;
        int gap = density * 5;
        int fullgap = density * 10;

        int iconWidth, iconHeight;
        boolean isFullScreen = width > mScreenWidth / 5;
        L.i(TAG, "isFullScreen:" + isFullScreen + "audioMute:" + audioMute);
        if (isFullScreen) {
            mNoVideoText.setTextSize(17);
            mProfileName.setTextSize(17);
            if (!mIsLocalFullScreen) {
                mNoVideoText.setTextSize(10);
                mProfileName.setTextSize(10);
            }
        } else {
            mNoVideoText.setTextSize(10);
            mProfileName.setTextSize(10);
        }
        if (audioMute) {
            if (isFullScreen) {
                iconWidth = ICON_SIZE_W_FS;
                iconHeight = ICON_SIZE_H_FS;
                if (!mIsLocalFullScreen) {
                    iconWidth = ICON_SIZE_W;
                    iconHeight = ICON_SIZE_H;
                }
            } else {
                iconWidth = ICON_SIZE_W;
                iconHeight = ICON_SIZE_H;
            }
            int maRight = gap + iconWidth;
            int bottom = gap + iconHeight;
            if (isFullScreen) {
                mAudioMuteImage.setVisibility(VISIBLE);
                mAudioSmallMute.setVisibility(GONE);
                mAudioMuteImage.layout(gap, gap, maRight, bottom);
            } else {
                mAudioMuteImage.setVisibility(GONE);
                mAudioSmallMute.setVisibility(VISIBLE);
                mAudioSmallMute.layout(gap, gap, maRight, bottom);
            }
        }
        L.i(TAG, "setVideoMute mVideoMute 1" + mVideoMute + "mIsNoVideo::" + mIsNoVideo);

        L.i(TAG, "setSwitchCallMode11223" + mSwitchCallMode);
        L.i(TAG, "setSwitchCallMode mVideoMute 1" + mMuteReason);
        if (mVideoMute) {
            L.i(TAG, "setVideoMute mVideoMute 2" + mVideoMute);
            mCellStateBg.setVisibility(VISIBLE);
            mNoVideoText.setVisibility(VISIBLE);
            mProfileName.setVisibility(VISIBLE);
            mNoVideoImage.setVisibility(VISIBLE);
            if (!TextUtils.isEmpty(mMuteReason)) {
                if (mMuteReason.equals("MuteByUser")) {
                    mNoVideoText.setText(getResources().getText(R.string.MuteByUser));
                } else if (mMuteReason.equals("MuteByNoInput")) {
                    mNoVideoText.setText(getResources().getText(R.string.MuteByNoInput));
                } else if (mMuteReason.equals("MuteByBWLimit")) {
                    mNoVideoText.setText(getResources().getText(R.string.MuteByBWLimit));
                } else if (mMuteReason.equals("MuteByConfMgmt")) {
                    mNoVideoText.setText(getResources().getText(R.string.MuteByConfMgmt));
                }
            }
            L.i(TAG, "setSwitchCallMode112231" + mSwitchCallMode);
            mProfileName.setText(mUserName);
            if(mSwitchCallMode){
                L.i(TAG, "setSwitchCallMode112231" + mSwitchCallMode);
                mCellStateBg.setVisibility(VISIBLE);
                mNoVideoText.setVisibility(VISIBLE);
                mProfileName.setVisibility(VISIBLE);
                mNoVideoImage.setVisibility(VISIBLE);
                mNoVideoText.setText(getResources().getString(R.string.switch_call_in));
                mProfileName.setText(mUserName);
                if (!TextUtils.isEmpty(mMuteReason)) {

                    if (mMuteReason.equals("MuteByUser")) {
                        mNoVideoText.setText(getResources().getText(R.string.switch_call_in));
                        L.i(TAG, "setSwitchCallMode112231" + mUserName);
                        mProfileName.setText(mUserName);
                    }

                }

            }
        } else if (mSwitchCallMode) {
            L.i(TAG, "setSwitchCallMode1122" + mSwitchCallMode);
            mCellStateBg.setVisibility(VISIBLE);
            mNoVideoText.setVisibility(VISIBLE);
            mProfileName.setVisibility(VISIBLE);
            mNoVideoImage.setVisibility(VISIBLE);
            mNoVideoText.setText(getResources().getString(R.string.switch_call_in));
            if (!TextUtils.isEmpty(mMuteReason)) {
                if (mMuteReason.equals("MuteByUser")) {
                    mNoVideoText.setText(getResources().getText(R.string.switch_call_in));
                    mProfileName.setText(mUserName);
                }
            }


        } else if (mIsNoVideo) {
            if (mLayoutVideoState.equals("kLayoutStateNoBandwidth")) {
                mNoVideoImage.setVisibility(GONE);
                mNoVideoText.setText(getResources().getString(R.string.call_no_video));
            } else if (mLayoutVideoState.equals("kLayoutStateNoDecoder")) {
                mNoVideoText.setText(getResources().getString(R.string.call_video_mute));

            } else if (mLayoutVideoState.equals("kLayoutStateRequesting")) {
                mNoVideoImage.setVisibility(GONE);
                mNoVideoText.setText(getResources().getString(R.string.call_video_requesting));
            } else {
                if (!TextUtils.isEmpty(mMuteReason)) {

                    if (mMuteReason.equals("MuteByUser")) {
                        mNoVideoText.setText(getResources().getText(R.string.MuteByUser));
                    } else if (mMuteReason.equals("MuteByNoInput")) {
                        mNoVideoText.setText(getResources().getText(R.string.MuteByNoInput));
                    } else if (mMuteReason.equals("MuteByBWLimit")) {
                        mNoVideoText.setText(getResources().getText(R.string.MuteByBWLimit));
                    } else if (mMuteReason.equals("MuteByConfMgmt")) {
                        mNoVideoText.setText(getResources().getText(R.string.MuteByConfMgmt));
                    }
                }
            }
        } else {

            mNoVideoText.setText(getResources().getString(R.string.close_video));
        }

        mNoVideoText.measure(0, 0);
        mProfileName.measure(0,0);
        mNoVideoImage.measure(0,0);
        L.i(TAG, "lsx  width:" + (r - l) + ":height:" + (b - t) + ":text width:" + mNoVideoText.getMeasuredWidth() + "::text height::" + mNoVideoText.getMeasuredHeight());




        mNoVideoImage.layout((width / 2 - 30 * density)
                , (height - mProfileName.getMeasuredHeight() - mNoVideoText.getMeasuredHeight() -30 * density) / 2 - 10 * density
                , (width / 2 + 30 * density)
                , (height - mProfileName.getMeasuredHeight() - mNoVideoText.getMeasuredHeight() - 30 * density) / 2 + 50 * density);

        mNoVideoText.layout((width - mNoVideoText.getMeasuredWidth()) / 2
                , (height - mNoVideoText.getMeasuredHeight() + mProfileName.getMeasuredHeight() + 70 * density) / 2 + 10 * density
                , (width + mNoVideoText.getMeasuredWidth()) / 2
                , (height + mNoVideoText.getMeasuredHeight() + 70 * density + mProfileName.getMeasuredHeight()) / 2 + 10 * density);


        mProfileName.layout((width - mProfileName.getMeasuredWidth()) / 2, (height - mProfileName.getMeasuredHeight()) / 2 + 30 * density,
                (width + mProfileName.getMeasuredWidth()) / 2, (height + mProfileName.getMeasuredHeight()) / 2 + 30 * density);



        // mNoVideoText.layout((width - mNoVideoText.getMeasuredWidth()) / 2, (height - mNoVideoText.getMeasuredHeight()) / 2, r, b);

//        mProfileName.layout((width - mProfileName.getMeasuredWidth()) / 2, (height - mProfileName.getMeasuredHeight()) / 2, r, b);
//        mNoVideoText.layout((width - mNoVideoText.getMeasuredWidth()) / 2, (height - mNoVideoText.getMeasuredHeight()) / 2 + 30 * density,
//                (width + mNoVideoText.getMeasuredWidth()) / 2, (height + mNoVideoText.getMeasuredHeight()) / 2 + 30 * density);
        mCellStateBg.layout(0, 0, width, height);
    }

    private void initView() {
        View.inflate(mContext, R.layout.cell_state_view, this);
        mAudioMuteImage = (ImageView) findViewById(R.id.cell_state_audio);
        mAudioSmallMute = (ImageView) findViewById(R.id.cell_state_audio_small);
        mCellStateBg = (ImageView) findViewById(R.id.cell_state_bg);
        mProfileName = (TextView) findViewById(R.id.cell_state_user_profile_name);
        mNoVideoText = (TextView) findViewById(R.id.cell_no_video_text);
        //mNoVideoText.setText(getResources().getString(R.string.call_no_video));
        mNoVideoImage = (ImageView) findViewById(R.id.cell_state_no_video);
        mCellVideoMuteText = (TextView) findViewById(R.id.cell_video_mute_text);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        density = (int) displayMetrics.density;
    }

}
