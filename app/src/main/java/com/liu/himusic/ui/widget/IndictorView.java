package com.liu.himusic.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;


import com.liu.himusic.R;
import com.liu.himusic.event.MusicPauseEvent;
import com.liu.himusic.event.MusicStartEvent;
import com.liu.himusic.event.MusicStopEvent;
import com.liu.himusic.ui.activity.MusicPlayerActivity;
import com.liu.himusic.ui.adapter.MusicPagerAdapter;
import com.liucj.lib_common.livedata.LiveDataBus;
import com.lzx.starrysky.SongInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * 音乐播放页面唱针布局
 */
public class IndictorView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;

    /*
     * view相关
     */
    private ImageView mImageView;
    private ViewPager mViewPager;
    private MusicPagerAdapter mMusicPagerAdapter;
    /*
     * data
     */
    private SongInfo mSongInfo; //当前播放歌曲
    private List<SongInfo> mQueue; //播放队列

    private ObjectAnimator discAnimation, needleAnimation;//自定义指针和唱盘

    public IndictorView(Context context) {
        this(context, null);
    }

    public IndictorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndictorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initData();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initData() {
        EventBus.getDefault().register(this);
        mQueue = SongPlayManager.getInstance().getSongList();
        mSongInfo = SongPlayManager.getInstance().getCurrentSong();
    }

    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.indictor_view, this);
        mImageView = rootView.findViewById(R.id.tip_view);
        mViewPager = rootView.findViewById(R.id.view_pager);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mMusicPagerAdapter = new MusicPagerAdapter(mQueue, mContext, null);
        mViewPager.setAdapter(mMusicPagerAdapter);
        showLoadView(false);
        //要在UI初始化完，否则会多一次listener响应
        mViewPager.addOnPageChangeListener(this);
        setAnimations();
    }

    public void upDataInit() {
        mQueue = SongPlayManager.getInstance().getSongList();
        mSongInfo = SongPlayManager.getInstance().getCurrentSong();
        mMusicPagerAdapter.notifyDataSetChanged();
    }

    //动画设置
    private void setAnimations() {
        needleAnimation = ObjectAnimator.ofFloat(mImageView, "rotation", -20, 0);
        mImageView.setPivotX(0);
        mImageView.setPivotY(0);
        needleAnimation.setDuration(800);
        needleAnimation.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                showPlayView();
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                needleAnimationEnd();
                showPauseView();
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
        }
    }

    public MusicPagerAdapter getMusicPagerAdapter() {
        return mMusicPagerAdapter;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void needleAnimationStart() {
        if (needleAnimation != null && !needleAnimation.isStarted()) {
            needleAnimation.start();
        }
    }

    public void needleAnimationEnd() {
        if (needleAnimation != null) {
            needleAnimation.reverse();
            needleAnimation.end();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayMusicEvent(MusicStartEvent event) {
        //更新activity为播放状态
        showPlayView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicPauseEvent(MusicPauseEvent event) {
        //更新activity为暂停状态
        needleAnimationEnd();
        showPauseView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicStopEvent(MusicStopEvent event) {
        //更新activity为停止状态
        needleAnimationEnd();
        showStopView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    private void showLoadView(boolean isSmooth) {
        mViewPager.setCurrentItem(mQueue.indexOf(mSongInfo), isSmooth);
    }

    public void showPauseView() {
        Animator anim = mMusicPagerAdapter.getAnim(mViewPager.getCurrentItem());
        if (anim != null) anim.pause();
    }

    private void showStopView() {
        Animator anim = mMusicPagerAdapter.getAnim(mViewPager.getCurrentItem());
        if (anim != null) anim.end();
    }

    public void showPlayView() {
        needleAnimationStart();
        Animator anim = mMusicPagerAdapter.getAnim(mViewPager.getCurrentItem());
        if (anim != null) {
            if (anim.isPaused()) {
                anim.resume();
            } else {
                anim.start();
            }
        }
    }

    public interface onPageChangeListener {
        void onPageSelected(int position);
    }

    private onPageChangeListener listener;

    public void setOnPageChangeListener(onPageChangeListener listener) {
        this.listener = listener;
    }
}
