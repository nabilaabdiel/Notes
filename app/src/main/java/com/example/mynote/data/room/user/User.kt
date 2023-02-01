package com.example.mynote.data.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @Expose
    @SerializedName("id_room")
    val idRoom: Int,
    @Expose
    @SerializedName("id")
    val id: String = "0",
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("email")
    val email: String?,
    @Expose
    @SerializedName("photo")
    val photo: String?,
    @Expose
    @SerializedName("password")
    val password: String?,
    @Expose
    @SerializedName("comfirmPassword")
    val confirmPassword: String?

)