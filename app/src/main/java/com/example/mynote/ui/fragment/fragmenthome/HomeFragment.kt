package com.example.mynote.ui.fragment.fragmenthome

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.crocodic.core.base.adapter.CoreListAdapter
import com.example.mynote.R
import com.example.mynote.data.room.user.Tittle
import com.example.mynote.databinding.FragmentHomeBinding
import com.example.mynote.databinding.ListItemBinding
import com.example.mynote.ui.fragment.Fragment

class HomeFragment : Fragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val notes = ArrayList<Tittle?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvHome?.adapter = CoreListAdapter<ListItemBinding, Tittle>(R.layout.list_item)
            .initItem(notes)
    }
}

//    @Inject
//    lateinit var userDao: UserDao
//    private var friend = ArrayList<User>()
//    private var keyword: String? = null
//
//    private val runnable by lazy {
//        Runnable {
//            refreshData()
//        }
//    }
//
//    private val adapter by lazy {
//        ReactiveListAdapter<, User>(R.layout.item_friend)
//    }

//        Timber.d("cek")
