package com.apps.pruebatecnica.models

import com.google.gson.annotations.SerializedName

data class PopularSeriesResult(
    @SerializedName("results")
    val result: MutableList<PopularSerie>
)
