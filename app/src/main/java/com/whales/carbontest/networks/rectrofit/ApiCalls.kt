package com.whales.carbontest.networks.rectrofit

import android.util.Log
import com.whales.carbontest.constant.genericNetworkErrorMsg
import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiCalls {

    @Inject
    lateinit var retrofit: Retrofit

    private var iApi: IApi = retrofit.create(IApi::class.java)

    fun getTrendingMovies(iResponse: IResponse<UsersResponseObject>)
    {
        try {
            val call: Call<UsersResponseObject> = iApi.getTrendingMovies()
            call.enqueue(object : Callback<UsersResponseObject> {
                override fun onResponse(call: Call<UsersResponseObject>, response: Response<UsersResponseObject>) {
                    when {
                        response.isSuccessful -> {
                            if (response.body() != null) {
                                iResponse.onSuccess(response.body()!!)
                            } else {
                                iResponse.onFailure(response.message())
                            }
                        }

                        else -> {
                            iResponse.onFailure(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<UsersResponseObject>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.message != null)
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            iResponse.onFailure(ex.message!!)
        }
    }

//    fun getUserDetails(userId: String, iResponse: IResponse<UserObject>)
//    {
//        try {
//            val call: Call<UserObject> = iApi!!.getUserDetails(userId)
//            call.enqueue(object : Callback<UserObject> {
//                override fun onResponse(call: Call<UserObject>, response: Response<UserObject>) {
//                    when {
//                        response.isSuccessful -> {
//                            if (response.body() != null) {
//                                Log.e("@getUserDetails", response.body().toString() + "")
//                                iResponse.onSuccess(response.body()!!)
//                            } else {
//                                iResponse.onFailure(response.message())
//                            }
//                        }
//
//                        else -> {
//                            iResponse.onFailure(response.message())
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<UserObject>, t: Throwable) {
//                    iResponse.onFailure(genericNetworkErrorMsg)
//                }
//            })
//        }catch (ex: Exception)
//        {
//            ex.printStackTrace()
//            iResponse.onFailure(ex.message!!)
//        }
//    }
}