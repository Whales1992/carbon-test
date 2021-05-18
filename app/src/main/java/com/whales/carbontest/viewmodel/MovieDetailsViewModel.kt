package com.whales.carbontest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.IResponse
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO
import com.whales.carbontest.repository.MovieRepository
import com.whales.carbontest.repository.ResponseObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel : ViewModel()
{
    private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun getMovieDetails(param : String, apiCalls: ApiCalls): LiveData<ResponseObjectMapper?>
    {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(GetMovieDetails(param, apiCalls, responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class GetMovieDetails(private val param : String, private val apiCalls: ApiCalls, private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            MovieRepository(apiCalls).getMovieDetails(param, object : IResponse<MovieDetailsDTO> {
                override fun onSuccess(res: MovieDetailsDTO) {
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