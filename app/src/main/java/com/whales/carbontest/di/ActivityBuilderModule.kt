package com.whales.carbontest.di

import com.whales.carbontest.view.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieActivity(): MovieActivity?

}