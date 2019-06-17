package com.paige.trysomethingnew.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class LoginSecret(
    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "encryption")
    val encryption: ByteArray,
    @ColumnInfo(name = "iv")
    val iv: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginSecret

        if (email != other.email) return false
        if (!encryption.contentEquals(other.encryption)) return false
        if (!iv.contentEquals(other.iv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + encryption.contentHashCode()
        result = 31 * result + iv.contentHashCode()
        return result
    }
}