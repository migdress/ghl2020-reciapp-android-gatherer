package com.reciapp.gatherer.data.mappers

import com.reciapp.gatherer.data.remote.models.LoginResponse
import com.reciapp.gatherer.domain.models.Login

class LoginMapper {

    val mapToDomainLogin = fun(loginResponse: LoginResponse): Login {
        return Login(
            id = loginResponse.id,
            firstName = loginResponse.firstName,
            lastName = loginResponse.lastName,
            type = loginResponse.type
        )
    }
}