package com.android.datacenter

import android.app.Application
import javax.inject.Inject

class DataClass {

    @Inject
    lateinit var getApplication: Application

}