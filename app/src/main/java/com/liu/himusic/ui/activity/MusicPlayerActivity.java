package com.liu.himusic.ui.activity;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.liu.himusic.R;
import com.liu.himusic.ui.widget.SongPlayManager;
import com.liu.himusic.utils.HiUtils;
import com.liucj.lib_common.utils.StatusBarKt;
import com.liucj.lib_common.view.PPImageView;
import com.lzx.starrysky.OnPlayProgressListener;
import com.lzx.starrysky.SongInfo;

/**
 * 播放页面
 */
public class MusicPlayerActivity extends AppCompatActivity {
    private RelativeLayout mBgView;
    private TextView mInfoView;
    private TextView mAuthorView;

    private ImageView mFavouriteView;

    private SeekBar mProgressView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarKt.INSTANCE.setStatusBar(this, true, Color.TRANSPARENT,  false);
        //添加入场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition_bottom2top));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        initData();
        initView();
    }

    private void initData() {
        mSongInfo = SongPlayManager.getInstance().getNowPlayingSongInfo();
        mPlayMode = SongPlayManager.getPlayMode();

    }

    private void initView() {
        SongPlayManager.getInstance().init(this);
        mBgView = findViewById(R.id.root_layout);
        mIvBg = findViewById(R.id.mIvBg);
        mIvBg.setBlurImageUrl(mIvBg,mSongInfo.getSongCover());
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
        mInfoView.setText(mSongInfo.getSongName());
        mInfoView.requestFocus();
        mAuthorView = findViewById(R.id.author_view);
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
        mProgressView.setProgress(0);
        mProgressView.setEnabled(false);
        mPlayModeView = findViewById(R.id.play_mode_view);
        mPlayModeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换播放模式
                SongPlayManager.getInstance().setPlayMode(mPlayMode);
//                switch (mPlayMode) {
//                    case LOOP:
//                        SongPlayManager.getInstance().setPlayMode(AudioController.PlayMode.RANDOM);
//                        break;
//                    case RANDOM:
//                        AudioController.getInstance().setPlayMode(AudioController.PlayMode.REPEAT);
//                        break;
//                    case REPEAT:
//                        AudioController.getInstance().setPlayMode(AudioController.PlayMode.LOOP);
//                        break;
//                }
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
                mStartTimeView.setText(HiUtils.formatTime(l));
                mTotalTimeView.setText(HiUtils.formatTime(l1));
                mProgressView.setMax((int) (l1));
                mProgressView.setProgress((int) (l));
                mProgressView.setEnabled(true);
                if (SongPlayManager.getInstance().isPlaying()) {
                    showPauseView();
                } else {
                    showPlayView();
                }
            }
        });
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

}
