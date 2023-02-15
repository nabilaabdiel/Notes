package com.example.mynote.ui.register

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.*
import com.crocodic.core.helper.DateTimeHelper
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.data.constant.Const
import com.example.mynote.databinding.ActivityRegisterBinding
import com.example.mynote.ui.home.HomeActivity
import com.example.mynote.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(R.layout.activity_register) {

//    @Inject
//    lateinit var session: CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initClick()

        observe()

    }

    private fun initClick() {
        //tos("Back View: $back")

        binding.btnBack.setOnClickListener{
            onBackPressed()
//            tos("test")
        }

        binding.btnSave.setOnClickListener {
            if (binding.etrName.isEmptyRequired(R.string.label_must_fill) ||
                binding.etrEmail.isEmptyRequired(R.string.label_must_fill) ||
                binding.etrPassword.isEmptyRequired(R.string.label_must_fill) ||
                binding.etrConfirm.isEmptyRequired(R.string.label_must_fill)){
                return@setOnClickListener
            }

            val name = binding.etrName.textOf()
            val email = binding.etrEmail.textOf()
            val password = binding.etrPassword.textOf()
            val confirm = binding.etrConfirm.textOf()

            viewModel.register(name, email, password, confirm)
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Please wait")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity>()
                                finish()
                            }
                            else -> loadingDialog.setResponse(it.message ?:return@collect)
                        }
                    }
                }
            }
        }
    }
}