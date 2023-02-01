package com.example.mynote.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.extension.toList
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.Tittle
import com.example.mynote.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao):
    BaseViewModel() {

        val dataNote = MutableLiveData<List<Tittle>>()

    fun getNote() = viewModelScope.launch {
        ApiObserver({ apiService.getNote()}, false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                val data =
                    response.getJSONArray(ApiCode.DATA).toList<Tittle>(gson)
                dataNote.postValue(data)
                Timber.d("cek api ${data.size}")
            }
            override suspend fun onError(response: ApiResponse) {
                super.onError(response)
            }
        })
    }
}