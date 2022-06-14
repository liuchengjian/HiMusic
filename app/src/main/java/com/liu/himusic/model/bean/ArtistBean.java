package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class ArtistBean implements Serializable {

    /**
     * name : 陈粒
     * id : 1007170
     * picId : 0
     * img1v1Id : 0
     * briefDesc :
     * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
     * img1v1Url : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
     * albumSize : 0
     * alias : []
     * trans :
     * musicSize : 0
     * topicPerson : 0
     */

    public String name;
    public int id;
    public int picId;
    public int img1v1Id;
    public String briefDesc;
    public String picUrl;
    public String img1v1Url;
    public int albumSize;
    public String trans;
    public int musicSize;
    public int topicPerson;
    public List<Object> alias;
}
