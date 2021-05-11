package com.whales.carbontest.repository

import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.UserObject
import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import retrofit2.Retrofit

class MovieRepository {

    companion object{
        private var singleton: MovieRepository? = null

        fun getSingleton(): MovieRepository? {
            if (singleton == null) singleton = MovieRepository()
            return singleton
        }

        fun getTrendingMovies(iResponse: IResponse<UsersResponseObject>) {
            ApiCalls().getTrendingMovies(iResponse)
        }
    }
}