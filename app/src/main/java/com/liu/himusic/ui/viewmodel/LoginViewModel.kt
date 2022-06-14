package com.liu.himusic.ui.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.liu.himusic.model.Const
import com.liu.himusic.model.bean.LoginBean
import com.liu.himusic.model.net.ApiFactory
import com.liu.himusic.model.api.LoginApi
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.SPUtil
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse

class LoginViewModel : ViewModel() {

    fun toLogin(phone: String, password: String) {
        ApiFactory.create(LoginApi::class.java).login(phone, password)
            .enqueue(object : HiCallback<LoginBean> {
                override fun onSuccess(response: HiResponse<LoginBean>) {
                    Log.e("onSuccess", response.toString())
                    if (response.code == 200) {
                        var bean: LoginBean =
                            Gson().fromJson(response.rawData, LoginBean::class.java)
                        if (bean.code == 200 && !TextUtils.isEmpty(bean.cookie)) {
                            SPUtil.putString(Const.AUTH_TOKEN, bean.cookie)
                            SPUtil.putString(Const.USER_INFO, response.rawData!!)
                            LiveDataBus.get().with("loginSuccess")
                                .postValue(true)
                            return
                        }
                    }
                    LiveDataBus.get().with("loginSuccess")
                        .postValue(false)
                }

                override fun onFailed(throwable: Throwable) {
                    Log.e("onFailed", throwable.toString())
                    LiveDataBus.get().with("loginSuccess")
                        .postValue(false)
                }

            })
    }
}