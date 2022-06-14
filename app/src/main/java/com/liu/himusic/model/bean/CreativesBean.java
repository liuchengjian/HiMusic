package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class CreativesBean implements Serializable {
    /**
     * creativeType : scroll_playlist
     * creativeId : 7440133398
     * action : orpheus://nm/playlist/flow?source=HOMEPAGE_BLOCK_PLAYLIST_RCMD&bizData=[{"resourceId":"7440133398","alg":"alg_exposure_mp","reason":null},{"resourceId":"7392369094","alg":"alg_exposure_mp","reason":null},{"resourceId":"7018363493","alg":"alg_exposure_mp","reason":null}]&snap=true&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD
     * actionType : orpheus
     * uiElement : {"mainTitle":{"title":"2022年BBMA公告牌音乐大奖获奖名单"},"image":{"imageUrl":"http://p1.music.126.net/fZdh32-fFlGgJfbEMkW9OQ==/109951167427737966.jpg"},"labelTexts":["欧美","榜单"],"rcmdShowType":"DEFAULT"}
     * resources : [{"uiElement":{"mainTitle":{"title":"2022年BBMA公告牌音乐大奖获奖名单"},"image":{"imageUrl":"http://p1.music.126.net/fZdh32-fFlGgJfbEMkW9OQ==/109951167427737966.jpg"},"labelTexts":["欧美","榜单"],"rcmdShowType":"DEFAULT"},"resourceType":"list","resourceId":"7440133398","resourceUrl":null,"resourceExtInfo":{"playCount":163500,"highQuality":false,"specialType":0},"action":"orpheus://nm/playlist/flow?source=HOMEPAGE_BLOCK_PLAYLIST_RCMD&bizData=[{\"resourceId\":\"7440133398\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7392369094\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7018363493\",\"alg\":\"alg_exposure_mp\",\"reason\":null}]&snap=true&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD","actionType":"orpheus","valid":true,"alg":"alg_exposure_mp","logInfo":"{\"deepScore\":\"0.0\",\"cartScore\":\"0.0\",\"src\":\"-1\",\"clickScore\":\"0.0\",\"pScore\":\"0.0\",\"srcType\":\"-1\"}"},{"uiElement":{"mainTitle":{"title":"港乐点唱机 | 声生不息的粤语抒情"},"image":{"imageUrl":"http://p1.music.126.net/pQhlbLvfZz-LK-3axhUC4g==/109951167397120818.jpg"},"labelTexts":["粤语","流行","经典"],"rcmdShowType":"DEFAULT"},"resourceType":"list","resourceId":"7392369094","resourceUrl":null,"resourceExtInfo":{"playCount":142777,"highQuality":false,"specialType":0},"action":"orpheus://nm/playlist/flow?source=HOMEPAGE_BLOCK_PLAYLIST_RCMD&bizData=[{\"resourceId\":\"7392369094\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7018363493\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7440133398\",\"alg\":\"alg_exposure_mp\",\"reason\":null}]&snap=true&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD","actionType":"orpheus","valid":true,"alg":"alg_exposure_mp","logInfo":"{\"deepScore\":\"0.0\",\"cartScore\":\"0.0\",\"src\":\"-1\",\"clickScore\":\"0.0\",\"pScore\":\"0.0\",\"srcType\":\"-1\"}"},{"uiElement":{"mainTitle":{"title":"Jazz 派对丨在灵魂深处轻步曼舞"},"image":{"imageUrl":"http://p1.music.126.net/fgSJz-eXdI0uOcEnf0sebA==/109951166516043985.jpg"},"labelTexts":["欧美","爵士","放松"],"rcmdShowType":"DEFAULT"},"resourceType":"list","resourceId":"7018363493","resourceUrl":null,"resourceExtInfo":{"playCount":31843,"highQuality":false,"specialType":0},"action":"orpheus://nm/playlist/flow?source=HOMEPAGE_BLOCK_PLAYLIST_RCMD&bizData=[{\"resourceId\":\"7018363493\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7440133398\",\"alg\":\"alg_exposure_mp\",\"reason\":null},{\"resourceId\":\"7392369094\",\"alg\":\"alg_exposure_mp\",\"reason\":null}]&snap=true&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD","actionType":"orpheus","valid":true,"alg":"alg_exposure_mp","logInfo":"{\"deepScore\":\"0.0\",\"cartScore\":\"0.0\",\"src\":\"-1\",\"clickScore\":\"0.0\",\"pScore\":\"0.0\",\"srcType\":\"-1\"}"}]
     * alg : alg_exposure_mp
     * logInfo : {"deepScore":"0.0","cartScore":"0.0","src":"-1","clickScore":"0.0","pScore":"0.0","srcType":"-1"}
     * position : 0
     */

    public String creativeType;
    public String creativeId;
    public String action;
    public String actionType;
    public UiElementBean uiElement;
    public String alg;
    public String logInfo;
    public int position;
    public List<ResourcesBean> resources;
}
