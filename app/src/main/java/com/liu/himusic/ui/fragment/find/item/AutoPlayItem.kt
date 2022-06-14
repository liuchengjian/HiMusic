package com.liu.himusic.ui.fragment.find.item

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import cn.jzvd.Jzvd
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.liu.himusic.R
import com.liu.himusic.model.bean.ExtInfoBean
import com.liu.himusic.ui.adapter.AutoPlayAdapter
import com.liu.himusic.utils.AutoPlayUtils
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder

class AutoPlayItem(val list: List<ExtInfoBean>) :
    HiDataItem<List<ExtInfoBean>, HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        val mainRecyclerview: RecyclerView = holder.view.findViewById(R.id.main_recyclerview)

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
            OnChildAttachStateChangeListener {
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

    override fun getItemLayoutRes(): Int {
        return R.layout.recommend_image_item
    }
}