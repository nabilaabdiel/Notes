package com.example.mynote.ui.add

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.databinding.ActivityAddNoteBinding
import com.example.mynote.ui.home.HomeActivity
import com.example.mynote.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteActivity : BaseActivity<ActivityAddNoteBinding, AddNoteViewModel>(R.layout.activity_add_note) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivAddBack.setOnClickListener {
            openActivity<HomeActivity>()
            finish()

//            binding.ivAddBack.setOnClickListener {
//            if (binding.etaddTittle.isEmptyRequired(R.string.label_must_fill) ||
//                binding.etaddContent.isEmptyRequired(R.string.label_must_fill)
//            ) {
//                return@setOnClickListener
//            }

            val tittle = binding.etaddTittle.textOf()
            val content = binding.etaddContent.textOf()

            viewModel.createNote(tittle, content)
        }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        viewModel.apiResponse.collect {
                            when (it.status) {
                                ApiStatus.LOADING -> loadingDialog.show("saved")
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
//        }
    }
}