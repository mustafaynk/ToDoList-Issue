package com.example.todolistziro.data.login

data class LoginResponse(
    val username: String = String(),
    val grants: List<String> = listOf(),
    val jwt: String = String()
)
