package com.whales.fairmoneytest

import android.app.Application
import android.content.Context

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

    companion object{
        var mContext : Context?=null

        fun getContext() : Context
        {
            return mContext!!
        }
    }
}