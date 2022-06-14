package com.liu.himusic.ui.adapter.provider

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.liu.himusic.R
import com.liu.himusic.databinding.BannerItemBinding
import com.liu.himusic.model.bean.ExtInfo
import com.liu.himusic.model.bean.ExtInfo.BannersBean
import com.liu.himusic.ui.fragment.banner.ImageNetAdapter
import com.youth.banner.Banner
import com.youth.banner.indicator.RoundLinesIndicator

class BannerItemProvider : BaseItemProvider<BlockBean>() {
    override val itemViewType: Int
        get() = FindAdapter.BANNER
    override val layoutId: Int
        get() = R.layout.banner_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean) {
        val extInfo = blockBean.extInfoBanner() ?: return
        val list = extInfo.banners
        if (list.isEmpty()) {
            return
        }
        val adapter = ImageNetAdapter(list)
        val binding = helper.getBinding<BannerItemBinding>()
        val banner = binding!!.banner
        banner.setAdapter(adapter)
        banner.setDatas(list)
//        banner.indicator = RoundLinesIndicator(context)
        //        banner.setIndicatorSelectedWidth(BannerUtils.dp2px(20f) )
        banner.start()
    }
}