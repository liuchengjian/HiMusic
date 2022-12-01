package com.liu.himusic.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.hjq.http.EasyHttp
import com.hjq.http.listener.HttpCallback
import com.hjq.toast.ToastUtils
import com.liu.himusic.http.api.FindHomeApi
import com.liu.himusic.http.model.HttpData
import com.liu.himusic.model.api.FindApi
import com.liu.himusic.model.bean.BaseModel
import com.liu.himusic.model.bean.FindBall
import com.liu.himusic.model.bean.FindBean
import com.liu.himusic.model.net.ApiFactory
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.SPUtil
import com.liucj.lib_common.utils.TimeUtils
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse

class FindViewModel : ViewModel() {

    fun toFind(refresh: Boolean, cursor: String?) {
        var hiCall = if (cursor == null)
            ApiFactory.create(FindApi::class.java).toFind(
                refresh,
                TimeUtils.getTime(),
                SPUtil.getString("cookie").toString()
            )
        else
            ApiFactory.create(FindApi::class.java).toFind(
                refresh,
                cursor,
                TimeUtils.getTime(),
                SPUtil.getString("cookie").toString()
            )
        hiCall.enqueue(object : HiCallback<BaseModel<FindBean>> {
            override fun onSuccess(response: HiResponse<BaseModel<FindBean>>) {
                if (response.code == 200) {
                    val bean: FindBean? = response.data?.data
                    bean?.isRefresh = refresh
                    LiveDataBus.get().with("FindPage")
                        .postValue(bean)
                } else {
                    LiveDataBus.get().with("FindPage")
                        .postValue(null)
                }
            }

            override fun onFailed(throwable: Throwable) {
                LiveDataBus.get().with("FindPage")
                    .postValue(null)
            }
        })
    }

    fun findBall() {
        ApiFactory.create(FindApi::class.java).findBall()
            .enqueue(object : HiCallback<BaseModel<List<FindBall>>> {
                override fun onSuccess(response: HiResponse<BaseModel<List<FindBall>>>) {
                    if (response.code == 200) {
                        val list: List<FindBall>? = response.data?.data
                        LiveDataBus.get().with("FindBall")
                            .postValue(list)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    LiveDataBus.get().with("FindBall")
                        .postValue(null)
                }
            })
    }
}