package com.liu.himusic.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liu.himusic.R;
import com.liucj.lib_common.view.PPImageView;
import com.youth.banner.util.BannerUtils;

public class BottomSongBarLeftView extends RelativeLayout {
    private ObjectAnimator animator;
    private PPImageView ivCover;

    public BottomSongBarLeftView(Context context) {
        this(context, null);
    }

    public BottomSongBarLeftView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomSongBarLeftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.bottom_song_bar_image, this);
        ivCover = view.findViewById(R.id.iv_cover);
        animator = ObjectAnimator.ofFloat(view, View.ROTATION.getName(), 0f, 360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
//        setPlaying(true);
    }

    public ObjectAnimator getAnimator(){
        return animator;
    }

    public void start() {
        setPlaying(true);
    }

    public void paused() {
        setPlaying(false);
    }

    public void end() {
        if (!animator.isStarted() || !animator.isRunning()) {
            animator.end();
        }
    }

    public PPImageView getImageView() {
        return ivCover;
    }

    // 更新播放状态
    public void setPlaying(boolean isPlaying) {
        if (isPlaying) {
            if (!animator.isRunning()) {
                animator.start();
            } else {
                animator.resume();
            }
        } else {
            if (!animator.isStarted() || !animator.isRunning()) {
                animator.cancel();
            }
            animator.pause();
        }
    }
}
