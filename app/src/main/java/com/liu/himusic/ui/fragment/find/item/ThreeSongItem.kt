package com.liu.himusic.ui.fragment.find.item

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.liu.himusic.R
import com.liu.himusic.model.*
import com.liu.himusic.model.bean.CreativesBean
import com.liu.himusic.model.bean.ResourceExtInfoBean
import com.liu.himusic.model.bean.ResourcesBean
import com.liu.himusic.model.bean.UiElementBean
import com.liu.himusic.ui.adapter.ThreeSongAdapter
import com.liu.himusic.ui.widget.IconTag
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder
import com.liucj.lib_common.view.MellowImageView
import com.liucj.lib_common.view.loadUrl


class ThreeSongItem(
    private var elementBean: UiElementBean?,
    private val list: List<CreativesBean>
) :
    HiDataItem<List<CreativesBean>, HiViewHolder>() {


    override fun onBindData(holder: HiViewHolder, position: Int) {
        if (position > list.size) {
            return
        }
        val context = holder.itemView.context
        val mainRecyclerview: RecyclerView = holder.view.findViewById(R.id.main_recyclerview)
        val mainContainerHeader: LinearLayout = holder.view.findViewById(R.id.main_container_header)
        val commendTitle: TextView = holder.view.findViewById(R.id.commend_title)
        val commendMore: TextView = holder.view.findViewById(R.id.commend_more)
        if (elementBean == null) {
            elementBean = list[0].uiElement
        }
        var title = ""
        if (elementBean?.subTitle == null) {
            if (elementBean?.mainTitle != null) {
                title = elementBean?.mainTitle!!.title
            }
        } else {
            title = elementBean?.subTitle!!.title
        }
        commendTitle.text = title
        commendMore.text = elementBean?.button!!.text + " >"
        mainContainerHeader.visibility = View.VISIBLE
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mainRecyclerview.layoutManager = linearLayoutManager
//        val snapHelper = MultiSnapHelper(MultiSnapHelper.DEFAULT_GRAVITY,
//            MultiSnapHelper.DEFAULT_INTERVAL, 100f)
        val snapHelper = GravitySnapHelper(Gravity.CENTER)
        mainRecyclerview.onFlingListener = null
        snapHelper.attachToRecyclerView(mainRecyclerview)
//        val decoration = DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
//        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.list_divider_h)!!)
//        mainRecyclerview.addItemDecoration(decoration)
        // 设置数据
        mainRecyclerview.adapter =
            ThreeSongAdapter(list)

    }

    override fun getItemLayoutRes(): Int {
        return R.layout.recommend_image_item
    }

    private fun addImageView(
        context: Context,
        resource: ResourcesBean,
        hasTitle: Boolean, index: Int
    ): View {
        val elementBean: UiElementBean = resource.uiElement
        val resourceExtInfo: ResourceExtInfoBean = resource.resourceExtInfo
        val view = View.inflate(context, R.layout.recommend_music_item_image, null)
        val imageView: MellowImageView = view.findViewById(R.id.iv_image)
        val title: TextView = view.findViewById(R.id.title)
        val playTag: IconTag = view.findViewById(R.id.play_tag)
        if (index < 0) {
            playTag.setTagNumText(resourceExtInfo.playCount)
        } else {
            playTag.visibility = View.GONE
        }

        imageView.loadUrl(elementBean.image.imageUrl)
        imageView.tag = index
        title.text = elementBean.mainTitle.title
        title.visibility = if (hasTitle) View.VISIBLE else View.GONE
        return view
    }

}


