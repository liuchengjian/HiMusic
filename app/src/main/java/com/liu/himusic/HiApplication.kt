package com.liu.himusic

import android.app.Application
import android.view.Gravity
import com.hjq.http.EasyConfig
import com.hjq.http.config.IRequestInterceptor
import com.hjq.http.config.IRequestServer
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.http.request.HttpRequest
import com.hjq.toast.ToastUtils
import com.liu.himusic.http.model.RequestHandler
import com.liu.himusic.http.server.ReleaseServer
import com.liu.himusic.http.server.TestServer
import com.lzx.starrysky.StarrySky.init
import okhttp3.OkHttpClient

class HiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化 Toast 框架
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.TOP)
        init()
        initService()
    }

    private fun init(){
        init(this)
            .setDebug(false) //是否debug，区别就是是否打印一些内部 log
            .connService(true) //是否需要后台服务，默认true
            .isStartService(true) //是否需要 startService，默认false
            .onlyStartService(false) //是否只是 startService 而不需要 startForegroundService，默认true
            .setNotificationSwitch(true) //通知栏开关
            .setOpenCache(true) //是否开启缓存
            //        .setCacheDestFileDir(..)             //配置缓存路径
            //        .setCacheMaxBytes(..)                //配置最大缓存大小，默认512 * 1024 * 1024
            //        .setCache(..)                        //缓存自定义实现
            //        .setAutoManagerFocus(..)             //是否自动焦点管理
            //        .setPlayback(..)                     //自定义实现播放器
            //        .setImageLoader(..)                  //配置自定义图片加载器
            //        .setGlobalPlaybackStageListener(..)  //配置全局的播放状态监听器
            .apply()
    }

    private fun initService(){
        // 网络请求框架初始化

        // 网络请求框架初始化
        val server: IRequestServer = if (BuildConfig.DEBUG) {
            TestServer()
        } else {
            ReleaseServer()
        }

        val okHttpClient = OkHttpClient.Builder()
            .build()

        EasyConfig.with(okHttpClient) // 是否打印日志
            //.setLogEnabled(BuildConfig.DEBUG)
            // 设置服务器配置
            .setServer(server) // 设置请求处理策略
            .setHandler(RequestHandler(this)) // 设置请求参数拦截器
            .setInterceptor(object : IRequestInterceptor {
                override fun interceptArguments(
                    httpRequest: HttpRequest<*>,
                    params: HttpParams,
                    headers: HttpHeaders
                ) {
                    headers.put("timestamp", System.currentTimeMillis().toString())
                }
            }) // 设置请求重试次数
            .setRetryCount(1) // 设置请求重试时间
//            .setRetryTime(2000) // 添加全局请求参数
            .addParam("token", "6666666") // 添加全局请求头
            //.addHeader("date", "20191030")
            .into()
    }
}