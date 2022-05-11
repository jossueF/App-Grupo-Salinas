package com.apps.pruebatecnica.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("release_date")
    val release: String,
    @SerializedName("backdrop_path")
    val backdrop: String
) : Parcelable
