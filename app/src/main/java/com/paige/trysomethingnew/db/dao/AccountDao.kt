package com.paige.trysomethingnew.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paige.trysomethingnew.db.entity.LoginSecret

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(secret: LoginSecret)

    @Query("DELETE FROM account_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM account_table WHERE email = :email")
    suspend fun getSecret(email: String): LoginSecret?
}