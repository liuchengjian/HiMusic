package com.liu.himusic.event;

import com.lzx.starrysky.SongInfo;

/**
 * 开始播放
 */
public class MusicAddEvent {
    SongInfo songInfo;

    public MusicAddEvent(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

}
