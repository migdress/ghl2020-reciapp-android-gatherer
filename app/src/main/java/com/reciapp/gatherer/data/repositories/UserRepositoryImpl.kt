package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.local.preferences.UserPreferences
import com.reciapp.gatherer.domain.models.Login
import com.reciapp.gatherer.domain.repositories.UserIdRepository
import com.reciapp.gatherer.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userPreferences: UserPreferences
) : UserRepository, UserIdRepository {

    override fun saveUser(loginResponse: Login) {
        with(loginResponse) {
            userPreferences.id = id
            userPreferences.firstName = firstName
            userPreferences.lastName = lastName
            userPreferences.type = type
        }
    }

    override fun getUserId(): String = userPreferences.id
}