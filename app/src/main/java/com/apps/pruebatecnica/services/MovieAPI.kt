package com.apps.pruebatecnica.services

import com.apps.pruebatecnica.models.PopularMoviesResult
import com.apps.pruebatecnica.models.RequestToken
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPI {
    @GET("/3/authentication/token/new?api_key=133bbab2b254c35a872b28bfdddb86eb")
    suspend fun getRequestToken() : Response<RequestToken>

    @GET("/3/movie/popular?api_key=133bbab2b254c35a872b28bfdddb86eb")
    suspend fun getPopularMoviesResult() : Response<PopularMoviesResult>
}