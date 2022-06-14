package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class ResourceExtInfoBean implements Serializable {
    /**
     * playCount : 163500
     * highQuality : false
     * specialType : 0
     */

    public Long playCount;
    public boolean highQuality;
    public int specialType;

    public List<ArtistBean> artists;
    public Song song;
    public SongData songData;
    public SongPrivilege songPrivilege;
}
