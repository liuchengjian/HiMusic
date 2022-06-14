package com.liu.himusic.ui.fragment.find.item

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hjq.toast.ToastUtils
import com.liu.himusic.BR
import com.liu.himusic.R
import com.liu.himusic.model.bean.UiElementBean
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.item.HiViewHolder

class HeaderItem(private val elementBean: UiElementBean) :
    HiDataItem<List<UiElementBean>, HeaderItem.HeadersItemHolder>() {
    override fun onBindData(holder: HeadersItemHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.setVariable(BR.uiElementBean,elementBean)
        val commendTitle = holder.itemView.findViewById<TextView>(R.id.commend_title)
        val commendMore = holder.itemView.findViewById<TextView>(R.id.commend_more)
//        commendTitle.text = elementBean.subTitle.title
        commendMore.text = elementBean.button.text +" >"
        commendMore.setOnClickListener {
            ToastUtils.show(commendMore.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): HeadersItemHolder? {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, getItemLayoutRes(),
            parent, false
        )
        return HeadersItemHolder(binding)
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.header_item
    }

    class HeadersItemHolder(val binding: ViewDataBinding) : HiViewHolder(binding.root) {

    }
}