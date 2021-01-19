package com.whales.fairmoneytest.networks.rectrofit

import com.whales.fairmoneytest.networks.rectrofit.pojo.UserObject
import com.whales.fairmoneytest.networks.rectrofit.pojo.UsersResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi {
    @GET("user?limit=100")
    fun getNext50Users(): Call<UsersResponseObject>

    @GET("user/{userId}")
    fun getUserDetails(@Path("userId") userId: String): Call<UserObject>
}