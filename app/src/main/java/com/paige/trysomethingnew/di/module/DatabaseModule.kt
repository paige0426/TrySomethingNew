package com.paige.trysomethingnew.di.module

import android.content.Context
import androidx.room.Room
import com.paige.trysomethingnew.db.dao.RestaurantDao
import com.paige.trysomethingnew.db.database.RestaurantDatabase
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun getRestaurantDatabase(context: Context) : RestaurantDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RestaurantDatabase::class.java,
            "Restaurant_database"
        ).build()
    }

    @ApplicationScope
    @Provides
    fun getRestaurantDao(restaurantDatabase: RestaurantDatabase) : RestaurantDao {
        return restaurantDatabase.restaurantDao()
    }
}