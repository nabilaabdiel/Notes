package com.example.mynote.ui.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crocodic.core.extension.textOf
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : BaseActivity<ActivityUpdateBinding, UpdateViewModel>(R.layout.activity_update) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        private fun initClick() {
//            binding.btnUpdateBack.setOnClickListener {
//                val tittle = binding.etUpTittle.textOf()
//                val content = binding.etUpContent.textOf()
//            }
//            if (upTitle.isNullOrEmpty)
//
//                viewModel.updateNote(tittle, content)
//        }
    }
}