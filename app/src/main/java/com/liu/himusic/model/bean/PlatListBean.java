package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class PlatListBean implements Serializable {
    public List<Song> dailySongs;
    public List<Song> orderSongs;
    public List<SongReason> recommendReasons;


    public static class SongReason {
        /**
         * songId : 133998
         * reason : 超76%人播放
         */

        public int songId;
        public String reason;
    }
}
