package com.example.mynote.ui.add

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.extension.tos
import com.crocodic.core.helper.DateTimeHelper
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityAddNoteBinding
import com.example.mynote.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@AndroidEntryPoint
class AddNoteActivity :
    BaseActivity<ActivityAddNoteBinding, AddNoteViewModel>(R.layout.activity_add_note) {

    private var id: String? = null
    private var title: String? = null
    private var content: String? = null
    private var updatedAt: Long? = null
    private var createdAt: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")
        updatedAt = intent.getLongExtra("updated_at", 0L)
        createdAt = intent.getLongExtra("created_at", 0L)

        binding.etaddTittle.setText(title)
        binding.etaddContent.setText(content)

        if (updatedAt == 0L) {
            updatedAt = null
        }
        if (createdAt == 0L) {
            createdAt = null
        }

        binding.ivAddBack.setOnClickListener {
            openActivity<HomeActivity>()

        }
        binding.ivSave.setOnClickListener {

            val etTitle = binding.etaddTittle.textOf()
            val etContent = binding.etaddContent.textOf()

            if (title.isNullOrEmpty() && content.isNullOrEmpty()) {
                viewModel.createNote(etTitle, etContent)
            } else {
                if (id.isNullOrEmpty()) {
                    viewModel.createNote(etTitle, etContent)
                } else {
                    if (etTitle != title || etContent != content) {
                        viewModel.updateNote(id!!, etTitle, etContent)
                    }
                }
            }
        }

        binding.ivDelete.setOnClickListener {
            if (id != null) {
                viewModel.deleteNote(id!!)
            } else {
                tos("Tidak ada data")
            }
        }

        val text: TextView = findViewById(R.id.edited)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateString =
            simpleDateFormat.format(updatedAt ?: DateTimeHelper().createAtLong())
        text.text = String.format("Date: %s", dateString)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("saved")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                tos("finish")
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