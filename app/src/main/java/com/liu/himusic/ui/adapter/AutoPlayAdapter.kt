package com.liu.himusic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hjq.toast.ToastUtils
import com.liu.himusic.R
import com.liu.himusic.model.bean.ExtInfoBean
import com.liu.himusic.ui.adapter.AutoPlayAdapter.AutoViewHolder
import com.liu.himusic.ui.widget.JzvdStdView

internal class AutoPlayAdapter internal constructor(private val dataList: List<ExtInfoBean>?) :
    RecyclerView.Adapter<AutoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.auto_play_item, parent, false)
        return AutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutoViewHolder, position: Int) {
        val extInfo: ExtInfoBean = dataList!![position]
        val resource = extInfo.resource
        val video = resource.mlogBaseData.video
        val jzvdStdView = JzvdStdView(holder.itemView.context)
        jzvdStdView.setOnClickListener{
            ToastUtils.show("点击了视频")
        }
        if (video != null) {
            jzvdStdView.toVideoId(resource.mlogBaseData.id, resource,position)
            holder.container.removeAllViews()
            holder.container.addView(jzvdStdView)
        }

    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    open class AutoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var container: ConstraintLayout = view.findViewById(R.id.container)

    }
}