package com.zty.yisheng.common.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ainemo.sdk.otf.OpenGLTextureView;


public class VideoCell extends OpenGLTextureView {
    private int participantId;
    private Context mContext;

    public VideoCell(Context context) {
        super(context);
    }

    public VideoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getParticipantId() {
        return participantId;
    }
}
