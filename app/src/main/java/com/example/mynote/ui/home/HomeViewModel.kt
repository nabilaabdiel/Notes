package com.example.mynote.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.extension.toList
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.Tittle
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService, private val gson: Gson):
    BaseViewModel() {

        val dataNote = MutableLiveData<List<Tittle>>()

    fun getNote(search: String?) = viewModelScope.launch {
        Log.d("cek search", "isinyaa search: $search")
        ApiObserver({ apiService.getNote()}, false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                val data = response.getJSONArray(ApiCode.DATA).toList<Tittle>(gson)

                if (search.isNullOrEmpty()) {
                    dataNote.postValue(data)
                } else {
                    val dataFiltered = data.filter { it.tittle.contains(search, ignoreCase = true) }
                    dataNote.postValue(dataFiltered)
                }
                Timber.d("cek api ${data.size}")
            }
            override suspend fun onError(response: ApiResponse) {
                super.onError(response)
            }
        })
    }
}