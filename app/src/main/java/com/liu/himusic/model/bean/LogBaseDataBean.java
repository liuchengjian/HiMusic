package com.liu.himusic.model.bean;

import java.io.Serializable;

public class LogBaseDataBean implements Serializable {

    public String id;
    public int type;
    public String originalTitle;
    public String text;
    public String desc;
    public String interveneText;
    public long pubTime;
    public String coverUrl;
    public CoverDetailBean coverDetail;
    public int coverHeight;
    public boolean greatCover;
    public int coverWidth;
    public Integer coverColor;
    public String coverPicKey;
    public String coverDynamicUrl;
    public String audio;
    public String threadId;
    public Integer duration;
    public VideoBean video;
    public Object videos;
    public String graphic;
}
