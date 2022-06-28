package com.liu.himusic.ui.widget;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;


import com.liu.himusic.R;
import com.liu.himusic.ui.activity.MusicPlayerActivity;
import com.liu.himusic.ui.adapter.MusicPagerAdapter;
import com.liucj.lib_common.livedata.LiveDataBus;
import com.lzx.starrysky.SongInfo;

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
        initEvent((MusicPlayerActivity) context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initData() {
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
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //指定要播放的position
        SongPlayManager.getInstance().clickASong(mQueue.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                showPlayView();
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                showPauseView();
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAudioLoadEvent(AudioLoadEvent event) {
//        //更新viewpager为load状态
//        mAudioBean = event.mAudioBean;
//        showLoadView(true);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAudioPauseEvent(AudioPauseEvent event) {
//        //更新activity为暂停状态
//        showPauseView();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAudioStartEvent(AudioStartEvent event) {
//        //更新activity为播放状态
//        showPlayView();
//    }

    public void initEvent(Activity activity) {
        LiveDataBus.get().with("stop_music").observe((LifecycleOwner) mContext, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                //更新activity为暂停状态
                showPauseView();
            }
        });
        LiveDataBus.get().with("play_music").observe((LifecycleOwner) activity, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                //更新activity为播放状态
                showPlayView();
            }
        });
        LiveDataBus.get().with("pause_music").observe((LifecycleOwner) activity, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                //更新activity为暂停状态
                showPauseView();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        EventBus.getDefault().unregister(this);
    }

    private void showLoadView(boolean isSmooth) {
        mViewPager.setCurrentItem(mQueue.indexOf(mSongInfo), isSmooth);
    }

    private void showPauseView() {
        Animator anim = mMusicPagerAdapter.getAnim(mViewPager.getCurrentItem());
        if (anim != null) anim.pause();
    }

    private void showPlayView() {
        Animator anim = mMusicPagerAdapter.getAnim(mViewPager.getCurrentItem());
        if (anim != null) {
            if (anim.isPaused()) {
                anim.resume();
            } else {
                anim.start();
            }
        }
    }
}
