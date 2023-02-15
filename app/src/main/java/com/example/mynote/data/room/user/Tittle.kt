package com.example.mynote.data.room.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Tittle(
    @PrimaryKey()
    @Expose
    @SerializedName("id")
    val id : String = "0",
    @Expose
    @SerializedName("title")
    val tittle: String,
    @Expose
    @SerializedName("content")
    val content : String,
    @Expose
    @SerializedName("date")
    val date : String,
    @Expose
    @SerializedName("id_room")
    val idRoom : String,
    @Expose
    @SerializedName("created_At")
    val created_At : Long,
    @Expose
    @SerializedName("updated_At")
    val updated_At : Long

) : Parcelable