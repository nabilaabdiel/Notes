package com.example.mynote.ui.edit

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class EditViewModel @Inject constructor(private val apiService: ApiService): BaseViewModel() {

    fun updateProfile (name: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver({apiService.updateProfile(name)}, false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                _apiResponse.send(ApiResponse().responseSuccess())
            }
        })
    }
    fun updateProfileImg (name: String, photo: File) = viewModelScope.launch {
        println("Nama: $name")
        val fileBody = photo.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("photo", photo.name, fileBody)

        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver({apiService.updateProfileImg(name, filePart)}, false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                _apiResponse.send(ApiResponse().responseSuccess())
            }
        })
    }
}