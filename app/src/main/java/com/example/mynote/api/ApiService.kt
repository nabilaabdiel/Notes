package com.example.mynote.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): String

    @FormUrlEncoded
    @POST("user")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("confirmPassword") confirmPassword: String?
    ): String

    @FormUrlEncoded
    @PATCH("user/profile")
    suspend fun Profile(
        @Field("name") name: String,
        @Field("photo") photo: String?
    ): String

    @FormUrlEncoded
    @POST("note")
    suspend fun createNote(
        @Field("tittle") title: String,
        @Field("content") content: String?
    ): String

    @FormUrlEncoded
    @PATCH("note/:id")
    suspend fun updateNote(
        @Field("tittle") title: String,
        @Field("content") content: String?
    ): String

    @GET("note/")
    suspend fun getNote(): String

    //token
    @GET("user/get-token")
    suspend fun getToken(): String

    @GET("token/renew")
    suspend fun getRenewToken(): String
}