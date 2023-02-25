package com.example.todolistziro.architecture.repositories

import com.example.todolistziro.architecture.App
import com.example.todolistziro.architecture.apis.LoginApi
import com.example.todolistziro.data.login.LoginRequest
import com.example.todolistziro.data.login.LoginResponse
import javax.inject.Inject

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): LoginResponse?

}

class LoginRepositoryImpl : LoginRepository {

    @Inject
    lateinit var loginApi: LoginApi

    init {
        App.instance.apiComponent.inject(this)
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        val response = loginApi.login(loginRequest)
        return if (response.jwt.isNotEmpty()) {
            response
        } else {
            null
        }
    }

}