package com.example.mynote.ui.fragment.fragmentprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crocodic.core.helper.log.Log
import com.example.mynote.R
import com.example.mynote.databinding.FragmentProfileBinding
import com.example.mynote.ui.edit.EditProfileActivity
import com.example.mynote.ui.fragment.Fragment
import com.example.mynote.ui.login.LoginActivity
import com.example.mynote.ui.profile.ProfileViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragment : Fragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private lateinit var btnEdit : TextView
    private lateinit var btnLogout : TextView

    private val viewmodel by activityViewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEdit = view.findViewById(R.id.tvp_profile)
        btnLogout = view.findViewById(R.id.tvp_logout)

        btnEdit.setOnClickListener{
            val back = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(back)
        }
        btnLogout.setOnClickListener{
            activity?.finishAffinity()
            val out = Intent(requireContext(), LoginActivity::class.java)
            startActivity(out)
        }

        //Kalau di fragment itu sebelum pakai lifecycleScope di kasi viewLifecycleOwner (*Kalau Fragment*)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewmodel.getUser.collect { user ->
                        binding?.user = user

                        //Flow itu ga pake observe, gantinya itu collect
                        //Collect itu artinya menerima

                        Glide
                            .with(requireContext())
                            .load(user?.photo)
                            .placeholder(R.drawable.ic_person)
                            .error(R.drawable.ic_outline_error_24)
                            .apply(RequestOptions.centerInsideTransform())
                            .into(binding?.fgPerson ?: return@collect)

                        //kalo binding fg person nya null, dia akan return.
                    // Kalau di let itukan untuk mengecek agar tidak null. Dan sebagai gantinya bisa menggunakan ?:
                    }
                }
            }
        }
    }
}