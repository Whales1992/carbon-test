package com.whales.carbontest.networks.rectrofit

import android.util.Log
import com.whales.carbontest.constant.genericNetworkErrorMsg
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class ApiCalls @Inject constructor(retrofit: Retrofit) {

    private var iApi: IApi = retrofit.create(IApi::class.java)

    fun getTrendingMovies(param : Map<String, String>, iResponse: IResponse<MovieDTO>)
    {
        try {
            val call: Call<MovieDTO> = iApi.getTrendingMovies(param)
            call.enqueue(object : Callback<MovieDTO> {
                override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
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

                override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.localizedMessage != null)
                        iResponse.onNetworkError(t.localizedMessage!!)
                    else
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            if (ex.message != null)
                iResponse.onFailure(ex.message!!)
            else
                iResponse.onFailure(genericNetworkErrorMsg)
        }
    }

    fun getMovieDetails(param : Map<String, String>, iResponse: IResponse<MovieDetailsDTO>)
    {
        try {
            val call: Call<MovieDetailsDTO> = iApi.getMovieDetails(param)
            call.enqueue(object : Callback<MovieDetailsDTO> {
                override fun onResponse(call: Call<MovieDetailsDTO>, response: Response<MovieDetailsDTO>) {
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

                override fun onFailure(call: Call<MovieDetailsDTO>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.localizedMessage != null)
                        iResponse.onNetworkError(t.localizedMessage!!)
                    else
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            if (ex.message != null)
                iResponse.onFailure(ex.message!!)
            else
                iResponse.onFailure(genericNetworkErrorMsg)
        }
    }
}