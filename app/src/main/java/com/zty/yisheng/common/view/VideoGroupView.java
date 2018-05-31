package com.zty.yisheng.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.log.L;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ainemo.sdk.otf.NemoSDK;
import com.ainemo.sdk.otf.OpenGLTextureView;
import com.ainemo.sdk.otf.VideoInfo;
import com.zty.yisheng.R;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VideoGroupView extends ViewGroup {
    private static final String TAG = VideoGroupView.class.getSimpleName();
    private Context mContext;
    public static final int LOCAL_VIEW_ID = 99;
    private static final int CELL_VIDEO_PADDING = 15;
    private OpenGLTextureView localVideoView;
    private int density;

    public boolean isMicMute() {
        return micMute;
    }

    public void setMicMute(boolean micMute) {
        this.micMute = micMute;
        if (cellStateView != null) {
            cellStateView.setMuteAudio(micMute);
        }
    }

    //语音
    public void setSwitchCallMode(boolean audioMode) {
        L.i(TAG, "chenshuliangclick" + audioMode);
        if (cellStateView != null) {
            L.i(TAG, "setSwitchCallMode 12" + audioMode);
            cellStateView.setSwitchCallMode(audioMode,"MuteByUser",null);
        }
    }

    public void setVideoMute(boolean videoMute) {
        L.i(TAG, "setVideoMute mVideoMute 5" + videoMute);
        if (cellStateView != null) {
            L.i(TAG, "setVideoMute mVideoMute 6" + videoMute);
            cellStateView.setVideoMute(videoMute, null,null);
        }
    }

    private boolean micMute;
    private List<VideoCell> mVideoViews = new CopyOnWriteArrayList<>();
    private List<CellStateView> mCellStateViews = new CopyOnWriteArrayList<>();
    private Handler handler = new Handler();
    private Runnable drawVideoFrameRunnable = new Runnable() {
        @Override
        public void run() {
            for (VideoCell videoCell : mVideoViews) {
                videoCell.requestRender();
                L.i(TAG, "getSourceID:" + videoCell.getSourceID());
            }
            requestRender();
        }
    };

    private Runnable drawLocalVideoFrameRunnable = new Runnable() {
        @Override
        public void run() {
            localVideoView.requestRender();
            requestLocalVideoRender();
        }
    };
    private int mCellPadding;
    private CellStateView cellStateView;

    public VideoGroupView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public VideoGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    int gap = density * 5;

    private void init() {
        mCellPadding = (int) getResources().getDimension(R.dimen.local_cell_pandding);
        localVideoView = new OpenGLTextureView(getContext());
        localVideoView.setSourceID(NemoSDK.getInstance().getLocalVideoStreamID());
        localVideoView.setContent(false);
        addView(localVideoView);

        cellStateView = new CellStateView(mContext);
        addView(cellStateView);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        density = (int) displayMetrics.density;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int size = Math.min(mVideoViews.size(), 1); //最多取5个view显示
        L.i(TAG, "layout cell count " + size);

        if (size == 0) {
            localVideoView.layout(l, t, r, b);
            localVideoView.bringToFront();
            cellStateView.layout(l, t, r, b);
            cellStateView.setLocalFullScreen(true);
            cellStateView.bringToFront();
        } else {
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    L.i(TAG, "layout full screen child");
                    mVideoViews.get(0).layout(l, t, r, b);  //取第一个view全屏显示
                    mCellStateViews.get(0).layout(l, t, r, b);
                    mCellStateViews.get(0).bringToFront();
                } else {
                    L.i(TAG, "layout item at " + i);
                    mVideoViews.get(i).layout(0,0,0,0);
                    mVideoViews.get(i).bringToFront();
                    mCellStateViews.get(i).layout(0,0,0,0);
                    mCellStateViews.get(i).bringToFront();
                }
            }
            L.i(TAG, "layout local item");
            localVideoView.layout(0,0,0,0);
            cellStateView.setLocalFullScreen(false);
            localVideoView.bringToFront();
            cellStateView.layout(0,0,0,0);
            cellStateView.bringToFront();
        }
    }

    private boolean isNoVideoState(VideoInfo layoutInfo) {
        if (layoutInfo != null) {
            L.i("isNoVideoState lsx layoutInfo.getLayoutVideoState():" + layoutInfo.getLayoutVideoState());
            return layoutInfo.getLayoutVideoState().equals("kLayoutStateNoDecoder") ||
                    layoutInfo.getLayoutVideoState().equals("kLayoutStateNoBandwidth") ||
                    layoutInfo.getLayoutVideoState().equals("kLayoutStateRequesting");
        }
        return false;
    }

    public void setLayoutInfos(List<VideoInfo> videoInfos) {

        L.i(TAG, "video info size is " + videoInfos.toString());

        List<VideoCell> toDel = new CopyOnWriteArrayList<>();
        List<CellStateView> cellStateViewList = new CopyOnWriteArrayList<>();

        l:
        for (int i = 0; i < mVideoViews.size(); i++) {
            VideoCell videoCell = mVideoViews.get(i);
            CellStateView cStateView = mCellStateViews.get(i);
            for (VideoInfo mVideoInfo : videoInfos) {
                if (mVideoInfo.getParticipantId() == videoCell.getParticipantId()) {
                    videoCell.setSourceID(mVideoInfo.getDataSourceID());
                    videoCell.setContent(mVideoInfo.isContent());
                    cStateView.setMuteAudio(mVideoInfo.isAudioMute());//是否关闭语音
                    cStateView.setSwitchCallMode(mVideoInfo.getLayoutVideoState().equals("kLayoutStateAudioOnly") || mVideoInfo.getLayoutVideoState().equals("kLayoutStateReceivedAudioOnly"),"MuteByUser",mVideoInfo.getRemoteName());//是否关闭语音11


                    L.i(TAG, "chenshuliang33" + mVideoInfo.getRemoteName());

                    L.i(TAG, "chenshuliang11" + mVideoInfo.getVideoMuteReason());
                    cStateView.setVideoMute(mVideoInfo.isVideoMute(), mVideoInfo.getVideoMuteReason(),mVideoInfo.getRemoteName());//显示视频
                    cStateView.setNoVideMute(isNoVideoState(mVideoInfo), mVideoInfo.getLayoutVideoState(), mVideoInfo.getVideoMuteReason(),mVideoInfo.getRemoteName());//关闭视频
                    continue l;
                }
            }
            cellStateViewList.add(cStateView);
            toDel.add(videoCell);
            removeView(videoCell);
            removeView(cStateView);
        }
        mVideoViews.removeAll(toDel);
        mCellStateViews.removeAll(cellStateViewList);

        for (int i = 0; i < videoInfos.size(); i++) {
            VideoInfo videoInfo = videoInfos.get(i);

            if (i < mVideoViews.size()) {
                if (videoInfo.getParticipantId() == mVideoViews.get(i).getParticipantId()) {
                    continue;
                } else {
                    int position = -1;
                    VideoCell cell = null;
                    for (int j = 0; j < mVideoViews.size(); j++) {
                        VideoCell jv = mVideoViews.get(j);
                        if (jv.getParticipantId() == videoInfo.getParticipantId()) {
                            position = j;
                            cell = jv;
                            break;
                        }
                    }

                    if (cell == null) {
                        VideoCell toAdd = new VideoCell(getContext());
                        toAdd.setParticipantId(videoInfo.getParticipantId());
                        toAdd.setSourceID(videoInfo.getDataSourceID());
                        L.i(TAG, "add view");
                        mVideoViews.add(toAdd);
                        CellStateView cellStateView = new CellStateView(mContext);
                        cellStateView.setMuteAudio(videoInfo.isAudioMute());//是否关闭语音

                        cellStateView.setSwitchCallMode(videoInfo.getLayoutVideoState().equals("kLayoutStateAudioOnly") || videoInfo.getLayoutVideoState().equals("kLayoutStateReceivedAudioOnly"),"MuteByUser",videoInfo.getRemoteName());
                        L.i(TAG, "chenshuliang33" + videoInfo.getRemoteName());


                        L.i(TAG, "lsx videoInfo.getLayoutVideoState()::" + videoInfo.getLayoutVideoState() + ":getVideoMuteReason:" + videoInfo.getVideoMuteReason());
                        cellStateView.setVideoMute(videoInfo.isVideoMute(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//显示视频
                        cellStateView.setNoVideMute(isNoVideoState(videoInfo), videoInfo.getLayoutVideoState(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//关闭视频
                        L.i(TAG, "lsx isNoVideoState:::" + isNoVideoState(videoInfo));
                        mCellStateViews.add(cellStateView);
                        addView(toAdd);
                        addView(cellStateView);
                    } else {
                        Collections.swap(mVideoViews, i, position);
                        Collections.swap(mCellStateViews, i, position);
                    }
                }
            } else {
                VideoCell toAdd = new VideoCell(getContext());
                toAdd.setParticipantId(videoInfo.getParticipantId());
                toAdd.setSourceID(videoInfo.getDataSourceID());
                L.i(TAG, "add view");
                mVideoViews.add(toAdd);
                CellStateView cellStateView = new CellStateView(mContext);
                cellStateView.setMuteAudio(videoInfo.isAudioMute());//是否关闭语音

                cellStateView.setSwitchCallMode(videoInfo.getLayoutVideoState().equals("kLayoutStateAudioOnly") || videoInfo.getLayoutVideoState().equals("kLayoutStateReceivedAudioOnly"),"MuteByUser",videoInfo.getRemoteName());
                L.i(TAG, "chenshuliang33" + videoInfo.getRemoteName());

                L.i(TAG, "lsx videoInfo.getLayoutVideoState()::" + videoInfo.getLayoutVideoState() + ":getVideoMuteReason:" + videoInfo.getVideoMuteReason());
                cellStateView.setVideoMute(videoInfo.isVideoMute(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//显示视频
                cellStateView.setNoVideMute(isNoVideoState(videoInfo), videoInfo.getLayoutVideoState(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//关闭视频
                L.i(TAG, "lsx isNoVideoState:::" + isNoVideoState(videoInfo));
                mCellStateViews.add(cellStateView);
                addView(toAdd);
                addView(cellStateView);
            }
        }

//        k:
//        for (VideoInfo videoInfo : videoInfos) {
//            for (VideoCell videoCell : mVideoViews) {
//                if (videoCell.getParticipantId() == videoInfo.getParticipantId()) {
//                    continue k;
//                }
//            }
//            VideoCell toAdd = new VideoCell(getContext());
//            toAdd.setParticipantId(videoInfo.getParticipantId());
//            toAdd.setSourceID(videoInfo.getDataSourceID());
//            L.i(TAG, "add view");
//            mVideoViews.add(toAdd);
//            CellStateView cellStateView = new CellStateView(mContext);
//            cellStateView.setMuteAudio(videoInfo.isAudioMute());//是否关闭语音
//            L.i(TAG, "lsx videoInfo.getLayoutVideoState()::" + videoInfo.getLayoutVideoState() + ":getVideoMuteReason:" + videoInfo.getVideoMuteReason());
//            cellStateView.setVideoMute(videoInfo.isVideoMute(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//显示视频
//            cellStateView.setNoVideMute(isNoVideoState(videoInfo), videoInfo.getLayoutVideoState(), videoInfo.getVideoMuteReason(),videoInfo.getRemoteName());//关闭视频
//            L.i(TAG, "lsx isNoVideoState:::" + isNoVideoState(videoInfo));
//            mCellStateViews.add(cellStateView);
//            addView(toAdd);
//            addView(cellStateView);
//        }

        requestRender();
        requestLayout();
    }

    public void destroy() {
        destroyDrawingCache();
        mVideoViews.clear();
        mCellStateViews.clear();
        handler.removeCallbacksAndMessages(null);
    }

    public void stopRender() {
        handler.removeCallbacks(drawVideoFrameRunnable);
    }

    private void requestRender() {
        handler.removeCallbacks(drawVideoFrameRunnable);
        if (getVisibility() == VISIBLE)
            handler.postDelayed(drawVideoFrameRunnable, 1000 / CELL_VIDEO_PADDING);
    }

    public void requestLocalFrame() {
        handler.removeCallbacks(drawLocalVideoFrameRunnable);
        requestLocalVideoRender();
    }

    public void stopLocalFrameRender() {
        handler.removeCallbacks(drawLocalVideoFrameRunnable);
    }

    private void requestLocalVideoRender() {
        if (getVisibility() == VISIBLE)
            handler.postDelayed(drawLocalVideoFrameRunnable, 1000 / CELL_VIDEO_PADDING);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            View child = getChildAt(childIndex);
            child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED));
        }
    }
}
