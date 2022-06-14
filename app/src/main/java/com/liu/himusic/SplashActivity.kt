package com.liu.himusic

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.liu.himusic.model.Const
import com.liu.himusic.ui.activity.LoginActivity
import com.liucj.lib_common.utils.SPUtil
import com.liucj.lib_common.utils.StatusBarKt

open class SplashActivity : AppCompatActivity() {
    private var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarKt.setStatusBar(this, false, statusBarColor = Color.TRANSPARENT, translucent = true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initData()
    }



    private fun initData() {
        startCountDownTime()
    }

    private fun startCountDownTime() {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val authToken: String? =
                    SPUtil.getString(Const.AUTH_TOKEN)
                if (TextUtils.isEmpty(authToken)) {
                    startActivity(this@SplashActivity,LoginActivity::class.java)
                } else {
                    startActivity(this@SplashActivity,MainActivity::class.java)
                }
                finishTimer();
            }
        }.start()
    }


     fun finishTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            countDownTimer = null
        }
    }

    fun startActivity(activity: Activity, cls: Class<*>?) {
        val intent = Intent(activity, cls)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }



}
