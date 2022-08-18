package com.liu.himusic.ui.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.liu.himusic.R;
import com.liu.himusic.event.MusicStartEvent;
import com.liu.himusic.model.api.FindApi;
import com.liu.himusic.model.api.PlayListApi;
import com.liu.himusic.model.bean.LyricBean;
import com.liu.himusic.model.net.ApiFactory;
import com.liu.himusic.ui.adapter.MusicPagerAdapter;
import com.liu.himusic.ui.widget.BottomSongBarLeftView;
import com.liu.himusic.ui.widget.IndictorView;
import com.liu.himusic.ui.widget.LyricView;
import com.liu.himusic.ui.widget.SongPlayManager;
import com.liu.himusic.utils.HiUtils;
import com.liucj.lib_common.utils.StatusBar;
import com.liucj.lib_common.utils.StatusBarKt;
import com.liucj.lib_common.view.PPImageView;
import com.liucj.lib_network.restful_kt.HiCall;
import com.liucj.lib_network.restful_kt.HiCallback;
import com.liucj.lib_network.restful_kt.HiResponse;
import com.lzx.starrysky.OnPlayProgressListener;
import com.lzx.starrysky.SongInfo;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

/**
 * 播放页面
 */
public class MusicPlayerActivity extends AppCompatActivity {
    private RelativeLayout mBgView;
    private TextView mInfoView;
    private TextView mAuthorView;

    private ImageView mFavouriteView;

    private TickSeekBar mProgressView;
    private TextView mStartTimeView;
    private TextView mTotalTimeView;

    private ImageView mPlayModeView;
    private ImageView mPlayView;
    private ImageView mNextView;
    private ImageView mPreViousView;

    private Animator animator;
    /**
     * data
     */
    private SongInfo mSongInfo; //当前正在播放歌曲
    private int mPlayMode;
    private PPImageView mIvBg;
    private boolean isFirstTouch = false;
    private LyricView mLyricView;
    private IndictorView mIndictorView;
    private FrameLayout mRlCenter;
    private boolean isShowLyrics = false;

    public static void start(Activity context) {
        Intent intent = new Intent(context, MusicPlayerActivity.class);
        ActivityCompat.startActivity(context, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加入场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition_bottom2top));
        }
        EventBus.getDefault().register(this);
        StatusBarKt.INSTANCE.setStatusBar(this, false, Color.TRANSPARENT, true);
        setContentView(R.layout.activity_music_player);
        initData();
        initView();
    }

    private void initData() {
        mSongInfo = SongPlayManager.getInstance().getNowPlayingSongInfo();
        mPlayMode = SongPlayManager.getPlayMode();
    }

    public void getLyric(String songId) {
        HiCall hiCall = ApiFactory.INSTANCE.create(PlayListApi.class).lyric(songId);
        hiCall.enqueue(new HiCallback() {
            @Override
            public void onSuccess(@NotNull HiResponse response) {
                String data = (String) response.getRawData();
                LyricBean bean = new Gson().fromJson(data, LyricBean.class);
                if (bean.lrc != null) {
                    if (bean.tlyric.lyric != null) {
                        mLyricView.loadLrc(bean.lrc.lyric, bean.tlyric.lyric);
                    } else {
                        mLyricView.loadLrc(bean.lrc.lyric, "");
                    }
                } else {
                    mLyricView.loadLrc("", "");
                }
                initLrcListener();
            }

            @Override
            public void onFailed(@NotNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        SongPlayManager.getInstance().init(this);
        mBgView = findViewById(R.id.root_layout);
        mIndictorView = findViewById(R.id.mIndictorView);
        mLyricView = findViewById(R.id.mLyricView);
        mRlCenter = findViewById(R.id.mRlCenter);
        mIvBg = findViewById(R.id.mIvBg);
        mIvBg.setBlurImageUrl(mIvBg, mSongInfo.getSongCover());
        mRlCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLyrics(true);
            }
        });
        mIndictorView.getMusicPagerAdapter().setOnItemClickListener(new MusicPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showLyrics(true);
            }
        });
        mLyricView.setCoverChangeListener(() -> {
            showLyrics(false);
        });
//        ImageLoaderManager.getInstance().displayImageForViewGroup(mBgView, mSongInfo.U);
        findViewById(R.id.back_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.title_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.share_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                shareMusic(mAudioBean.mUrl, mAudioBean.name);
            }
        });
        findViewById(R.id.show_list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MusicListDialog dialog = new MusicListDialog(MusicPlayerActivity.this);
//                dialog.show();
            }
        });
        mInfoView = findViewById(R.id.album_view);

        mInfoView.requestFocus();
        mAuthorView = findViewById(R.id.author_view);
        mInfoView.setText(mSongInfo.getSongName());
        mAuthorView.setText(mSongInfo.getArtist());

        mFavouriteView = findViewById(R.id.favourite_view);
        mFavouriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏与否
//                AudioController.getInstance().changeFavourite();
            }
        });
