package com.example.todolistziro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistziro.architecture.extensions.asLiveData
import com.example.todolistziro.architecture.repositories.LoginRepository
import com.example.todolistziro.data.login.LoginRequest
import com.example.todolistziro.data.login.LoginResponse
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: LoginRepository): BaseViewModel() {

    private val loginResponseResource: MutableLiveData<LoginResponse> = MutableLiveData()
    var loginResponse = loginResponseResource.asLiveData()

    fun login(userName: String, password: String) {
        loginResponseResource.value = LoginResponse()
        viewModelScope.launch {
            val response = repository.login(LoginRequest(userName, password))
            response?.let {
                loginResponseResource.postValue(it)
            }
        }
    }

}