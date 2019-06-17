package com.paige.trysomethingnew.api.repositories

import android.content.Context
import com.paige.trysomethingnew.api.keystore.KeyStoreManager
import com.paige.trysomethingnew.db.dao.AccountDao
import com.paige.trysomethingnew.db.entity.LoginSecret
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val keyStoreManager: KeyStoreManager,
    val context: Context
) {
    suspend fun getAccountSecret(email: String): LoginSecret? = accountDao.getSecret(email)

    suspend fun cleanAllAccount() = accountDao.deleteAll()

    suspend fun insert(email: String, password: String) {
        val (encryption, iv) = keyStoreManager.encrypt(password)

        accountDao.insert(LoginSecret(email, encryption, iv))
    }

    suspend fun verifyLogin(email: String, password: String): Boolean {
        val record = getAccountSecret(email) ?: return false

        val recordPassword = keyStoreManager.decrypt(record.encryption, record.iv)

        return password == recordPassword
    }

    suspend fun loginWithFingerPrint(email: String): Boolean {
        val record = getAccountSecret(email) ?: return false

        val password = keyStoreManager.decrypt(record.encryption, record.iv)

        return verifyLogin(email, password)
    }

    fun remeberEmail(email: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(REMEMBER_KEY_NAME, email)
        editor.apply()
    }

    fun getRememberedEmail(): String? {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(REMEMBER_KEY_NAME, null)
    }

    companion object {
        private const val SHARED_PREF_NAME = "account_pref"
        private const val REMEMBER_KEY_NAME = "remembered_key_name"
    }
}