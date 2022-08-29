package com.liu.himusic.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.liu.himusic.R
import com.liu.himusic.databinding.ActivitySongPlayerBinding
import com.liu.himusic.event.MusicAddEvent
import com.liu.himusic.event.MusicPauseEvent
import com.liu.himusic.event.MusicStartEvent
import com.liu.himusic.event.MusicStopEvent
import com.liu.himusic.model.bean.LyricBean
import com.liu.himusic.ui.adapter.MusicPagerAdapter
import com.liu.himusic.ui.viewmodel.SongPlayerViewModel
import com.liu.himusic.ui.widget.LyricView.OnCoverChangeListener
import com.liu.himusic.ui.widget.SongPlayManager
import com.liu.himusic.utils.HiUtils
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.StatusBarKt.setStatusBar
import com.liucj.lib_common.view.PPImageView
import com.lzx.starrysky.OnPlayProgressListener
import com.lzx.starrysky.SongInfo
import com.warkiz.tickseekbar.OnSeekChangeListener
import com.warkiz.tickseekbar.SeekParams
import com.warkiz.tickseekbar.TickSeekBar
import kotlinx.android.synthetic.main.activity_song_player.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SongPlayerActivity : AppCompatActivity() {
    private var mSongInfo: SongInfo? = null
    var binding: ActivitySongPlayerBinding? = null
    var isFirstTouch: Boolean = false
    private var viewMolder: SongPlayerViewModel? = null
    private var mPlayMode = 0

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val intent = Intent(context, SongPlayerActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(this, false, Color.TRANSPARENT, true)
        binding = ActivitySongPlayerBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        mPlayMode = SongPlayManager.getPlayMode()
        EventBus.getDefault().register(this)
        viewMolder = ViewModelProvider(this)[SongPlayerViewModel::class.java]
        initView()
        SongPlayManager.getInstance().init(this)
    }

    private fun initView() {
        mSongInfo = SongPlayManager.getInstance().currentSong
        initHeaderView(mSongInfo)
        //中间的view
        initCenterView()
        //底部的view
        initBottomView()
        //获取歌词
        toLyric()
        initData()
    }

    private fun initData() {
        LiveDataBus.get().with("lyricSuccess").observe(this, { bean ->
            if (bean != null && bean is LyricBean) {
                //获取歌词成功
                if (bean.lrc != null) {
                    if (bean.tlyric.lyric != null) {
                        mLyricView.loadLrc(bean.lrc.lyric, bean.tlyric.lyric)
                    } else {
                        mLyricView.loadLrc(bean.lrc.lyric, "")
                    }
                } else {
                    mLyricView.loadLrc("", "")
                }
                initLrcListener()
            }
        })
    }

    /**
     * 头部展示
     */
    private fun initHeaderView(mSongInfo:SongInfo?) {
        val mIvBg:PPImageView = binding!!.mIvBg
        PPImageView.setBlurImageUrl(mIvBg, mSongInfo!!.songCover)
        binding!!.albumView.text = mSongInfo!!.songName
        binding!!.authorView.text = mSongInfo!!.artist
        binding!!.backView.setOnClickListener {
            finish()
        }
    }

    /**
     * 中间展示
     */
    private fun initCenterView() {
        binding!!.mRlCenter.setOnClickListener(View.OnClickListener { showLyrics(true) })
        binding!!.mIndictorView.musicPagerAdapter
            .setOnItemClickListener(MusicPagerAdapter.OnItemClickListener { showLyrics(true) })
        binding!!.mLyricView.setCoverChangeListener(OnCoverChangeListener { showLyrics(false) })
        //要在UI初始化完，否则会多一次listener响应
        binding!!.mIndictorView.setOnPageChangeListener { position -> //指定要播放的position
            var mQueue: List<SongInfo> = SongPlayManager.getInstance().songList
            if (position < mQueue.size && position > 0) {

                SongPlayManager.getInstance().clickASong(mQueue[position])
                initHeaderView(mQueue[position])
                //展示播放的歌曲的歌词
                binding!!.mLyricView.postDelayed({
                    viewMolder!!.toLyric(mQueue[position]!!.songId)
                }, 500)
            }
        }
    }

    /**
     * 歌词展示
     */
    private fun initLrcListener() {
        mLyricView.setListener { time: Long ->
            val mSongInfo: SongInfo = SongPlayManager.getInstance().currentSong
            SongPlayManager.getInstance().seekTo(time)
            binding!!.progressView.setProgress(time.toFloat())
            if (SongPlayManager.getInstance().isPaused) {
                SongPlayManager.getInstance().playMusic(mSongInfo.songId)
            } else if (SongPlayManager.getInstance().isIdle) {
                SongPlayManager.getInstance().clickASong(mSongInfo)
            }
            true
        }
        mLyricView.setCoverChangeListener { showLyrics(false) }
    }

    /**
     * 底部展示
     */
    private fun initBottomView() {
        updatePlayModeView()
        //显示按钮
        if (SongPlayManager.getInstance().isPlaying) {
            showPlayView()
        } else {
            showPauseView()
        }
        binding!!.previousView.setOnClickListener(View.OnClickListener {
            SongPlayManager.getInstance().playPreMusic()
        })
        binding!!.playView.setOnClickListener(View.OnClickListener {
            SongPlayManager.getInstance().playOrPause()
        })
        binding!!.nextView.setOnClickListener(View.OnClickListener {
            SongPlayManager.getInstance().playNextMusic()
        })

        val startTimeView = binding!!.startTimeView
        val totalTimeView = binding!!.totalTimeView
        val progressView = binding!!.progressView
        val currentProgress = SongPlayManager.getInstance().playingPosition
        val duration = SongPlayManager.getInstance().duration
        startTimeView.text = HiUtils.formatTime(currentProgress)
        totalTimeView.text = HiUtils.formatTime(duration)
        progressView.max = duration.toFloat()
        progressView.setProgress(currentProgress.toFloat())
        SongPlayManager.getInstance().setOnPlayProgressListener(object : OnPlayProgressListener {
            override fun onPlayProgress(currPos: Long, duration: Long) {
                Log.e("onPlayProgress", "currPos:$currPos")
                if (isFirstTouch) {
                    return
                }
                startTimeView.text = HiUtils.formatTime(currPos)
                totalTimeView.text = HiUtils.formatTime(duration)
                progressView.max = duration.toFloat()
                progressView.setProgress(currPos.toFloat())
                mLyricView.updateTime(currPos)
            }
        })
        //进度条监听
        progressView.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams) {
                Log.e("mProgressView", "onSeeking")
                startTimeView.text = HiUtils.formatTime(seekParams.progress.toLong())
            }

            override fun onStartTrackingTouch(seekBar: TickSeekBar) {
                Log.e("mProgressView", "onStartTrackingTouch")
                isFirstTouch = true
            }

            override fun onStopTrackingTouch(seekBar: TickSeekBar) {
                Log.e("mProgressView", "onStopTrackingTouch")
                SongPlayManager.getInstance().seekTo(seekBar.progress.toLong())
                binding!!.mLyricView.updateTime(seekBar.progress.toLong())
                isFirstTouch = false
            }
        }

    }

    /**
     * 根据isShowLyrics来判断是否展示歌词
     */
    private fun showLyrics(isShowLyrics: Boolean) {
        binding!!.mIndictorView.visibility = if (isShowLyrics) View.GONE else View.VISIBLE
        binding!!.mLyricView.visibility = if (isShowLyrics) View.VISIBLE else View.GONE
    }

    /**
     * 修改播放模式
     */
    private fun updatePlayModeView() {
        when (mPlayMode) {
            SongPlayManager.REPEAT_MODE_SHUFFLE -> binding!!.playModeView.setImageResource(R.mipmap.player_loop)
            SongPlayManager.REPEAT_MODE_NONE -> binding!!.playModeView.setImageResource(R.mipmap.player_random)
            SongPlayManager.REPEAT_MODE_ONE -> binding!!.playModeView.setImageResource(R.mipmap.player_once)
        }
    }

    /**
     * 设置播放图片
     */
    private fun showPlayView() {
        binding!!.playView.setImageResource(R.mipmap.audio_aj6)
    }

    /**
     * 设置暂停图片
     */
    private fun showPauseView() {
        binding!!.playView.setImageResource(R.mipmap.audio_aj7)
    }

    private fun toLyric() {
        binding!!.mLyricView.postDelayed({
            mSongInfo = SongPlayManager.getInstance().currentSong
            viewMolder!!.toLyric(mSongInfo!!.songId)
        }, 500)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPlayMusicEvent(event: MusicStartEvent) {
        showPlayView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStopMusicEvent(event: MusicStopEvent?) {
        showPauseView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPauseMusicEvent(event: MusicPauseEvent?) {
        showPauseView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddMusicEvent(event: MusicAddEvent?) {
        showPauseView()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.activity_close)
        EventBus.getDefault().unregister(this)
    }

}