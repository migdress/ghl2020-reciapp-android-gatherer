package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.data.remote.models.login.LoginRequest
import com.reciapp.gatherer.domain.models.Login
import io.reactivex.Single

interface LoginRepository {

    fun login(loginRequest: LoginRequest): Single<Login>
}