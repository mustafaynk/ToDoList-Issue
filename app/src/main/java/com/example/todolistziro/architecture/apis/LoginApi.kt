package com.example.todolistziro.architecture.apis

import com.example.todolistziro.data.login.LoginRequest
import com.example.todolistziro.data.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("signin")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}