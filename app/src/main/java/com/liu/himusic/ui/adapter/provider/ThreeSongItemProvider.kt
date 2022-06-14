package com.liu.himusic.ui.adapter.provider

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.liu.himusic.BR
import com.liu.himusic.R
import com.liu.himusic.databinding.BannerItemBinding
import com.liu.himusic.databinding.RecommendImageItemBinding
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.model.bean.CreativesBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.liu.himusic.ui.adapter.ThreeSongAdapter
import com.liu.himusic.ui.fragment.banner.ImageNetAdapter

class ThreeSongItemProvider : BaseItemProvider<BlockBean>() {
    override val itemViewType: Int
        get() = FindAdapter.HOMEPAGE_BLOCK_STYLE_RCMD
    override val layoutId: Int
        get() = R.layout.recommend_image_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean) {
        val binding = helper.getBinding<RecommendImageItemBinding>()
        binding!!.setVariable(BR.uiElementBean,blockBean!!.uiElement)
//        if (elementBean == null) {
//            elementBean = list[0].uiElement
//        }
//        var title = ""
//        if (elementBean?.subTitle == null) {
//            if (elementBean?.mainTitle != null) {
//                title = elementBean?.mainTitle!!.title
//            }
//        } else {
//            title = elementBean?.subTitle!!.title
//        }
//        binding!!.commendTitle.text = title
//        binding!!.commendMore.text = elementBean?.button!!.text + " >"
        binding!!.mainContainerHeader.visibility = View.VISIBLE
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding!!.mainRecyclerview.layoutManager = linearLayoutManager
//        val snapHelper = MultiSnapHelper(MultiSnapHelper.DEFAULT_GRAVITY,
//            MultiSnapHelper.DEFAULT_INTERVAL, 100f)
        val snapHelper = GravitySnapHelper(Gravity.CENTER)
        binding!!.mainRecyclerview.onFlingListener = null
        snapHelper.attachToRecyclerView( binding!!.mainRecyclerview)
//        val decoration = DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
//        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.list_divider_h)!!)
//        mainRecyclerview.addItemDecoration(decoration)
        // 设置数据
        val  creatives = blockBean.creatives
        binding!!. mainRecyclerview.adapter =
            ThreeSongAdapter(creatives)

    }
}