//        changeFavouriteStatus(false);
        mStartTimeView = findViewById(R.id.start_time_view);
        mTotalTimeView = findViewById(R.id.total_time_view);
        mProgressView = findViewById(R.id.progress_view);
        long currentProgress = SongPlayManager.getInstance().getPlayingPosition();
        long duration = SongPlayManager.getInstance().getDuration();
        mStartTimeView.setText(HiUtils.formatTime(currentProgress));
        mTotalTimeView.setText(HiUtils.formatTime(duration));
        mProgressView.setProgress((int) currentProgress);
        mProgressView.setMax((int) duration);
        //进度条监听
        mProgressView.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.e("mProgressView", "onSeeking");
                mStartTimeView.setText(HiUtils.formatTime(seekParams.progress));
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {
                Log.e("mProgressView", "onStartTrackingTouch");
                isFirstTouch = true;
            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {
                Log.e("mProgressView", "onStopTrackingTouch");
                SongPlayManager.getInstance().seekTo(seekBar.getProgress());
                mLyricView.updateTime(seekBar.getProgress());
                isFirstTouch = false;
            }
        });

        mPlayModeView = findViewById(R.id.play_mode_view);
        mPlayModeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换播放模式
                switch (mPlayMode) {
                    case SongPlayManager.REPEAT_MODE_NONE:
                        SongPlayManager.getInstance().setPlayMode(SongPlayManager.REPEAT_MODE_ONE);
                        break;
                    case SongPlayManager.REPEAT_MODE_ONE:
                        SongPlayManager.getInstance().setPlayMode(SongPlayManager.REPEAT_MODE_REVERSE);
                        break;
                    case SongPlayManager.REPEAT_MODE_REVERSE:
                        SongPlayManager.getInstance().setPlayMode(SongPlayManager.REPEAT_MODE_NONE);
                        break;
                }
            }
        });
        updatePlayModeView();
        mPreViousView = findViewById(R.id.previous_view);
        mPreViousView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongPlayManager.getInstance().playPreMusic();
            }
        });
        mPlayView = findViewById(R.id.play_view);
        mPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongPlayManager.getInstance().playOrPause();
                if (SongPlayManager.getInstance().isPlaying()) {
                    showPlayView();
                } else {
                    showPauseView();
                }
            }
        });
        mNextView = findViewById(R.id.next_view);
        mNextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongPlayManager.getInstance().playNextMusic();
            }
        });

        SongPlayManager.getInstance().setOnPlayProgressListener(new OnPlayProgressListener() {
            @Override
            public void onPlayProgress(long l, long l1) {
                if (isFirstTouch) {
                    return;
                }
                mTotalTimeView.setText(HiUtils.formatTime(l1));
                mStartTimeView.setText(HiUtils.formatTime(l));
                mProgressView.setMax((int) (l1));
                mProgressView.setProgress((int) (l));
                mLyricView.updateTime((int) (l));
            }
        });
    }

    private void initLrcListener() {
        mLyricView.setListener(time -> {
            mSongInfo = SongPlayManager.getInstance().getNowPlayingSongInfo();
            SongPlayManager.getInstance().seekTo(time);
            if (SongPlayManager.getInstance().isPaused()) {
                SongPlayManager.getInstance().playMusic(mSongInfo.getSongId());
            } else if (SongPlayManager.getInstance().isIdle()) {
                SongPlayManager.getInstance().clickASong(mSongInfo);
            }
            return true;
        });

        mLyricView.setCoverChangeListener(() -> {
            showLyrics(false);
        });
    }

    //根据isShowLyrics来判断是否展示歌词
    private void showLyrics(boolean isShowLyrics) {
        mIndictorView.setVisibility(isShowLyrics ? View.GONE : View.VISIBLE);
        mLyricView.setVisibility(isShowLyrics ? View.VISIBLE : View.GONE);
    }

    private void showPlayView() {
        mPlayView.setImageResource(R.mipmap.audio_aj6);
    }

    private void showPauseView() {
        mPlayView.setImageResource(R.mipmap.audio_aj7);
    }

    private void updatePlayModeView() {
        switch (mPlayMode) {
            case SongPlayManager.REPEAT_MODE_SHUFFLE:
                mPlayModeView.setImageResource(R.mipmap.player_loop);
                break;
            case SongPlayManager.REPEAT_MODE_NONE:
                mPlayModeView.setImageResource(R.mipmap.player_random);
                break;
            case SongPlayManager.REPEAT_MODE_ONE:
                mPlayModeView.setImageResource(R.mipmap.player_once);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicStartEvent(MusicStartEvent event) {
        SongInfo songInfo =  SongPlayManager.getInstance().getNowPlayingSongInfo();
        if(!mSongInfo.getSongId().equals(songInfo.getSongId())){
            mSongInfo = songInfo;
        }
        mInfoView.setText(mSongInfo.getSongName());
        mAuthorView.setText(mSongInfo.getArtist());
        mIvBg.setBlurImageUrl(mIvBg, mSongInfo.getSongCover());
        mIndictorView.upDataInit();
        getLyric(mSongInfo.getSongId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
