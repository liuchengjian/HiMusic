package com.liu.himusic.ui.adapter.provider

import android.view.Gravity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.liu.himusic.BR
import com.liu.himusic.R
import com.liu.himusic.databinding.RecommendImageItemBinding
import com.liu.himusic.ui.adapter.RecommendImageAdapter

class ImageItemProvider : BaseItemProvider<BlockBean?>() {
    override val itemViewType: Int
        get() = FindAdapter.HOMEPAGE_SLIDE_PLAYLIST
    override val layoutId: Int
        get() = R.layout.recommend_image_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean?) {
        val binding = helper.getBinding<RecommendImageItemBinding>()
        binding!!.setVariable(BR.uiElementBean,blockBean!!.uiElement)
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding!!.mainRecyclerview.layoutManager = linearLayoutManager
        val snapHelper = GravitySnapHelper(Gravity.CENTER)
        binding!!.mainRecyclerview.onFlingListener = null
        snapHelper.attachToRecyclerView( binding!!.mainRecyclerview)
        // 设置数据
        binding!!.mainRecyclerview.adapter = RecommendImageAdapter(blockBean!!.creatives)
    }
}