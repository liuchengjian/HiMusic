package com.liu.himusic.model.api

import com.liu.himusic.model.bean.BaseModel
import com.liu.himusic.model.bean.LyricBean
import com.liu.himusic.model.bean.PlatListBean
import com.liucj.lib_network.restful_kt.HiCall
import com.liucj.lib_network.restful_kt.annotation.Filed
import com.liucj.lib_network.restful_kt.annotation.GET
import com.liucj.lib_network.restful_kt.annotation.POST

interface PlayListApi {
    @POST("recommend/songs")
    fun songs(
        @Filed("timestamp") timestamp: Long,
        @Filed("cookie") cookie: String
    ): HiCall<BaseModel<PlatListBean>>

    @GET("/lyric")
    fun lyric(
        @Filed("id") id: String,
    ): HiCall<BaseModel<LyricBean>>
}