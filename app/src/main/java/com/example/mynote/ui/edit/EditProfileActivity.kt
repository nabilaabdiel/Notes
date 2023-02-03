package com.example.mynote.ui.edit

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityEditProfileBinding
import com.example.mynote.ui.fragment.fragmentprofile.ProfileFragment
import com.example.mynote.ui.home.HomeActivity

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding, EditViewModel>(R.layout.activity_edit_profile) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivEdtprofileBack.setOnClickListener{
            openActivity<HomeActivity>()
            finish()
        }

        binding.btnProfSave.setOnClickListener {
            finish()

            val name = binding.etrName.textOf()

            viewModel.updateProfile(name)
        }
    }
}