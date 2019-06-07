package com.paige.trysomethingnew.di.module

import android.content.Context
import androidx.room.Room
import com.paige.trysomethingnew.db.dao.RestaurantDao
import com.paige.trysomethingnew.db.database.RestaurantDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRestaurantDatabase(context: Context) : RestaurantDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RestaurantDatabase::class.java,
            "Restaurant_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRestaurantDao(restaurantDatabase: RestaurantDatabase) : RestaurantDao {
        return restaurantDatabase.restaurantDao()
    }
}