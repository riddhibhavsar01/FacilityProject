package com.example.facilityproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Album(
    @SerializedName("albumId")
    val albumId: Int? = null,

    @SerializedName("id")
    @PrimaryKey
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null
) : Parcelable
