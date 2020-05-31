package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.domain.models.Login

interface UserRepository {

    fun saveUser(loginResponse: Login)
}