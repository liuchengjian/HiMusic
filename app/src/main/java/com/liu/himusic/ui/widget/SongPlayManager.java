package com.liu.himusic.ui.widget;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.hjq.toast.ToastUtils;
import com.liu.himusic.event.MusicAddEvent;
import com.liu.himusic.event.MusicPauseEvent;
import com.liu.himusic.event.MusicStartEvent;
import com.liu.himusic.event.MusicStopEvent;
import com.liucj.lib_common.livedata.LiveDataBus;
import com.liucj.lib_network.cache.CacheManager;
import com.lzx.starrysky.OnPlayProgressListener;
import com.lzx.starrysky.SongInfo;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.control.RepeatMode;
import com.lzx.starrysky.manager.PlaybackStage;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 播放器控制类型
 */
public class SongPlayManager {
    private static final String TAG = "SongPlayManager";
    //播放模式
    private int mode;
    //播放列表
    private List<SongInfo> songList = new ArrayList<>();
    //播放到第几首歌曲
    private int currentSongIndex;
    //单例模式
    private static SongPlayManager instance;
    //维护一个哈希表， key是 SongId, value 是 isMusicCanPlay，如果一首歌已经知道它是否可以播放，就把它放在这个哈希表里面
    private HashMap<String, Boolean> musicCanPlayMap;
    private Boolean isChecked = true;//是否可以播放

    //维护第二个哈希表，Key是SongId,value是 songDetail，如果歌曲详情已经获取，则不必再获取
//    private HashMap<Long, SongDetailBean> songDetailMap;

    private Application context;
    public static final int REPEAT_MODE_NONE = RepeatMode.REPEAT_MODE_NONE;//顺序播放
    public static final int REPEAT_MODE_ONE = RepeatMode.REPEAT_MODE_ONE;//单曲播放
    public static final int REPEAT_MODE_SHUFFLE = RepeatMode.REPEAT_MODE_SHUFFLE;//随机播放
    public static final int REPEAT_MODE_REVERSE = RepeatMode.REPEAT_MODE_REVERSE;//倒序播放


    private SongPlayManager() {
        musicCanPlayMap = new HashMap<>();
        musicCanPlayMap.clear();
        songList.clear();
        mode = StarrySky.with().getRepeatMode().getRepeatMode();
    }

    public static SongPlayManager getInstance() {
        if (instance == null) {
            synchronized (SongPlayManager.class) {
                if (instance == null) {
                    instance = new SongPlayManager();
                }
            }
        }
        return instance;
    }

    public void init(Activity activity) {
        //状态监听
        StarrySky.with().playbackState().observe((LifecycleOwner) activity, new Observer<PlaybackStage>() {
            @Override
            public void onChanged(PlaybackStage playbackStage) {
                if (playbackStage.getStage().equals(PlaybackStage.PLAYING)) {
                    EventBus.getDefault().post(new MusicStartEvent(songList.get(currentSongIndex)));
//                    LiveDataBus.get().with("play_music").postValue(songList.get(currentSongIndex));

                } else if (playbackStage.getStage().equals(PlaybackStage.PAUSE)) {
//                    LiveDataBus.get().with("pause_music").postValue(songList.get(currentSongIndex));
                    EventBus.getDefault().post(new MusicPauseEvent());
                }
            }
        });
    }

    /**
     * 当前播放器 是否是 空闲状态
     */
    public boolean isIdle() {
        return StarrySky.with().isIdle();
    }

    /**
     * 播放到指定位置
     */
    public void seekTo(long progress) {
        StarrySky.with().seekTo(progress, true);
    }

    /**
     * 添加一首歌曲到列表，并返回这首歌所在的位置
     */
    public int addSong(SongInfo songInfo) {
        //查重
        int index = 0;
        boolean hasSong = false;
        for (int i = 0; i < songList.size(); i++) {
            if (songInfo.getSongId().equals(songList.get(i).getSongId())) {
                index = i;
                hasSong = true;
                break;
            }
        }
        if (hasSong) {
            return index;
        } else {
            songList.add(songInfo);
        }
        return songList.size() - 1;
    }

    /**
     * 是否正在播放歌曲
     */
    public boolean isPlaying() {
        return StarrySky.with().isPlaying();
    }

    /**
     * 是否 当前有歌曲播放 暂停状态
     */
    public boolean isPaused() {
        return StarrySky.with().isPaused();
    }

    /**
     * 停止播放
     */
    public void cancelPlay() {
        if (isPlaying() || isPaused()) {
            Log.d(TAG, "cancel Play");
            StarrySky.with().stopMusic();
        }
    }

    /**
     * 暂停播放
     */
    public void pauseMusic() {
        if (isPlaying()) {
            StarrySky.with().pauseMusic();
        }
    }


    /**
     * 删除一首歌
     */
    public void deleteSong(int position) {
        songList.remove(position);
    }

    /**
     * 清空列表
     */
    public void clearSongList() {
        songList.clear();
        currentSongIndex = 0;
    }

