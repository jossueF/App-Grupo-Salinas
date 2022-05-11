package com.apps.pruebatecnica.services

import com.apps.pruebatecnica.models.PopularSeriesResult
import retrofit2.Response
import retrofit2.http.GET

interface SeriesAPI {

    @GET("/3/tv/popular?api_key=133bbab2b254c35a872b28bfdddb86eb")
    suspend fun getPopularSeriesResult() : Response<PopularSeriesResult>
}