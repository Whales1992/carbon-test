package com.whales.fairmoneytest.networks.rectrofit

interface IResponse<T> {
    fun onSuccess(res : T)
    fun onFailure(res : String)
    fun onNetworkError(res : String)
}