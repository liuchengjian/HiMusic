package com.liu.himusic.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hjq.toast.ToastUtils
import com.liu.himusic.MainActivity
import com.liu.himusic.R
import com.liu.himusic.databinding.ActivityLoginBinding
import com.liu.himusic.databinding.ActivityMainBinding
import com.liu.himusic.ui.viewmodel.LoginViewModel
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.StatusBarKt

class LoginActivity : AppCompatActivity() {
    private lateinit var viewMolder: LoginViewModel
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarKt.setStatusBar(this, true, statusBarColor = Color.TRANSPARENT, translucent = false)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initView()
    }

    private fun initView() {
        viewMolder = ViewModelProvider(this)[LoginViewModel::class.java]
        binding!!.btnLogin.setOnClickListener {
            var phone: String = binding!!.etPhone.text.toString().trim()
            var password: String = binding!!.etPwd.text.toString().trim()
            if (TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(password)
            ) {
                ToastUtils.show("请输入电话和密码！！")
            }
            viewMolder.toLogin(phone, password)
        }

        LiveDataBus.get().with("loginSuccess").observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                if (t == true) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    ToastUtils.show("登录成功！")
                    finish()
                }
            }
        })
    }
}