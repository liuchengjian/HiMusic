package com.liu.himusic.model.bean;

import java.io.Serializable;

public class ResourcesBean implements Serializable {
    /**
     * uiElement : {"mainTitle":{"title":"2022年BBMA公告牌音乐大奖获奖名单"},"image":{"imageUrl":"http://p1.music.126.net/fZdh32-fFlGgJfbEMkW9OQ==/109951167427737966.jpg"},"labelTexts":["欧美","榜单"],"rcmdShowType":"DEFAULT"}
     * resourceType : list
     * resourceId : 7440133398
     * resourceUrl : null
     * resourceExtInfo : {"playCount":163500,"highQuality":false,"specialType":0}
     * action : orpheus://nm/playlist/flow?source=HOMEPAGE_BLOCK_PLAYLIST_RCMD&bizData=[{"resourceId":"7440133398","alg":"alg_exposure_mp","reason":null},{"resourceId":"7392369094","alg":"alg_exposure_mp","reason":null},{"resourceId":"7018363493","alg":"alg_exposure_mp","reason":null}]&snap=true&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD
     * actionType : orpheus
     * valid : true
     * alg : alg_exposure_mp
     * logInfo : {"deepScore":"0.0","cartScore":"0.0","src":"-1","clickScore":"0.0","pScore":"0.0","srcType":"-1"}
     */

    public UiElementBean uiElement;
    public String resourceType;
    public String resourceId;
    public Object resourceUrl;
    public ResourceExtInfoBean resourceExtInfo;
    public String action;
    public String actionType;
    public boolean valid;
    public String alg;
    public String logInfo;
}
