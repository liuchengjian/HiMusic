package com.liu.himusic.model.api

import com.liu.himusic.model.bean.*
import com.liucj.lib_network.bean.Cursor
import com.liucj.lib_network.restful_kt.HiCall
import com.liucj.lib_network.restful_kt.annotation.Filed
import com.liucj.lib_network.restful_kt.annotation.GET
import com.liucj.lib_network.restful_kt.annotation.POST

interface FindApi {
    @GET("homepage/block/page")
    fun toFind(
        @Filed("refresh") refresh: Boolean,
        @Filed("cursor") cursor: String,
        @Filed("timestamp") timestamp: Long,
        @Filed("cookie") cookie: String
    ): HiCall<BaseModel<FindBean>>

    @POST("homepage/block/page")
    fun toFind(
        @Filed("refresh") refresh: Boolean,
        @Filed("timestamp") timestamp: Long,
        @Filed("cookie") cookie: String
    ): HiCall<BaseModel<FindBean>>

    @GET("homepage/dragon/ball")
    fun findBall(): HiCall<BaseModel<List<FindBall>>>

    @GET("mlog/to/video")
    fun toVideoId(@Filed("id") id: String): HiCall<BaseModel<String>>

    @GET("video/url")
    fun toVideoUrl(@Filed("id") id: String): HiCall<VideoPlayUrlBean>

    @GET("song/url")
    fun toSongUrl(@Filed("id") id: String): HiCall<BaseModel<List<SongPlayBean>>>

    @GET("topic/detail")
    fun topicDetail(@Filed("actid") actid: String): HiCall<TopicDetailBean>

}