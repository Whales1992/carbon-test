package com.whales.carbontest.networks.rectrofit

import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import retrofit2.Call
import retrofit2.http.GET

interface IApi {
    @GET("user?limit=100")
    fun getTrendingMovies(): Call<UsersResponseObject>

//    @GET("user/{userId}")
//    fun getUserDetails(@Path("userId") userId: String): Call<UserObject>
}