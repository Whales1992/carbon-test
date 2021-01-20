package com.whales.fairmoneytest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whales.fairmoneytest.models.room.User
import com.whales.fairmoneytest.networks.rectrofit.ApiCalls
import com.whales.fairmoneytest.networks.rectrofit.IResponse
import com.whales.fairmoneytest.networks.rectrofit.pojo.UsersResponseObject
import com.whales.fairmoneytest.repository.DataBase
import com.whales.fairmoneytest.repository.Repository
import com.whales.fairmoneytest.repository.ResponseObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    var responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun getAllUsers(
        apiCalls: ApiCalls
    ): LiveData<ResponseObjectMapper?> {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(Next50UsersRequest(apiCalls, responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class Next50UsersRequest(private val apiCalls: ApiCalls, var responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            Repository.getSingleton()!!.getNext50Users(apiCalls, object : IResponse<UsersResponseObject> {
                override fun onSuccess(res: UsersResponseObject) {

                    for(user in res.data!!){
                        DataBase().addUser(User(user.id!!, user.lastName,user.firstName, user.email, user.title, user.picture))
                    }

                    responseMutableLiveData.postValue(ResponseObjectMapper (true, res))
                }

                override fun onFailure(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }

                override fun onNetworkError(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }
            });
        }
    }
}