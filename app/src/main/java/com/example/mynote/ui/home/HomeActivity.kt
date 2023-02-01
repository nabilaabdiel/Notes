package com.example.mynote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.crocodic.core.extension.openActivity
import com.example.mynote.R
import com.example.mynote.base.activity.BaseActivity
import com.example.mynote.databinding.ActivityHomeBinding
import com.example.mynote.ui.add.AddNoteActivity
import com.example.mynote.ui.fragment.fragmenthome.HomeFragment
import com.example.mynote.ui.fragment.fragmentprofile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fabHome.setOnClickListener {
            openActivity<AddNoteActivity>() {
                finish()
            }
        }
        replesFragment(homeFragment)

        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btm_home -> {
                    replesFragment(homeFragment)
                }
                R.id.btm_profile -> {
                    replesFragment(profileFragment)
                }
            }
            true
        }
    }

    private fun replesFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fram_home, fragment)
            commit()
        }

    }
}