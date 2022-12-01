package com.liu.himusic.ui.fragment

import android.app.ProgressDialog
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hjq.http.listener.OnHttpListener
import com.hjq.toast.ToastUtils
import com.liu.himusic.R
import com.liu.himusic.http.model.HttpData
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.PixUtils
import com.liucj.lib_common.view.HiNavigationBar
import okhttp3.Call

abstract class BaseHomeFragment : Fragment(), OnHttpListener<Any> {

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

    /** 加载对话框  */
    private var mDialog: ProgressDialog? = null

    /** 对话框数量  */
    private var mDialogTotal = 0

    /**
     * 当前加载对话框是否在显示中
     */
    open fun isShowDialog(): Boolean {
        return mDialog != null && mDialog!!.isShowing
    }

    /**
     * 显示加载对话框
     */
    open fun showDialog() {
        if (mDialog == null) {
            mDialog = ProgressDialog(activity)
            mDialog!!.setMessage(resources.getString(R.string.http_loading))
            mDialog!!.setCancelable(false)
            mDialog!!.setCanceledOnTouchOutside(false)
        }
        if (!mDialog!!.isShowing) {
            mDialog!!.show()
        }
        mDialogTotal++
    }

    /**
     * 隐藏加载对话框
     */
    open fun hideDialog() {
        if (mDialogTotal == 1) {
            if (mDialog != null && mDialog!!.isShowing) {
                mDialog!!.dismiss()
            }
        }
        if (mDialogTotal > 0) {
            mDialogTotal--
        }
    }

    override fun onStart(call: Call?) {
//        showDialog()
    }

     override fun onSucceed(result: Any?) {
//         if (result is HttpData<*>) {
//             ToastUtils.show(result.message)
//         }
     }
    override fun onFail(e: Exception) {
        ToastUtils.show(e.message)
    }

    override fun onEnd(call: Call?) {
//        hideDialog()
    }


 }