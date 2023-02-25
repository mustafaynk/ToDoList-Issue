package com.example.todolistziro.data

import java.time.Instant

sealed class BaseModel(
    val id: Long = 0L,
    val create_time: Instant? = null,
    val creating_user_id: Long = 0L,
    val update_time: Instant? = null,
    val updating_User_id: Long = 0L,
    val version: Int = 0
)
