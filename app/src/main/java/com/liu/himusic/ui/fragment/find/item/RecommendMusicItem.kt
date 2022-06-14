package com.liu.himusic.ui.fragment.find.item

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.liu.himusic.R
import com.liu.himusic.model.bean.*
import com.liu.himusic.ui.widget.IconTag
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder
import com.liucj.lib_common.view.MellowImageView
import com.liucj.lib_common.view.loadUrl

class RecommendMusicItem(private val recommendModel: RecommendBean) :
    HiDataItem<RecommendBean, HiViewHolder>(recommendModel) {


    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        val mainContainer: LinearLayout = holder.view.findViewById(R.id.main_container)
        val viewFlipperContainer: View =
            View.inflate(context, R.layout.recommend_music_viewflipper, null)
        val viewFlipper: ViewFlipper = viewFlipperContainer!!.findViewById(R.id.view_flipper)
        val viewFlipperText: TextView = viewFlipperContainer!!.findViewById(R.id.title)
        mainContainer.removeAllViews()
        viewFlipper.removeAllViews()
        var viewFlipperTextStr = ArrayList<String>()
        for (cb: CreativesBean in recommendModel.creatives) {
            if (cb.creativeType.equals("scroll_playlist")) {
                cb.resources.forEachIndexed { index, resource ->
                    var imageItem: View = addImageView(context, resource, false, index)
                    viewFlipperTextStr.add(resource?.uiElement!!.mainTitle.title)
                    viewFlipper.addView(imageItem)
                }
                viewFlipper.startFlipping()
                viewFlipperText.text = viewFlipperTextStr[0]

                viewFlipperContainer.setPadding(10, 0, 10, 0)
                mainContainer.addView(viewFlipperContainer)
            } else {
                var imageItem: View = addImageView(context, cb.resources[0], true, -1)
                imageItem.setPadding(10, 0, 10, 0)
                mainContainer.addView(imageItem)
            }
        }
        viewFlipper.inAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                viewFlipperText.text = viewFlipperTextStr[viewFlipper.displayedChild]
            }

            override fun onAnimationEnd(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.recommend_music_item
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