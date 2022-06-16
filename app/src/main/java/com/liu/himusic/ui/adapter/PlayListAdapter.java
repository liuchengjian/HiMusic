package com.liu.himusic.ui.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.liu.himusic.model.bean.PlatListBean;
import com.liu.himusic.model.bean.Song;
import com.liu.himusic.ui.adapter.provider.PlayListItemProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayListAdapter extends BaseProviderMultiAdapter<Song> {
    private final PlayListItemProvider provider;
    public List<PlatListBean.SongReason> songReasons;

    public void setSongReasons(List<PlatListBean.SongReason> songReasons) {
        this.songReasons = songReasons;
        provider.setSongReasons(songReasons);
    }

    public PlayListAdapter(List<PlatListBean.SongReason> songReasons) {
        super();
        // 注册 Provider
        this.songReasons = songReasons;
        provider = new PlayListItemProvider(songReasons);
        addItemProvider(provider);
    }

    @Override
    protected int getItemType(@NotNull List<? extends Song> list, int i) {
        return 0;
    }
}
