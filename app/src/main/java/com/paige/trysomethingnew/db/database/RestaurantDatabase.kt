package com.paige.trysomethingnew.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paige.trysomethingnew.db.entity.Restaurant
import com.paige.trysomethingnew.db.dao.AccountDao
import com.paige.trysomethingnew.db.dao.RestaurantDao
import com.paige.trysomethingnew.db.entity.LoginSecret

@Database(entities = [Restaurant::class, LoginSecret::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    abstract fun accountDao(): AccountDao
}