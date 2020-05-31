package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.mappers.LoginMapper
import com.reciapp.gatherer.data.remote.api.LoginApi
import com.reciapp.gatherer.data.remote.models.login.LoginRequest
import com.reciapp.gatherer.domain.models.Login
import com.reciapp.gatherer.domain.repositories.LoginRepository
import io.reactivex.Single

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val mapper: LoginMapper
) : LoginRepository {

    override fun login(loginRequest: LoginRequest): Single<Login> =
        loginApi.login(loginRequest).map(mapper.mapToDomainLogin)
}