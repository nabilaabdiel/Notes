package com.example.mynote.ui.add

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.extension.tos
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.databinding.ActivityAddNoteBinding
import com.example.mynote.ui.home.HomeActivity
import com.example.mynote.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteActivity :
    BaseActivity<ActivityAddNoteBinding, AddNoteViewModel>(R.layout.activity_add_note) {

    private var id: String? = null
    private var title: String? = null
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")

        binding.etaddTittle.setText(title)
        binding.etaddContent.setText(content)

        binding.ivAddBack.setOnClickListener {
            openActivity<HomeActivity>()
            finish()

            val tittle = binding.etaddTittle.textOf()
            val content = binding.etaddContent.textOf()

            viewModel.createNote(tittle, content)
        }

        binding.ivSave.setOnClickListener {
            openActivity<HomeActivity>()
            finish()

            val tittle = binding.etaddTittle.textOf()
            val content = binding.etaddContent.textOf()

            viewModel.createNote(tittle, content)
        }

        binding.ivDelete.setOnClickListener {
            if (id != null) {
                viewModel.deleteNote(id!!)
            } else {
                tos("Tidak ada data")
            }
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
    }
}