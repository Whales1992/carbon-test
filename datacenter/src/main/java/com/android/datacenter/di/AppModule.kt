package com.android.datacenter.di

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class AppModule {

    @Provides
    @DataCenterScope
    fun getApplication(application: Application) : Application
    {
        return application
    }

}