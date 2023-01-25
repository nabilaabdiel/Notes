package com.example.mynote.ui.home

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityHomeBinding
import com.example.mynote.ui.add.AddNoteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fabHome.setOnClickListener {
            openActivity<AddNoteActivity>() {
                finish()
            }
        }
    }
}