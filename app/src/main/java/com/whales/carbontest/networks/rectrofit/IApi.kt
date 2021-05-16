package com.whales.carbontest.networks.rectrofit

import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IApi {
    @GET("trending/movie/week")
    fun getTrendingMovies(@QueryMap options : Map<String, String>): Call<MovieDTO>

//    @GET("user/{userId}")
//    fun getUserDetails(@Path("userId") userId: String): Call<UserObject>
}