package com.example.mynote.ui.splash

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.tos
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.data.constant.Const
import com.example.mynote.databinding.ActivitySplashBinding
import com.example.mynote.ui.home.HomeActivity
import com.example.mynote.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userLogin = session.getString(Const.USER.PROFILE)

        tos("Login: $userLogin")

        viewModel.splash {
            if (userLogin == "Login") {
                openActivity<HomeActivity>()
            } else {
                openActivity<LoginActivity>()
            }
            finish()
        }
    }
}