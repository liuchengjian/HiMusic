package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class Song implements Serializable {

    /**
     * name : 走马
     * id : 30431367
     * pst : 0
     * t : 0
     * ar : [{"id":1007170,"name":"陈粒","tns":[],"alias":[]}]
     * alia : []
     * pop : 100
     * st : 0
     * rt : null
     * fee : 8
     * v : 27
     * crbt : null
     * cf :
     * al : {"id":3098832,"name":"如也","picUrl":"http://p3.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg","tns":[],"pic":7721870161993398}
     * dt : 235555
     * h : {"br":320000,"fid":0,"size":9425023,"vd":-42438,"sr":44100}
     * m : {"br":192000,"fid":0,"size":5655031,"vd":-39791,"sr":44100}
     * l : {"br":128000,"fid":0,"size":3770035,"vd":-37995,"sr":44100}
     * sq : {"br":789014,"fid":0,"size":23232090,"vd":-42455,"sr":44100}
     * hr : null
     * a : null
     * cd : 1
     * no : 11
     * rtUrl : null
     * ftype : 0
     * rtUrls : []
     * djId : 0
     * copyright : 2
     * s_id : 0
     * mark : 8192
     * originCoverType : 1
     * originSongSimpleData : null
     * tagPicList : null
     * resourceState : true
     * version : 27
     * songJumpInfo : null
     * entertainmentTags : null
     * single : 0
     * noCopyrightRcmd : null
     * rtype : 0
     * rurl : null
     * mst : 9
     * cp : 753020
     * mv : 0
     * publishTime : 1422806400007
     * videoInfo : {"moreThanOne":false,"video":{"vid":"a14B98vPVamvkN8","type":3,"title":null,"playTime":0,"coverUrl":null,"publishTime":0,"artists":null,"alias":null}}
     */

    public String name;
    public int id;
    public int pst;
    public int t;
    public int pop;
    public int st;
    public Object rt;
    public int fee;
    public int v;
    public Object crbt;
    public String cf;
    public AlBean al;
    public int dt;
    public HBean h;
    public MBean m;
    public LBean l;
    public SqBean sq;
    public Object hr;
    public Object a;
    public String cd;
    public int no;
    public Object rtUrl;
    public int ftype;
    public int djId;
    public int copyright;
    public int s_id;
    public long mark;
    public int originCoverType;
    public Object originSongSimpleData;
    public Object tagPicList;
    public boolean resourceState;
    public int version;
    public Object songJumpInfo;
    public Object entertainmentTags;
    public int single;
    public Object noCopyrightRcmd;
    public int rtype;
    public Object rurl;
    public int mst;
    public int cp;
    public int mv;
    public long publishTime;
    public VideoInfoBean videoInfo;
    public List<ArBean> ar;
    public List<Object> alia;
    public List<Object> rtUrls;

    public static class AlBean implements Serializable {
        /**
         * id : 3098832
         * name : 如也
         * picUrl : http://p3.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg
         * tns : []
         * pic : 7721870161993398
         */

        public int id;
        public String name;
        public String picUrl;
        public long pic;
        public List<?> tns;
    }

    public static class HBean implements Serializable {
        /**
         * br : 320000
         * fid : 0
         * size : 9425023
         * vd : -42438
         * sr : 44100
         */

        public int br;
        public int fid;
        public int size;
        public Object vd;
        public int sr;
    }

    public static class MBean implements Serializable {
        /**
         * br : 192000
         * fid : 0
         * size : 5655031
         * vd : -39791
         * sr : 44100
         */

        public int br;
        public int fid;
        public int size;
        public Object vd;
        public int sr;
    }

    public static class LBean implements Serializable {
        /**
         * br : 128000
         * fid : 0
         * size : 3770035
         * vd : -37995
         * sr : 44100
         */

        public int br;
        public int fid;
        public int size;
        public Object vd;
        public int sr;
    }

    public static class SqBean implements Serializable {
        /**
         * br : 789014
         * fid : 0
         * size : 23232090
         * vd : -42455
         * sr : 44100
         */

        public int br;
        public int fid;
        public int size;
        public Object vd;
        public int sr;
    }

    public static class VideoInfoBean implements Serializable {
        /**
         * moreThanOne : false
         * video : {"vid":"a14B98vPVamvkN8","type":3,"title":null,"playTime":0,"coverUrl":null,"publishTime":0,"artists":null,"alias":null}
         */

        public boolean moreThanOne;
        public VideoBean video;
    }


    public static class ArBean implements Serializable {
        /**
         * id : 1007170
         * name : 陈粒
         * tns : []
         * alias : []
         */

        public int id;
        public String name;
        public List<?> tns;
        public List<?> alias;
    }
}
