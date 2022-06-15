package com.liu.himusic.ui.adapter.provider

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.liu.himusic.model.bean.BlockBean
import com.liu.himusic.ui.adapter.FindAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.liu.himusic.MainActivity
import com.liu.himusic.R
import com.liu.himusic.databinding.RecommendMusicItemBinding
import com.liu.himusic.model.bean.FindBall
import com.liu.himusic.ui.activity.PlayListActivity
import com.liu.himusic.ui.widget.TabIconView
import com.liucj.lib_common.utils.HiDisplayUtil

class TapItemProvider : BaseItemProvider<BlockBean>() {
    override val itemViewType: Int
        get() = FindAdapter.TAP
    override val layoutId: Int
        get() = R.layout.recommend_music_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, blockBean: BlockBean) {
        var list: List<FindBall> = blockBean.findBall
        val binding = helper.getBinding<RecommendMusicItemBinding>()
        val scrollview = binding!!.horizontalScrollview
        val params = LinearLayout.LayoutParams(
            HiDisplayUtil.dp2px(60f),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        binding!!.mainContainer.removeAllViews()
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
            iconContainer.setOnClickListener {
                //点击item
                val intent = Intent(context, PlayListActivity::class.java)
                context.startActivity(intent)
            }
            binding!!.mainContainer.addView(iconContainer)
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

}