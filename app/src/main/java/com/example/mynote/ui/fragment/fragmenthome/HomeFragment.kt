package com.example.mynote.ui.fragment.fragmenthome

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.crocodic.core.base.adapter.CoreListAdapter
import com.crocodic.core.extension.openActivity
import com.example.mynote.R
import com.example.mynote.data.room.user.Tittle
import com.example.mynote.databinding.FragmentHomeBinding
import com.example.mynote.databinding.ListItemBinding
import com.example.mynote.ui.add.AddNoteActivity
import com.example.mynote.ui.fragment.Fragment
import com.example.mynote.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment<FragmentHomeBinding>(R.layout.fragment_home) {

    //Refresh
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    //search-end
    private var keyword: String? = null

    private val runnable by lazy {
        Runnable {
            viewModel.getNote(keyword)
        }
    }
    private val handler = Handler(Looper.getMainLooper())
    //end

    //List
    private val notes = ArrayList<Tittle?>()

    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniSwipe()

        observe()
        //List
        binding?.rvHome?.adapter = CoreListAdapter<ListItemBinding, Tittle>(R.layout.list_item)
            .initItem(notes) {position, data ->
                activity?.openActivity<AddNoteActivity> {
                    putExtra("id", data?.id)
                    putExtra("title", data?.tittle)
                    putExtra("content", data?.content)
                    putExtra("updated_at", data?.updated_At)
                    putExtra("created_at", data?.created_At)
                }
            }

        //Search-end
        binding?.svHome?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("cek search", "isinyaa: $p0")

                keyword = if (p0?.trim()?.isEmpty() == true) {
                    null
                } else {
                    p0
                }

                Log.d("cek search", "isinyaa keyword: $keyword")
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 1500)

                return true
            }
        })
        //end

//        override fun onQueryTextSubmit(query: String?): Boolean {
//            //kalo kita mau search ketika dienter
//        }
//        override fun onQueryTextChange(query: String): Boolean {
//            //kalo kita mau lakukan search tiap diketik
//        }

        viewModel.getNote(keyword)

    }

    private fun observe(){
        lifecycleScope.launch {
            viewModel.dataNote.observe(requireActivity()) {
                notes.clear()
                notes.addAll(it)
                binding?.rvHome?.adapter?.notifyDataSetChanged()
                binding?.swipeRefresh?.isRefreshing = false
            }
        }
    }

    private fun iniSwipe() {
        binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.getNote(keyword)
        }
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
