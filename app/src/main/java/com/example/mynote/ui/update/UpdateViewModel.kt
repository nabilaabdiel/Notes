package com.example.mynote.ui.update

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(private val apiService: ApiService): BaseViewModel() {

//    fun updateNote (title: String, content: String) = viewModelScope.launch {
//        _apiResponse.send(ApiResponse().responseLoading())
//        ApiObserver({apiService.updateNote(title, content)}, false, object : ApiObserver.ResponseListener{
//            override suspend fun onSuccess(response: JSONObject) {
//                _apiResponse.send(ApiResponse().responseSuccess())
//            }
//        })
//    }
}