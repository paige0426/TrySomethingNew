package com.paige.trysomethingnew.di.module

import android.app.Application
import android.content.Context
import com.paige.trysomethingnew.api.repositories.RestaurantRepository
import com.paige.trysomethingnew.api.service.YelpApiService
import com.paige.trysomethingnew.db.dao.RestaurantDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideRestaurantRepository(restaurantDao: RestaurantDao, yelpApiService: YelpApiService): RestaurantRepository {
        return RestaurantRepository(restaurantDao, yelpApiService)
    }
}