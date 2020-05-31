package com.reciapp.gatherer.data.remote.models.login

import com.squareup.moshi.Json

class LoginResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "firstname")
    val firstName: String,
    @Json(name = "lastname")
    val lastName: String,
    @Json(name = "type")
    val type: String
)