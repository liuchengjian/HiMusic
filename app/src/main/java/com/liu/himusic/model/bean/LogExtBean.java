package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class LogExtBean implements Serializable {

    /**
     * likedCount : 9491
     * commentCount : 581
     * playCount : 578057
     * song : {"id":1382576173,"name":"Cruel Summer","coverUrl":"http://p1.music.126.net/6CB6Jsmb7k7qiJqfMY5Row==/109951164260234943.jpg","duration":178426,"artists":[{"artistId":44266,"artistName":"Taylor Swift"}],"privilege":null,"albumName":"Lover","startTime":null,"endTime":null}
     * algSong : null
     * videoStartPlayTime : 0
     * canCollect : true
     * artistName : null
     * rcmdInfo : null
     * strongPushMark : null
     * strongPushIcon : null
     * specialTag : null
     * channelTag : null
     * artists : []
     */

    public int likedCount;
    public int commentCount;
    public int playCount;
    public SongBean song;
    public Object algSong;
    public int videoStartPlayTime;
    public boolean canCollect;
    public Object artistName;
    public Object rcmdInfo;
    public Object strongPushMark;
    public Object strongPushIcon;
    public Object specialTag;
    public Object channelTag;
    public List<?> artists;

    public static class SongBean implements Serializable {
        /**
         * id : 1382576173
         * name : Cruel Summer
         * coverUrl : http://p1.music.126.net/6CB6Jsmb7k7qiJqfMY5Row==/109951164260234943.jpg
         * duration : 178426
         * artists : [{"artistId":44266,"artistName":"Taylor Swift"}]
         * privilege : null
         * albumName : Lover
         * startTime : null
         * endTime : null
         */

        public int id;
        public String name;
        public String coverUrl;
        public int duration;
        public Object privilege;
        public String albumName;
        public Object startTime;
        public Object endTime;
        public List<ArtistsBean> artists;

        public static class ArtistsBean implements Serializable {
            /**
             * artistId : 44266
             * artistName : Taylor Swift
             */

            public int artistId;
            public String artistName;
        }
    }
}
