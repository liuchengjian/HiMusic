package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class VideoBean implements Serializable {

    public String videoKey;
    public Integer duration;
    public String coverUrl;
    public String frameUrl;
    public ImageBean frameImage;
    public Integer width;
    public Integer height;
    public UrlInfoBean urlInfo;
    public List<UrlInfoBean> urlInfos;
    public UrlInfoBean rcmdUrlInfo;
    public String playCount;
    public CoverDetailBean coverDetail;


}
