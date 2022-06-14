package com.liu.himusic.model.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BlockBean implements Serializable {
    //"blockCode": "HOMEPAGE_VOICELIST_RCMD",
    //                "showType": "SLIDE_PODCAST_VOICE_MORE_TAB",
    //                "dislikeShowType": 0,
    //"canClose": false,
    //                "defaultTab": "VOICE_LIST_HOMEPAGE",
    //                "blockStyle": 0

    public String blockCode;
    public String showType;
    public int dislikeShowType;
    public List<CreativesBean> creatives;
    public boolean canClose;
    public String defaultTab;
    public int blockStyle;
    public Object extInfo;
    public UiElementBean uiElement;

    public List<ExtInfoBean> extInfoList (){
        Gson gson = new Gson();
        if(extInfo==null){
            return new ArrayList<>();
        }
       String jsonArray =  gson.toJson(extInfo);
        List<ExtInfoBean> list = new Gson().fromJson(jsonArray, new TypeToken<List<ExtInfoBean>>() {}.getType());

        return list;
    }

    public ExtInfo extInfoBanner (){
        Gson gson = new Gson();
        if(extInfo==null){
            return null;
        }
        String jsonArray =  gson.toJson(extInfo);
        ExtInfo ext = new Gson().fromJson(jsonArray, ExtInfo.class);
        return ext;
    }

}
