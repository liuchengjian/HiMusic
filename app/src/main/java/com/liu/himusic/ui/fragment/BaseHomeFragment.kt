package com.liu.himusic.ui.fragment

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.liu.himusic.R
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.PixUtils
import com.liucj.lib_common.view.HiNavigationBar

abstract class BaseHomeFragment : Fragment() {


     fun setNavigationLeftDrawer(navigationBar: HiNavigationBar) {
        val tvLeft = TextView(activity)
        tvLeft.id = View.generateViewId()
        tvLeft.setPadding(5, 5, 5, 5)
        tvLeft.background = requireActivity().getDrawable(R.mipmap.category)
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.leftMargin = PixUtils.dp2px(10)
        params.topMargin = PixUtils.dp2px(10)
        navigationBar.addLeftView(tvLeft, params)
        tvLeft.setOnClickListener {
            LiveDataBus.get().with("drawer_layout")
                .setValue(true)
        }
    }

    //检测 宿主 是否还存活
    open fun isAlive(): Boolean {
        return !(isRemoving || isDetached || activity == null)
    }
}