package com.liu.himusic.model.bean;

import java.io.Serializable;

public class ExtInfoBean {
    public String id;
    public int type;
    public int mlogBaseDataType;
    public Object position;
    public Resource resource;
    public static class Resource implements Serializable {
        public LogBaseDataBean mlogBaseData;
        public LogExtBean mlogExtVO;
        public Object userProfile;
        public Object relatedPubUsers;
        public int status;
        public int source;
        public String shareUrl;
    }
}

