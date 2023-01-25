package com.example.mynote.ui.splash

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivitySplashBinding
import com.example.mynote.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.splash {
            if (it) {
                openActivity<LoginActivity>()
            }
            finish()
        }
    }
}