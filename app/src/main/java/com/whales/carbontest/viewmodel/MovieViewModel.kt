package com.whales.carbontest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.UsersResponseObject
import com.whales.carbontest.repository.MovieRepository
import com.whales.carbontest.repository.ResponseObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun getTrendingMovies(): LiveData<ResponseObjectMapper?> {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(GetTrendingMovies(responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class GetTrendingMovies(private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            MovieRepository.getTrendingMovies(object : IResponse<UsersResponseObject> {
                override fun onSuccess(res: UsersResponseObject) {
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