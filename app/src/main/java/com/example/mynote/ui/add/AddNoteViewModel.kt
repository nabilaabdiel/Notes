package com.example.mynote.ui.add

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

    fun createNote(title: String, content: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver(
            { apiService.createNote(title, content) },
            false,
            object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    _apiResponse.send(ApiResponse().responseSuccess())
                }

                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                }
            })
    }

    fun updateNote(id: String, tittle: String, content: String) = viewModelScope.launch {
        println("updatenote 1")
        _apiResponse.send(ApiResponse().responseLoading())
        println("updatenote 2")
        ApiObserver(
            { apiService.updateNote(id, tittle, content) },
            false,
            object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    println("updatenote 3")
                    _apiResponse.send(ApiResponse().responseSuccess("Note Updated"))
                }
            })
    }

    //delete
    fun deleteNote(id: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver({ apiService.deleteNote(id) }, false, object : ApiObserver.ResponseListener {
            override suspend fun onSuccess(response: JSONObject) {
                _apiResponse.send(ApiResponse().responseSuccess())
            }
        })
    }
}