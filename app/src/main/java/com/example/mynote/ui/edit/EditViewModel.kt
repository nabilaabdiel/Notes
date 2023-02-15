package com.example.mynote.ui.edit

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.extension.toObject
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.User
import com.example.mynote.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val gson: Gson
) : BaseViewModel() {

    fun updateProfile(name: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver(
            { apiService.updateProfile(name) },
            false,
            object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)
                    userDao.update(data.copy(idRoom = 1))
                    _apiResponse.send(ApiResponse().responseSuccess())
                }

                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }
            })
    }

    fun updateProfileImg(name: String, photo: File) = viewModelScope.launch {
        println("Nama: $name")
        val fileBody = photo.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("photo", photo.name, fileBody)

        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver(
            { apiService.updateProfileImg(name, filePart) },
            false,
            object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)
                    userDao.insert(data.copy(idRoom = 1))
                    _apiResponse.send(ApiResponse().responseSuccess("profile uodated"))
                }

                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }
            })
    }
}