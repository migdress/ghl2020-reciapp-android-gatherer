package com.reciapp.gatherer.data.remote.models

import com.squareup.moshi.Json

data class LoginRequest(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String
)