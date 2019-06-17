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

class SignUpViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val secret = accountRepository.getAccountSecret(email)

            if (secret != null) {
                _result.postValue("Email has been registered")
                return@launch
            }

            accountRepository.insert(email, password)
            _result.postValue("Registration Success")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}