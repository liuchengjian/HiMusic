package com.liu.himusic.model.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

public class UiElementBean extends BaseObservable implements Serializable {
    /**
     * subTitle : {"title":"推荐歌单"}
     * button : {"action":"orpheus://playlistCollection?referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD","actionType":"orpheus","text":"更多","iconUrl":null}
     * rcmdShowType : DEFAULT
     */

    public SubTitleBean subTitle;
    public ButtonBean button;
    public ImageBean image;
    public String rcmdShowType;
    public MainTitleBean mainTitle;


}
