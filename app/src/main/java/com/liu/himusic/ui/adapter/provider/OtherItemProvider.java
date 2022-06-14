package com.liu.himusic.ui.adapter.provider;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liu.himusic.R;
import com.liu.himusic.databinding.BannerItemBinding;
import com.liu.himusic.model.bean.BlockBean;
import com.liu.himusic.model.bean.ExtInfo;
import com.liu.himusic.ui.adapter.FindAdapter;
import com.liu.himusic.ui.fragment.banner.ImageNetAdapter;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RoundLinesIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OtherItemProvider extends BaseItemProvider<BlockBean> {
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.banner_item;
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BlockBean blockBean) {

    }
}
