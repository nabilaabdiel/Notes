package com.example.mynote.ui.fragment

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.crocodic.core.base.fragment.CoreFragment
import com.crocodic.core.data.CoreSession
import com.example.mynote.base.App
import com.google.gson.Gson
import javax.inject.Inject

open class Fragment<vb : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
        CoreFragment<vb>(layoutRes) {
            protected val app: App by lazy { requireActivity().aplication as App }

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var session: CoreSession

        }