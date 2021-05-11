package com.whales.carbontest.di

import com.whales.carbontest.constant.RequestBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class CoreModule {

    @Provides
    fun provideClient() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val ongoing = chain.request().newBuilder()
//                    ongoing.addHeader("app-id", ApiToken)
                chain.proceed(ongoing.build())
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
//                .sslSocketFactory(trustAllSslSocketFactory, trustAllCerts[0] as X509TrustManager) //comment out when going live
            .build()

        return Retrofit.Builder()
            .baseUrl(RequestBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}