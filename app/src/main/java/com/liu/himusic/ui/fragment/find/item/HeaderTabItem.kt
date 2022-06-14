package com.liu.himusic.ui.fragment.find.item

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import com.liu.himusic.R
import com.liu.himusic.model.bean.FindBall
import com.liu.himusic.ui.widget.TabIconView
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder
import com.liucj.lib_common.utils.HiDisplayUtil

open class HeaderTabItem(val list: List<FindBall>) :
    HiDataItem<List<FindBall>, HiViewHolder>() {
    val iconTitles = arrayOf("每日推荐", "私人FM", "歌单", "排行榜", "直播", "数字专辑", "有声书", "关注新歌", "歌房", "游戏专区")
    val icons = intArrayOf(
        R.drawable.icon_oneday,
        R.drawable.icon_find_fm,
        R.drawable.icon_music_list,
        R.drawable.icon_rank,
        R.drawable.icon_logo,
        R.drawable.icon_logo, R.drawable.icon_logo,
        R.drawable.icon_logo, R.drawable.icon_logo, R.drawable.icon_logo
    )

    @SuppressLint("WrongConstant")
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        val mainContainer = holder.itemView.findViewById<LinearLayout>(R.id.main_container)
        val scrollview =
            holder.itemView.findViewById<HorizontalScrollView>(R.id.horizontal_scrollview)
        val params = LinearLayout.LayoutParams(
            HiDisplayUtil.dp2px(60f),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        mainContainer.removeAllViews()
        scrollview.scrollTo(0, 0)
        list.forEachIndexed { index, findBall: FindBall ->
            val iconContainer = LinearLayout(context)
            iconContainer.orientation = LinearLayout.VERTICAL
            params.leftMargin = HiDisplayUtil.dp2px(10f)
            params.rightMargin = HiDisplayUtil.dp2px(10f)
            iconContainer.layoutParams = params
            iconContainer.gravity = Gravity.CENTER_HORIZONTAL
            iconContainer.addView(getIconView(findBall.iconUrl, context, index))
            iconContainer.addView(getIconTextView(context, findBall.name))
            mainContainer.addView(iconContainer)
        }

    }

    private fun getIconView(url: String, context: Context, index: Int): View {
        val tabItem = TabIconView(context)
        tabItem.setTabIcon(url)
        tabItem.setTabDay(index)
        tabItem.gravity = Gravity.CENTER_HORIZONTAL
        return tabItem
    }

    private fun getIconTextView(
        context: Context,
        iconTitle: String
    ): View {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = HiDisplayUtil.dp2px(5f)
        var iconText = TextView(context)
        iconText.text = iconTitle
        iconText.layoutParams = params
        iconText.gravity = Gravity.CENTER
        return iconText
    }


    override fun getItemLayoutRes(): Int {
        return R.layout.recommend_music_item
    }

}