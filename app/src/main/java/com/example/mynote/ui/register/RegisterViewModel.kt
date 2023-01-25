package com.example.mynote.ui.register

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService,
//    private val gson: Gson,
//    private val userDao: UserDao
    ): BaseViewModel() {

    fun register(name: String, email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver({apiService.register(name, email, password, confirmPassword)}, false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                _apiResponse.send(ApiResponse().responseSuccess())
            }
        })
    }
}