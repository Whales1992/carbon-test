package com.android.datacenter.di

import dagger.Subcomponent

@DataCenterScope
@Subcomponent(modules = [(AppModule::class)])
interface DataCenterComponent {

    @Subcomponent.Builder
    interface Builder {
//        fun application(application: Application):Builder
        fun build(): DataCenterComponent?
    }
}