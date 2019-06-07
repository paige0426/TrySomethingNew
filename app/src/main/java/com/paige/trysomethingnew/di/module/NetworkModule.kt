package com.paige.trysomethingnew.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.paige.trysomethingnew.BuildConfig
import com.paige.trysomethingnew.api.service.YelpApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingIntercepter() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideYelpApiService(client: OkHttpClient) : YelpApiService {
        return Retrofit.Builder()
            .baseUrl(YELP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(YelpApiService::class.java)
    }

    companion object {
        private const val CLIENT_TIME_OUT_SECS = 60L
        private const val YELP_BASE_URL = "https://api.yelp.com/v3/"
    }
}