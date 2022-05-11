package com.apps.pruebatecnica.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize
data class RequestToken(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("expires_at")
    val expires: String,
    @SerializedName("request_token")
    val requestToken: String
) //: Parcelable
