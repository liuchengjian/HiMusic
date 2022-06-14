package com.liu.himusic.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.RecyclerView
import com.liu.himusic.R
import com.liu.himusic.model.bean.CreativesBean
import com.liu.himusic.model.bean.ResourceExtInfoBean
import com.liu.himusic.model.bean.ResourcesBean
import com.liu.himusic.model.bean.UiElementBean
import com.liu.himusic.ui.widget.IconTag
import com.liucj.lib_common.view.MellowImageView
import com.liucj.lib_common.view.loadUrl

class RecommendImageAdapter internal constructor(private val dataList: List<CreativesBean>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var viewFlipperTextStr = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recommend_music_viewflipper, parent, false)
            ViewFlipperHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recommend_music_item_image, parent, false)
            ImageViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cb: CreativesBean = dataList!![position]
        val resources = cb.resources
        val elementBean: UiElementBean = resources!![0].uiElement
        if (holder is ImageViewHolder) {
            val resourceExtInfo: ResourceExtInfoBean = resources[0].resourceExtInfo
            if (resourceExtInfo != null && resourceExtInfo.playCount > 0) {
                holder.playTag.setTagNumText(resourceExtInfo.playCount)
            } else {
                holder.playTag.visibility = View.GONE
            }

            holder.imageView.loadUrl(elementBean.image.imageUrl)
            holder.title.text = elementBean.mainTitle.title
        } else if (holder is ViewFlipperHolder) {
            holder.viewFlipper.removeAllViews()
            var viewFlipperTextStr = ArrayList<String>()
            cb.resources.forEachIndexed { index, resource ->
                var imageItem: View = addImageView(holder.itemView.context, resource, false, index)
                viewFlipperTextStr.add(resource?.uiElement!!.mainTitle.title)
                holder.viewFlipper.addView(imageItem)
            }
            holder.viewFlipper.startFlipping()
            holder.viewFlipperText.text = viewFlipperTextStr[0]
            holder.viewFlipper.inAnimation.setAnimationListener(object :
                Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    holder.viewFlipperText.text =
                        viewFlipperTextStr[holder.viewFlipper.displayedChild]
                }

                override fun onAnimationEnd(p0: Animation?) {
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

            })

        }
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList!![position].creativeType == "scroll_playlist") {
            0
        } else {
            1
        }

    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    open class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_image)
        var title: TextView = itemView.findViewById(R.id.title)
        var playTag: IconTag = itemView.findViewById(R.id.play_tag)

    }

    open class ViewFlipperHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewFlipper: ViewFlipper = view.findViewById(R.id.view_flipper)
        val viewFlipperText: TextView = view.findViewById(R.id.title)


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