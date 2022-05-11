package com.apps.pruebatecnica.models

import com.google.gson.annotations.SerializedName

data class PopularMoviesResult(
    @SerializedName("results")
    val result: MutableList<PopularMovie>
)
