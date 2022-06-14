package com.liu.himusic.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.liu.himusic.R;

import cn.jzvd.JzvdStd;

public class VideoPlayerItem extends JzvdStd {

    public VideoPlayerItem(Context context) {
        super(context);
    }

    public VideoPlayerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onPrepared() {
        super.onPrepared();
        if (screen == SCREEN_FULLSCREEN) {
            mediaInterface.setVolume(1f, 1f);
        } else {
            mediaInterface.setVolume(0f, 0f);
        }
    }

    @Override
    public void setScreenNormal() {
        super.setScreenNormal();
        if (mediaInterface != null)
            mediaInterface.setVolume(0f, 0f);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_jzstd_notitle;
    }

}