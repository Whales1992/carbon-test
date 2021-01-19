package com.whales.fairmoneytest.repository

import com.whales.fairmoneytest.networks.rectrofit.ApiCalls
import com.whales.fairmoneytest.networks.rectrofit.IResponse
import com.whales.fairmoneytest.networks.rectrofit.pojo.UserObject
import com.whales.fairmoneytest.networks.rectrofit.pojo.UsersResponseObject

class Repository {

    companion object{
        private var singleton: Repository? = null

        fun getSingleton(): Repository? {
            if (singleton == null) singleton = Repository()
            return singleton
        }
    }

    fun getNext50Users(
        apiCalls: ApiCalls,
        iResponse: IResponse<UsersResponseObject>) {
        apiCalls.getNext50Users(iResponse)
    }

    fun getUserDetails(apiCalls: ApiCalls, parameterId: String, iResponse: IResponse<UserObject>) {
        apiCalls.getUserDetails(parameterId, iResponse)
    }

}