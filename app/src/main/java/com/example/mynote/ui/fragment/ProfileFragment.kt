package com.example.mynote.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynote.R
import com.example.mynote.databinding.FragmentProfileBinding
import timber.log.Timber

class ProfileFragment : Fragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("cek")

        Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
    }
}