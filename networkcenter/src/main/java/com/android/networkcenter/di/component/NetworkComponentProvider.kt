package com.android.networkcenter.di.component

import com.android.networkcenter.di.modules.AppModule
import com.whales.carbontest.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<BaseApplication?> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication?): Builder?
        fun build(): AppComponent?
    }
}