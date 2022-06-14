package com.liu.himusic.model.bean;

import java.util.List;

public class VideoPlayUrlBean {

    public List<UrlsDTO> urls;
    public Integer code;

    public static class UrlsDTO {
        public String id;
        public String url;
        public Integer size;
        public Integer validityTime;
        public Boolean needPay;
        public Object payInfo;
        public Integer r;
    }
}
