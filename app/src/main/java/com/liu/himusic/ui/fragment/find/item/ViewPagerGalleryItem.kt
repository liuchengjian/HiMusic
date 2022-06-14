package com.liu.himusic.ui.fragment.find.item

import android.widget.TextView
import com.liu.himusic.R
import com.liu.himusic.model.bean.CreativesBean
import com.liu.himusic.model.bean.GalleryBean
import com.liu.himusic.model.bean.ResourcesBean
import com.liu.himusic.model.bean.UiElementBean
import com.liu.himusic.ui.fragment.banner.ImageGalleryAdapter
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder
import com.youth.banner.Banner
import com.youth.banner.listener.OnPageChangeListener

/**
 * 画廊效果
 */
class ViewPagerGalleryItem(
    private val elementBean: UiElementBean,
    val creatives: List<CreativesBean>
) :
    HiDataItem<List<GalleryBean>, HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.title)
        val num = holder.itemView.findViewById<TextView>(R.id.num)

        title.text = elementBean.subTitle.title
        val resources = ArrayList<ResourcesBean>()
        for (creativesBean in creatives) {
            resources.addAll(creativesBean.resources)
        }
        num.text = "1/" + resources.size

        var adapter = ImageGalleryAdapter(resources)
        var banner =
            holder.itemView.findViewById<Banner<ResourcesBean,
                    ImageGalleryAdapter>>(R.id.banner)
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
                num.text = (position + 1).toString() + "/" + resources.size
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.item_vp_gallery
    }

}