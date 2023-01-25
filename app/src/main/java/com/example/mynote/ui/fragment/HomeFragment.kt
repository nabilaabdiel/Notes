package com.example.mynote.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynote.R
import com.example.mynote.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : Fragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("cek")

        Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
    }
}