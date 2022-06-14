package com.liu.himusic.ui.fragment.find.item

import com.liu.himusic.R
import com.liu.himusic.model.bean.ExtInfo
import com.liu.himusic.ui.fragment.banner.ImageNetAdapter
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder
import com.youth.banner.Banner
import com.youth.banner.indicator.RoundLinesIndicator

open class BannerNewItem(val list: List<ExtInfo.BannersBean>) :
    HiDataItem<List<ExtInfo.BannersBean>, HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        var adapter = ImageNetAdapter(list)
        var banner =
            holder.itemView.findViewById<Banner<ExtInfo.BannersBean,
                    ImageNetAdapter>>(R.id.banner)
        banner.setAdapter(adapter)
        banner.setDatas(list)

//        banner.setIndicatorMargins(
//            IndicatorConfig.Margins(
//                BannerUtils.dp2px(12f), 0,
//                BannerUtils.dp2px(12f), BannerUtils.dp2px(20f)
//            )
//        )
        banner.indicator =RoundLinesIndicator(context)
//        banner.setIndicatorSelectedWidth(BannerUtils.dp2px(20f) )
        banner.start()
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.banner_item
    }

}