package com.example.mynote.ui.login

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.base64encrypt
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.helper.DateTimeHelper
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.data.constant.Const
import com.example.mynote.databinding.ActivityLoginBinding
import com.example.mynote.ui.home.HomeActivity
import com.example.mynote.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tokenApi()

        binding.btnRegister.setOnClickListener {
            openActivity<RegisterActivity>() {}
        }

        binding.btnLogin.setOnClickListener {
            if (binding.etlEmail.isEmptyRequired(R.string.label_must_fill) ||
                binding.etlPassword.isEmptyRequired(R.string.label_must_fill)
            ) {
                return@setOnClickListener
            }

            val email = binding.etlEmail.textOf()
            val password = binding.etlPassword.textOf()

            viewModel.login(email, password)
            Timber.d("Password: $password")

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Please wait ..")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity>()
                                finish()
                            }
                            else -> loadingDialog.setResponse(it.message ?: return@collect)
                        }
                    }
                }
            }
        }
    }

    private fun tokenApi() {
        val dateNow = DateTimeHelper().dateNow()
        val tokenInit = "$dateNow|rahasia"
        val tokenEncrypt = tokenInit.base64encrypt()
        session.setValue(Const.TOKEN.API_TOKEN, tokenEncrypt)
        Timber.d("cek token : $tokenInit")

        lifecycleScope.launch {
            viewModel.getToken()
        }
    }
}