package com.whales.carbontest.networks.rectrofit.dto

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserObject {
    var id : String?=null
    var lastName : String?=null
    var firstName : String?=null
    var gender : String?=null
    var dateOfBirth : String?=null
    var registerDate : String?=null
    var phone : String?=null
    var email : String?=null
    var title : String?=null
    var picture : String?=null
    var location: LocationBean?=null

    companion object{
        fun objectFromData(src: String?): UserObject {
            return Gson().fromJson(src, UserObject::class.java)
        }

        fun arrayDataBeanFromData(src: String?):List<UserObject>{
            val listType = object : TypeToken<ArrayList<UserObject?>?>() {}.type
            return Gson().fromJson(src, listType)
        }
    }

    class LocationBean{
        var timezone : String?=null
        var street : String?=null
        var country : String?=null
        var state : String?=null
        var city : String?=null

        companion object{
            fun objectFromData(src: String?): LocationBean {
                return Gson().fromJson(src, LocationBean::class.java)
            }

            fun arrayDataBeanFromData(src: String?):List<LocationBean>{
                val listType = object : TypeToken<ArrayList<LocationBean?>?>() {}.type
                return Gson().fromJson(src, listType)
            }
        }
    }
}