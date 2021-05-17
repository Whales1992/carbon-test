package com.whales.carbontest.repository

import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO

class MovieRepository(private val apiCalls: ApiCalls) {
    fun getTrendingMovies(param: Map<String, String>, iResponse: IResponse<MovieDTO>) {
        apiCalls.getTrendingMovies(param, iResponse)
    }

    fun getMovieDetails(param: Map<String, String>, iResponse: IResponse<MovieDetailsDTO>) {
        apiCalls.getMovieDetails(param, iResponse)
    }
}