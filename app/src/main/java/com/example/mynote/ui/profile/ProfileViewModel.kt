package com.example.mynote.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.User
import com.example.mynote.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val apiService: ApiService, private val userDao: UserDao, private val gson: Gson): BaseViewModel() {

    private val _user = Channel<List<User>>()
    val user = _user.receiveAsFlow()


    val getUser = userDao.getUser()

    fun logout (logout: ()-> Unit) = viewModelScope.launch {
        userDao.deleteAll()
        logout()
    }
}