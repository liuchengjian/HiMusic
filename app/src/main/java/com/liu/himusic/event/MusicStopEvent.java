package com.liu.himusic.event;

import com.lzx.starrysky.SongInfo;

/**
 * 开始播放
 */
public class MusicStopEvent {
    SongInfo songInfo;

    public MusicStopEvent(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

}
