package com.example.mynote.ui.home

import com.example.mynote.api.ApiService
import com.example.mynote.base.viewModel.BaseViewModel
import com.example.mynote.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao): BaseViewModel(){
}