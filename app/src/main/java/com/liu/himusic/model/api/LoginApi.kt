package com.liu.himusic.model.api

import com.liu.himusic.model.bean.LoginBean
import com.liucj.lib_network.restful_kt.HiCall
import com.liucj.lib_network.restful_kt.annotation.Filed
import com.liucj.lib_network.restful_kt.annotation.GET

interface LoginApi {
    @GET("login/cellphone")
    fun login(
        @Filed("phone") phone: String,
        @Filed("password") password: String
    ): HiCall<LoginBean>
}