    /**
     * 添加一个歌曲列表，一般是播放歌单列表时用的，进来的时候要清空一下播放列表
     */
    public void addSongList(List<SongInfo> songInfoList, int index) {
        clearSongList();
        songList.addAll(songInfoList);
        if (index >= songInfoList.size()) {
            currentSongIndex = songInfoList.size() - 1;
        } else {
            currentSongIndex = index;
        }
    }

    /**
     * 添加一个歌曲列表，并播放
     */
    public void addSongListAndPlay(List<SongInfo> songInfoList, int index) {
        if (songInfoList == null || songInfoList.size() == 0) {
            Log.e(TAG, "songInfoList is null");
            return;
        }
        addSongList(songInfoList, index);
        checkMusic(songInfoList.get(index).getSongId());
    }

    /**
     * 添加一首歌并且播放
     */
    public void addSongAndPlay(SongInfo songInfo) {
        if (songInfo == null) {
            Log.e(TAG, "songInfo is null");
            return;
        }
        currentSongIndex = addSong(songInfo);
//        LiveDataBus.get().with("add_music")
//                .postValue(songInfo);
        EventBus.getDefault().post(new MusicAddEvent(songList.get(currentSongIndex)));
        checkMusic(songInfo.getSongId());
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * 检查一首歌是否可以播放
     */
    private void checkMusic(String songId) {
        if (songId == null) {
            Log.e(TAG, "songId is null");
            return;
        }
        if (musicCanPlayMap.get(songId) == null) {
            //如果一首歌还没有去检测它 是否可以播放，则就去做检测
            if (isChecked) {
                musicCanPlayMap.put(songId, true);
            } else {
                musicCanPlayMap.put(songId, false);
            }
            playMusic(songId);
        } else {
            //如果一首歌曲之前已经检测过了，则直接调用结果即可
            playMusic(songId);
        }
    }

    /**
     * 根据是否可以播放去 播放歌曲/弹出吐司
     */
    public void playMusic(String songId) {
        Log.d(TAG, "songId :" + songId + "music size : " + songList.size());
        if (musicCanPlayMap.get(songId)) {
            //歌曲是可以播放的，直接播放,或者他是本地音乐
            Log.d(TAG, "music can play");
            StarrySky.with().playMusic(songList, currentSongIndex);
//            SharePreferenceUtil.getInstance(App.getContext()).saveLatestSong(songList.get(currentSongIndex));
//            PrefUtils.getInstance().put("currentSongIndex", currentSongIndex);
//            PrefUtils.getInstance().put("songList",new Gs.toJson(songList));
            CacheManager.save("songList", songList);
            CacheManager.save("currentSongIndex", currentSongIndex);
        } else {
            //弹出Toast
            Log.d(TAG, "music can not play");
            ToastUtils.show("本歌曲不能播放，可能是没有版权Or你不是尊贵的VIP用户");
            if (mode != REPEAT_MODE_ONE) {
                playNextMusic();
            } else {
//                LiveDataBus.get().with("stop_music").postValue(songList.get(currentSongIndex));
                EventBus.getDefault().post(new MusicStopEvent(songList.get(currentSongIndex)));
            }
        }
    }

    public void initSongData() {
        List<SongInfo> cacheList = (List<SongInfo>) CacheManager.getCache("songList");
        if (cacheList != null && !cacheList.isEmpty()) {
            currentSongIndex = (int) CacheManager.getCache("currentSongIndex");
            songList.addAll(cacheList);
        }
    }

    public void setOnPlayProgressListener(OnPlayProgressListener listener) {
        if (listener != null) {
            StarrySky.with().setOnPlayProgressListener(listener);
        }
    }

    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     */
    public boolean judgeContainsStr(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

    /**
     * 播放下一首歌曲，根据不同的模式来
     */
    public void playNextMusic() {
        Log.d(TAG, "playNextMusic");
        cancelPlay();
        switch (mode) {
            case REPEAT_MODE_NONE:
                if (currentSongIndex < songList.size()) {
                    //列表播完了的话，继续播放，并且要把index置为0，最近听过的歌曲调成列表第一个
                    if (currentSongIndex == songList.size() - 1) {
                        currentSongIndex = 0;
                    } else {
                        currentSongIndex++;
                    }
                    checkMusic(songList.get(currentSongIndex).getSongId());
                } else {
                    Log.w(TAG, "currentSongIndex >= songListSize");
                }
                break;
            case REPEAT_MODE_ONE:
                playMusic(songList.get(currentSongIndex).getSongId());
                break;
            case REPEAT_MODE_SHUFFLE:
                Random ra = new Random();
                int random = ra.nextInt(songList.size() - 1);
                while (random == currentSongIndex) {
                    random = ra.nextInt(songList.size() - 1);
                }
                currentSongIndex = random;
                checkMusic(songList.get(currentSongIndex).getSongId());
                break;
        }
    }

    /**
     * 播放前一首歌曲
     */
    public void playPreMusic() {
        Log.d(TAG, "playPreMusic");
        cancelPlay();
        switch (mode) {
            case REPEAT_MODE_NONE:
                if (currentSongIndex < songList.size()) {
                    if (currentSongIndex == 0) {
                        currentSongIndex = songList.size() - 1;
                    } else {
                        currentSongIndex--;
                    }
                    checkMusic(songList.get(currentSongIndex).getSongId());
                } else {
                    Log.w(TAG, "currentSongIndex >= songListSize");
                }
                break;
            case REPEAT_MODE_ONE:
                playMusic(songList.get(currentSongIndex).getSongId());
                break;
            case REPEAT_MODE_SHUFFLE:
                Random ra = new Random();
                int random = ra.nextInt(songList.size() - 1);
                while (random == currentSongIndex) {
                    random = ra.nextInt(songList.size() - 1);
                }
                currentSongIndex = random;
                checkMusic(songList.get(currentSongIndex).getSongId());
                break;
        }
    }

    /**
     * 判断传入的歌曲是否正在播放
     */
    public boolean isCurMusicPlaying(String songId) {
        return StarrySky.with().isCurrMusicIsPlayingMusic(songId);
    }

    /**
     * 判断传入的歌曲是否正在暂停
     */
    public boolean isCurMusicPaused(String songId) {
        return StarrySky.with().isCurrMusicIsPaused(songId);
    }

    /**
     * 在Activity点击一首歌的item的时候，需要进入 听歌界面
     * 同时播放这首歌，需要在进入这首歌界面之前重置下状态
     */
    public void clickASong(SongInfo songInfo) {
        if (isPlaying()) {
            Log.d(TAG, "isPlaying");
            //当前播放的歌曲不是准备播放的歌曲，停止该歌曲
            if (!isCurMusicPlaying(songInfo.getSongId())) {
                Log.d(TAG, "!isCurMusicPlaying");
                cancelPlay();
                addSongAndPlay(songInfo);
            }
        } else if (isPaused()) {
            Log.d(TAG, "isPaused");
            //当前有歌曲正在暂停，但是并不是要选中的歌曲，则停止该歌曲
            if (!isCurMusicPaused(songInfo.getSongId())) {
                Log.d(TAG, "!isCurMusicPaused");
                cancelPlay();
                addSongAndPlay(songInfo);
            }
        } else if (isIdle()) {
            Log.d(TAG, "isIdle");
            //空闲中 则直接播放这首歌
            addSongAndPlay(songInfo);
        } else {
//            Log.d(TAG, "no idle,no playing ,no paused state:" + StarrySky.with().getS());
        }
    }

    /**
     * 点击一个列表全部播放，则先把正在播放的歌停掉
     */
    public void clickPlayAll(List<SongInfo> songList, int position) {
        cancelPlay();
        addSongListAndPlay(songList, position);
    }

    /**
     * 点击底部音乐控制栏的开始播放
     * 如果有歌曲正在暂停，则播放，如果是idle状态，则播放该歌曲
     */
    public void clickBottomContrllerPlay(SongInfo songInfo) {
        if (isPaused()) {
            restoreMusic();
        } else if (isIdle()) {
            SongPlayManager.getInstance().addSongAndPlay(songInfo);
        }
    }

    /**
     * 恢复播放
     */
    public void restoreMusic() {
        if (isPaused()) {
            StarrySky.with().restoreMusic();
        }
    }


    /**
     * 设置/获取 播放模式
     */
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 获取播放进度
     */
    public long getPlayProgress() {
        if (isPlaying()) {
            return StarrySky.with().getPlayingPosition();
        } else {
            return 0;
        }
    }

    /**
     * 扫描本地媒体信息
     */
    public List<SongInfo> getLocalSongInfoList() {
        return StarrySky.with().querySongInfoInLocal(context);
    }

    /**
     * 获取当前正在播放的歌曲的index
     */
    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    /**
     * 获取歌单列表
     */
    public List<SongInfo> getSongList() {
        return songList;
    }

    public SongInfo getCurrentSong() {
        return songList.get(currentSongIndex);
    }

    public SongInfo getNowPlayingSongInfo() {
        return StarrySky.with().getNowPlayingSongInfo();
    }

    /**
     * 设置播放模式
     *
     * @param repeatMode
     */
    public void setPlayMode(int repeatMode) {
        StarrySky.with().setRepeatMode(repeatMode, true);
    }

    public void playOrPause() {
        if (!isPlaying()) {
            Log.d(TAG, "playMusic");
            clickBottomContrllerPlay(songList.get(currentSongIndex));
        } else {
            Log.d(TAG, "pauseMusic");
            pauseMusic();
        }
    }
    public long getPlayingPosition() {
       return StarrySky.with().getPlayingPosition();
    }
    public long getDuration() {
       return StarrySky.with().getDuration();
    }

    public static int getPlayMode() {
        return StarrySky.with().getRepeatMode().getRepeatMode();
    }


}
