package com.liu.himusic.ui.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.liu.himusic.model.bean.BlockBean;
import com.liu.himusic.ui.adapter.provider.BannerItemProvider;
import com.liu.himusic.ui.adapter.provider.ImageItemProvider;
import com.liu.himusic.ui.adapter.provider.OtherItemProvider;
import com.liu.himusic.ui.adapter.provider.TapItemProvider;
import com.liu.himusic.ui.adapter.provider.ThreeSongItemProvider;
import com.liu.himusic.ui.adapter.provider.VideoItemProvider;
import com.liu.himusic.ui.adapter.provider.VpItemProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FindAdapter extends BaseProviderMultiAdapter<BlockBean> {
    public static int BANNER = 1;
    public static int TAP = 2;
    public static int HOMEPAGE_SLIDE_PLAYLIST = 3;
    public static int HOT_TOPIC = 4;
    public static int HOMEPAGE_SLIDE_PLAYABLE_MLOG = 5;
    public static int HOMEPAGE_BLOCK_STYLE_RCMD = 6;

    public FindAdapter() {
        super();
        // 注册 Provider
        addItemProvider(new OtherItemProvider());
        addItemProvider(new BannerItemProvider());
        addItemProvider(new TapItemProvider());
        addItemProvider(new ImageItemProvider());
        addItemProvider(new VpItemProvider());
        addItemProvider(new VideoItemProvider());
        addItemProvider(new ThreeSongItemProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BlockBean> list, int i) {
        BlockBean blockBean = list.get(i);
        int type = 0;
        switch (blockBean.showType) {
            case "BANNER":
                type = BANNER;
                break;
            case "TAP":
                type = TAP;
                break;
            case "HOMEPAGE_SLIDE_PLAYLIST":
                type = HOMEPAGE_SLIDE_PLAYLIST;
                break;
            case "HOT_TOPIC":
                type = HOT_TOPIC;
                break;
            case "HOMEPAGE_SLIDE_PLAYABLE_MLOG":
                type = HOMEPAGE_SLIDE_PLAYABLE_MLOG;
                break;
            case "SLIDE_PODCAST_VOICE_MORE_TAB":
                type = HOMEPAGE_BLOCK_STYLE_RCMD;
                break;
        }
        if ("HOMEPAGE_BLOCK_STYLE_RCMD".equals(blockBean.blockCode)) {
            type = HOMEPAGE_BLOCK_STYLE_RCMD;
        }
        return type;
    }
}
