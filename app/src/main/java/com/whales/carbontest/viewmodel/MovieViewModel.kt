package com.whales.carbontest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import com.whales.carbontest.repository.MovieRepository
import com.whales.carbontest.repository.ResponseObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun getTrendingMovies(apiCalls: ApiCalls): LiveData<ResponseObjectMapper?> {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(GetTrendingMovies(apiCalls, responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class GetTrendingMovies(private val apiCalls: ApiCalls, private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            MovieRepository(apiCalls).getTrendingMovies(object : IResponse<MovieDTO> {
                override fun onSuccess(res: MovieDTO) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (true, res))
                }

                override fun onFailure(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }

                override fun onNetworkError(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }
            })
        }
    }
}