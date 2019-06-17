package com.paige.trysomethingnew.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paige.trysomethingnew.db.entity.Restaurant
import androidx.paging.DataSource

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant_table ORDER BY id ASC")
    suspend fun getAllRestaurants(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurants: List<Restaurant>)

    @Query("DELETE FROM restaurant_table")
    fun deleteAll()

    @Query("SELECT * FROM restaurant_table ORDER BY id ASC")
    fun getRestaurantDataSource(): DataSource.Factory<Int, Restaurant>
}