package com.liu.himusic.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.liu.himusic.model.net.ApiFactory
import com.liu.himusic.model.api.PlayListApi
import com.liu.himusic.model.bean.BaseModel
import com.liu.himusic.model.bean.PlatListBean
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.SPUtil
import com.liucj.lib_common.utils.TimeUtils
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse

class PlayListViewModel : ViewModel() {

    fun toPlayList() {
        ApiFactory.create(PlayListApi::class.java).songs(
            TimeUtils.getTime(),
            SPUtil.getString("cookie").toString()
        ).enqueue(object : HiCallback<BaseModel<PlatListBean>> {
                override fun onSuccess(response: HiResponse<BaseModel<PlatListBean>>) {
                    if (response.code == 200) {
                        val platListBean: PlatListBean? = response.data?.data
                        LiveDataBus.get().with("PlayList")
                            .postValue(platListBean)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    LiveDataBus.get().with("PlayList")
                        .postValue(null)
                }
            })
    }
}