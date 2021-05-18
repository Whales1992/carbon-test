package com.whales.carbontest.networks.rectrofit

import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IApi {
    @GET("trending/movie/week")
    fun getTrendingMovies(@QueryMap params : Map<String, String>): Call<MovieDTO>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: String, @QueryMap params : Map<String, String>): Call<MovieDetailsDTO>
}