package com.paige.trysomethingnew.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paige.trysomethingnew.api.model.Restaurant
import com.paige.trysomethingnew.db.dao.RestaurantDao

@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao() : RestaurantDao
}