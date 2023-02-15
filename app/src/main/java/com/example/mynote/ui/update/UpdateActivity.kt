package com.example.mynote.ui.update

import android.os.Bundle
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : BaseActivity<ActivityUpdateBinding, UpdateViewModel>(R.layout.activity_update) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}