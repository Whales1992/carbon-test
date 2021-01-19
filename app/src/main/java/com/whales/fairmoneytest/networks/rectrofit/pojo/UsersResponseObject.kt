package com.whales.fairmoneytest.networks.rectrofit.pojo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UsersResponseObject {

    var data : List<DataBean> ?=null

    class DataBean{
        var id : String?=null
        var lastName : String?=null
        var firstName : String?=null
        var email : String?=null
        var title : String?=null
        var picture : String?=null

        companion object{
            fun objectFromData(src: String?): DataBean {
                return Gson().fromJson(src, DataBean::class.java)
            }

            fun arrayDataBeanFromData(src: String?):List<DataBean>{
                val listType = object : TypeToken<ArrayList<DataBean?>?>() {}.type
                return Gson().fromJson(src, listType)
            }

        }
    }

    companion object{
        fun objectFromData(src: String?): UsersResponseObject {
            return Gson().fromJson(src, UsersResponseObject::class.java)
        }
    }
}