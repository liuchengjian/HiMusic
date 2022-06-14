package com.liu.himusic

import android.app.Application
import android.view.Gravity
import com.hjq.toast.ToastUtils
import com.lzx.starrysky.StarrySky.init

class HiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化 Toast 框架
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.TOP)
        init()
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
}