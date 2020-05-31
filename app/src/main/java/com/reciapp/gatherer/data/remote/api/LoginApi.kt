package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.login.LoginRequest
import com.reciapp.gatherer.data.remote.models.login.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("v1")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}