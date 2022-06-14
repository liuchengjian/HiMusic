package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class AlbumBean implements Serializable {
    /**
     * name : 如也
     * id : 3098832
     * type : 专辑
     * size : 15
     * picId : 7721870161993398
     * blurPicUrl : http://p4.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg
     * companyId : 0
     * pic : 7721870161993398
     * picUrl : http://p3.music.126.net/VuJFMbXzpAProbJPoXLv7g==/7721870161993398.jpg
     * publishTime : 1422806400007
     * description :
     * tags :
     * company : 独立发行
     * briefDesc :
     * artist : {"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}
     * songs : []
     * alias : []
     * status : 3
     * copyrightId : 0
     * commentThreadId : R_AL_3_3098832
     * artists : [{"name":"陈粒","id":1007170,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}]
     * subType : 录音室版
     * transName : null
     * onSale : false
     * mark : 0
     * gapless : 0
     */

    public String name;
    public int id;
    public String type;
    public int size;
    public long picId;
    public String blurPicUrl;
    public int companyId;
    public long pic;
    public String picUrl;
    public long publishTime;
    public String description;
    public String tags;
    public String company;
    public String briefDesc;
    public ArtistBean artist;
    public int status;
    public int copyrightId;
    public String commentThreadId;
    public String subType;
    public Object transName;
    public boolean onSale;
    public int mark;
    public int gapless;
    public List<?> songs;
    public List<?> alias;
    public List<ArtistBean> artists;
}
