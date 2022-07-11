package com.liu.himusic.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import cn.jzvd.Jzvd
import com.google.gson.Gson
import com.liu.himusic.R
import com.liu.himusic.model.bean.BaseModel
import com.liu.himusic.model.bean.ExtInfoBean
import com.liu.himusic.model.bean.VideoPlayUrlBean
import com.liu.himusic.model.net.ApiFactory
import com.liu.himusic.model.api.FindApi
import com.liucj.lib_common.view.loadUrl
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse
import com.youth.banner.util.BannerUtils

class JzvdStdView : ConstraintLayout {

    private var video: VideoPlayerItem?
    private var iconTag: IconTag?
    private var title: TextView?

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    constructor(context: Context, attributes: AttributeSet?, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    ) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.jzcd_std_view, this, true)
        video = view.findViewById(R.id.jz_video)
        video!!.isEnabled = false
        BannerUtils.setBannerRound(video, 20f)
        iconTag = view.findViewById(R.id.play_video_tag)
        title = view.findViewById(R.id.video_title)
        video!!.widthRatio = 3
        video!!.heightRatio = 4
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP)
//        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //请求所有父控件拦截Touch事件
        parent.requestDisallowInterceptTouchEvent(false)
        return super.dispatchTouchEvent(ev)
    }

    fun toVideoId(id: String, resource: ExtInfoBean.Resource, pos: Int) {
        ApiFactory.create(FindApi::class.java).toVideoId(id)
            .enqueue(object : HiCallback<BaseModel<String>> {
                override fun onSuccess(response: HiResponse<BaseModel<String>>) {
                    if (response.code == 200) {
                        val model = response.data as String//A9504F36F1DE3ACBF9FF63A0132BFD5F
                        toVideoUrl(model!!, resource, pos)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                }
            })
    }

    private fun toVideoUrl(id: String, resource: ExtInfoBean.Resource, pos: Int) {
        ApiFactory.create(FindApi::class.java).toVideoUrl(id)
            .enqueue(object : HiCallback<VideoPlayUrlBean> {
                override fun onSuccess(response: HiResponse<VideoPlayUrlBean>) {
                    if (response.code == 200) {
                        val rawData = response.rawData
                        val videoPlayUrlBean: VideoPlayUrlBean =
                            Gson().fromJson(rawData, VideoPlayUrlBean::class.java) ?: return
                        val urlBean: VideoPlayUrlBean.UrlsDTO? =
                            if (videoPlayUrlBean.urls.size > 0) videoPlayUrlBean.urls[0] else null

                        val videoBean = resource.mlogBaseData.video
                        if (videoBean != null) {
                            video!!.setUp(
                                urlBean?.url,
                                "", Jzvd.SCREEN_NORMAL
                            )
                            video!!.posterImageView.scaleType = ImageView.ScaleType.CENTER_CROP
                            video!!.posterImageView.loadUrl(videoBean.frameUrl)
                            title!!.text = resource.mlogBaseData.originalTitle
                            iconTag!!.setTagNumText(resource.mlogExtVO.playCount.toLong())
                            if (pos == 0) {
                                if (video!!.state != Jzvd.STATE_PLAYING) video!!.startButton.performClick()
                            }

                        }
                    }
                }

                override fun onFailed(throwable: Throwable) {

                }
            })
    }

    public fun getVideo(): VideoPlayerItem {
        return video!!
    }
}