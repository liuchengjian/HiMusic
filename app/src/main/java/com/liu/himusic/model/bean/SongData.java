package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class SongData implements Serializable {

    /**
     * name : 走马
     * id : 30431367
     * position : 0
     * alias : []
     * status : 0
     * fee : 8
     * copyrightId : 753020
     * disc : 1
     * no : 11
     * artists : [{"name":"陈粒","id":1007170,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}]
     * album : {"name":"如也","id":3098832,"type":"专辑","size":15,"picId":7721870161993398,"blurPicUrl":"http://p4.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg","companyId":0,"pic":7721870161993398,"picUrl":"http://p3.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg","publishTime":1422806400007,"description":"","tags":"","company":"独立发行","briefDesc":"","artist":{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0},"songs":[],"alias":[],"status":3,"copyrightId":0,"commentThreadId":"R_AL_3_3098832","artists":[{"name":"陈粒","id":1007170,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}],"subType":"录音室版","transName":null,"onSale":false,"mark":0,"gapless":0}
     * starred : false
     * popularity : 100
     * score : 100
     * starredNum : 0
     * duration : 235555
     * playedNum : 0
     * dayPlays : 0
     * hearTime : 0
     * sqMusic : {"name":null,"id":7238175830,"size":23232090,"extension":"flac","sr":44100,"dfsId":0,"bitrate":789014,"playTime":235555,"volumeDelta":-42455}
     * hrMusic : null
     * ringtone : null
     * crbt : null
     * audition : null
     * copyFrom :
     * commentThreadId : R_SO_4_30431367
     * rtUrl : null
     * ftype : 0
     * rtUrls : []
     * copyright : 2
     * transName : null
     * sign : null
     * mark : 0
     * originCoverType : 1
     * originSongSimpleData : null
     * single : 0
     * noCopyrightRcmd : null
     * rtype : 0
     * rurl : null
     * mvid : 0
     * hMusic : {"name":null,"id":7238175823,"size":9425023,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":235555,"volumeDelta":-42438}
     * mMusic : {"name":null,"id":7238175824,"size":5655031,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":192000,"playTime":235555,"volumeDelta":-39791}
     * lMusic : {"name":null,"id":7238175831,"size":3770035,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":235555,"volumeDelta":-37995}
     * bMusic : {"name":null,"id":7238175831,"size":3770035,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":235555,"volumeDelta":-37995}
     * mp3Url : null
     */

    public String name;
    public int id;
    public int position;
    public int status;
    public int fee;
    public int copyrightId;
    public String disc;
    public int no;
    public AlbumBean album;
    public boolean starred;
    public int popularity;
    public int score;
    public int starredNum;
    public int duration;
    public int playedNum;
    public int dayPlays;
    public int hearTime;
    public SqMusicBean sqMusic;
    public Object hrMusic;
    public Object ringtone;
    public Object crbt;
    public Object audition;
    public String copyFrom;
    public String commentThreadId;
    public Object rtUrl;
    public int ftype;
    public int copyright;
    public Object transName;
    public Object sign;
    public int mark;
    public int originCoverType;
    public Object originSongSimpleData;
    public int single;
    public Object noCopyrightRcmd;
    public int rtype;
    public Object rurl;
    public int mvid;
    public HMusicBean hMusic;
    public MMusicBean mMusic;
    public LMusicBean lMusic;
    public BMusicBean bMusic;
    public Object mp3Url;
    public List<?> alias;
    public List<ArtistBean> artists;
    public List<?> rtUrls;

    public static class SqMusicBean implements Serializable {
        /**
         * name : null
         * id : 7238175830
         * size : 23232090
         * extension : flac
         * sr : 44100
         * dfsId : 0
         * bitrate : 789014
         * playTime : 235555
         * volumeDelta : -42455
         */

        public Object name;
        public long id;
        public int size;
        public String extension;
        public long sr;
        public long dfsId;
        public long bitrate;
        public long playTime;
        public long volumeDelta;
    }

    public static class HMusicBean implements Serializable {
        /**
         * name : null
         * id : 7238175823
         * size : 9425023
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 320000
         * playTime : 235555
         * volumeDelta : -42438
         */

        public Object name;
        public long id;
        public long size;
        public String extension;
        public long sr;
        public long dfsId;
        public long bitrate;
        public long playTime;
        public long volumeDelta;
    }

    public static class MMusicBean implements Serializable {
        /**
         * name : null
         * id : 7238175824
         * size : 5655031
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 192000
         * playTime : 235555
         * volumeDelta : -39791
         */

        public Object name;
        public long id;
        public long size;
        public String extension;
        public long sr;
        public long dfsId;
        public long bitrate;
        public long playTime;
        public long volumeDelta;
    }

    public static class LMusicBean implements Serializable {
        /**
         * name : null
         * id : 7238175831
         * size : 3770035
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 128000
         * playTime : 235555
         * volumeDelta : -37995
         */

        public Object name;
        public long id;
        public long size;
        public String extension;
        public long sr;
        public long dfsId;
        public long bitrate;
        public long playTime;
        public long volumeDelta;
    }

    public static class BMusicBean implements Serializable {
        /**
         * name : null
         * id : 7238175831
         * size : 3770035
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 128000
         * playTime : 235555
         * volumeDelta : -37995
         */

        public Object name;
        public long id;
        public long size;
        public String extension;
        public long sr;
        public long dfsId;
        public long bitrate;
        public long playTime;
        public long volumeDelta;
    }
}
