package com.paige.trysomethingnew.di.module

import com.paige.trysomethingnew.BuildConfig
import com.paige.trysomethingnew.api.service.YelpApiService
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .build()
    }

    @ApplicationScope
    @Provides
    fun httpLoggingIntercepter() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @ApplicationScope
    @Provides
    fun yelpApiService(client: OkHttpClient) : YelpApiService {
        return Retrofit.Builder()
            .baseUrl(YELP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(YelpApiService::class.java)
    }

    companion object {
        private const val CLIENT_TIME_OUT_SECS = 60L
        private const val YELP_BASE_URL = "https://api.yelp.com/v3/"
    }
}