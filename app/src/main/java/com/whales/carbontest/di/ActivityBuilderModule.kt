package com.whales.carbontest.di

import com.whales.carbontest.view.MovieActivity
import com.whales.carbontest.view.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieActivity(): MovieActivity?

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsActivity(): MovieDetailsActivity?

}