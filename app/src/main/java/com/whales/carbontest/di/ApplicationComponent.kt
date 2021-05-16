package com.whales.carbontest.di

import android.app.Application
import com.whales.carbontest.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@AppScope
@Component(modules = [
    (CoreModule::class),
    (AndroidSupportInjectionModule::class),
    (ActivityBuildersModule::class)])
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent?
    }

}