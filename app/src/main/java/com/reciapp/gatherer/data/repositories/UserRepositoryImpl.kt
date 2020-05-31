package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.local.preferences.UserPreferences
import com.reciapp.gatherer.domain.models.Login
import com.reciapp.gatherer.domain.repositories.SaveUserRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository
import com.reciapp.gatherer.domain.repositories.UserNameRepository

class UserRepositoryImpl(
    private val userPreferences: UserPreferences
) : SaveUserRepository, UserIdRepository, UserNameRepository {

    override fun saveUser(loginResponse: Login) {
        with(loginResponse) {
            userPreferences.id = id
            userPreferences.firstName = firstName
            userPreferences.lastName = lastName
            userPreferences.type = type
        }
    }

    override fun getUserId(): String = userPreferences.id

    override fun getUserName(): String = "${userPreferences.firstName} ${userPreferences.lastName}"
}