package com.example.mynote.data.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Tittle(
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("tittle")
    val id : Int = 0,
    @Expose
    @SerializedName("tittle")
    val tittle: String,
    @Expose
    @SerializedName("date")
    val date : String,
    @Expose
    @SerializedName("content")
    val content : String,
    @Expose
    @SerializedName("id_room")
    val idRoom : String
)