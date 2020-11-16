package com.app.daggermvvm.di.modules

import com.app.daggermvvm.di.annotations.BaseUrl
import com.app.daggermvvm.di.annotations.TimeOut
import com.app.daggermvvm.global.BASE_URL
import com.app.daggermvvm.network.MovieApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(httpClient: OkHttpClient, @BaseUrl baseUrl: String): MovieApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }


    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @TimeOut
    fun provideReadWriteTimeOut(): Long = 60L

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: Interceptor,
        @TimeOut timeOutVal: Long
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeOutVal, TimeUnit.SECONDS)
            .readTimeout(timeOutVal, TimeUnit.SECONDS)
            .writeTimeout(timeOutVal, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}