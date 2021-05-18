package com.whales.carbontest.repository

import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO

class MovieRepository(private val apiCalls: ApiCalls) {
    fun getTrendingMovies(iResponse: IResponse<MovieDTO>) {
        apiCalls.getTrendingMovies(iResponse)
    }

    fun getMovieDetails(param: String, iResponse: IResponse<MovieDetailsDTO>) {
        apiCalls.getMovieDetails(param, iResponse)
    }
}