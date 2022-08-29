package com.liu.himusic.ui.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.liu.himusic.model.Const
import com.liu.himusic.model.bean.LoginBean
import com.liu.himusic.model.net.ApiFactory
import com.liu.himusic.model.api.LoginApi
import com.liu.himusic.model.api.PlayListApi
import com.liu.himusic.model.bean.BaseModel
import com.liu.himusic.model.bean.LyricBean
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.SPUtil
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse

class SongPlayerViewModel : ViewModel() {

    fun toLyric(ids: String) {
        ApiFactory.create(PlayListApi::class.java).lyric(ids)
            .enqueue(object : HiCallback<BaseModel<LyricBean>> {
                override fun onSuccess(response: HiResponse<BaseModel<LyricBean>>) {
                    Log.e("onSuccess", response.toString())
                    if (response.code == 200) {
                        val bean = Gson().fromJson(response.rawData, LyricBean::class.java)
                        LiveDataBus.get().with("lyricSuccess")
                            .postValue(bean)
                    }

                }

                override fun onFailed(throwable: Throwable) {
                    Log.e("onFailed", throwable.toString())
                    LiveDataBus.get().with("lyricSuccess")
                        .postValue(null)
                }

            })
    }
}