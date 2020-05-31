package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.domain.models.Login

interface SaveUserRepository {

    fun saveUser(loginResponse: Login)
}