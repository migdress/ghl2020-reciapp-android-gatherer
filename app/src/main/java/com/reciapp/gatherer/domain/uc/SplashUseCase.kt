package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.domain.repositories.UserIdRepository

class SplashUseCase(
    private val userIdRepository: UserIdRepository
) {

    fun isUserLogged(): Boolean = userIdRepository.getUserId().isNotBlank()
}