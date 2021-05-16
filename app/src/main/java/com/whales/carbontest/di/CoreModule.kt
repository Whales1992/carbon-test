package com.whales.carbontest.di

import android.app.Application
import com.android.datacenter.di.DataCenterComponent
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.whales.carbontest.constant.ApiToken
import com.whales.carbontest.constant.ImageBaseUrl
import com.whales.carbontest.constant.RequestBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(subcomponents = [(DataCenterComponent::class)])
class CoreModule {

    @AppScope
    @Provides
    @Named("token")
    fun getToken():Map<String, String> {
        return mapOf("api_key" to ApiToken)
    }

    @AppScope
    @Provides
    fun provideClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val ongoing = chain.request().newBuilder()
                    chain.proceed(ongoing.build())
                })
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    @AppScope
    @Provides
    fun provideNetworkClient(provideClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(RequestBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient)
            .build()
    }

    @AppScope
    @Provides
    fun okHttp3Downloader(provideClient : OkHttpClient):OkHttp3Downloader {
        return OkHttp3Downloader(provideClient)
    }

    @Provides
    @AppScope
    fun picasso(application: Application, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(application)
                .downloader(okHttp3Downloader)
                .loggingEnabled(true)
                .build()
    }

    @Provides
    @AppScope
    @Named("imageBaseUrl200")
    fun imageBaseUrl200():String{
        return "${ImageBaseUrl}w200"
    }

    @Provides
    @AppScope
    @Named("imageBaseUrl500")
    fun imageBaseUrl500():String{
        return "${ImageBaseUrl}w500"
    }
}
