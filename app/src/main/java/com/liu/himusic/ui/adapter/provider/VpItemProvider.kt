package com.liu.himusic.ui.adapter.provider

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.liu.himusic.R
import com.liu.himusic.databinding.ItemVpGalleryBinding
import com.liu.himusic.model.bean.ResourcesBean
import com.liu.himusic.ui.fragment.banner.ImageGalleryAdapter
import com.youth.banner.listener.OnPageChangeListener

class VpItemProvider : BaseItemProvider<BlockBean>() {
    override val itemViewType: Int
        get() = FindAdapter.HOT_TOPIC
    override val layoutId: Int
        get() = R.layout.item_vp_gallery

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean) {
        val binding = helper.getBinding<ItemVpGalleryBinding>()

        binding!!.title.text = blockBean.uiElement.subTitle.title
        val resources = ArrayList<ResourcesBean>()
        val creatives = blockBean.creatives
        for (creativesBean in creatives) {
            resources.addAll(creativesBean.resources)
        }
        binding!!.num.text = "1/" + resources.size

        var adapter = ImageGalleryAdapter(resources)
        var banner =  binding!!.banner
        banner.setBannerGalleryEffect(20, 20)
        banner.setAdapter(adapter)
        banner.setDatas(resources)
        banner.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding!!.num.text = (position + 1).toString() + "/" + resources.size
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}