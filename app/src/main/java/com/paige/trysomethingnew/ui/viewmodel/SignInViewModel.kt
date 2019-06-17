package com.paige.trysomethingnew.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paige.trysomethingnew.api.repositories.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun login(email: String, password: String, shouldRemember: Boolean) {
        viewModelScope.launch {
            if (accountRepository.verifyLogin(email, password)) {
                _result.postValue("Login Succeed")

                if (shouldRemember) {
                    accountRepository.remeberEmail(email)
                }
            } else {
                _result.postValue("Login Failed")
            }
        }
    }

    fun getRememberEmail() = accountRepository.getRememberedEmail()

    fun loginWithFingerPrint(email: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (accountRepository.loginWithFingerPrint(email)) {
                _result.postValue("Login Succeed")
                onSuccess()
            } else {
                _result.postValue("Login Failed")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}