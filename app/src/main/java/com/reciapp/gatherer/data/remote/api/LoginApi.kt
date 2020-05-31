package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.LoginRequest
import com.reciapp.gatherer.data.remote.models.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("v1")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}