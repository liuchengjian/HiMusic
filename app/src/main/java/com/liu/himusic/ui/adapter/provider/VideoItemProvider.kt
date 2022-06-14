package com.liu.himusic.ui.adapter.provider

import android.view.Gravity
import android.view.View
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.liu.himusic.BR
import com.liu.himusic.R
import com.liu.himusic.databinding.BannerItemBinding
import com.liu.himusic.databinding.RecommendImageItemBinding
import com.liu.himusic.model.bean.ExtInfo
import com.liu.himusic.model.bean.ExtInfo.BannersBean
import com.liu.himusic.model.bean.ExtInfoBean
import com.liu.himusic.ui.adapter.AutoPlayAdapter
import com.liu.himusic.ui.fragment.banner.ImageNetAdapter
import com.liu.himusic.utils.AutoPlayUtils
import com.youth.banner.Banner
import com.youth.banner.indicator.RoundLinesIndicator

class VideoItemProvider : BaseItemProvider<BlockBean>() {
    override val itemViewType: Int
        get() = FindAdapter.HOMEPAGE_SLIDE_PLAYABLE_MLOG
    override val layoutId: Int
        get() = R.layout.recommend_image_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean) {
        val binding = helper.getBinding<RecommendImageItemBinding>()
        binding!!.setVariable(BR.uiElementBean,blockBean!!.uiElement)
        val mainRecyclerview = binding!!.mainRecyclerview
        val list: List<ExtInfoBean> = blockBean.extInfoList()
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mainRecyclerview.layoutManager = linearLayoutManager
        val snapHelper = GravitySnapHelper(Gravity.CENTER)
        mainRecyclerview.onFlingListener = null
        snapHelper.attachToRecyclerView(mainRecyclerview)
        snapHelper.setSnapListener { position ->
            AutoPlayUtils.onPlayVideoPosition(
                mainRecyclerview,
                R.id.jz_video,
                if (position - 1 <= 0) 0 else position - 1
            )
        }
        // 设置数据
//        val decoration = DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
//        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.list_divider)!!)
//        mainRecyclerview.addItemDecoration(decoration)
        mainRecyclerview.adapter =
            AutoPlayAdapter(list)
        //通过裁剪实现圆角
        mainRecyclerview.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {}
            override fun onChildViewDetachedFromWindow(view: View) {
                val jzvd = view.findViewById<Jzvd>(R.id.jz_video)
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                    jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.currentUrl)
                ) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos()
                    }
                }
            }
        })

        mainRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx != 0) {
                    AutoPlayUtils.onScrollReleaseAllVideos(
                        linearLayoutManager.findFirstVisibleItemPosition(),
                        linearLayoutManager.findLastVisibleItemPosition(),
                        0.5f
                    )
                }
            }
        })

    }
}