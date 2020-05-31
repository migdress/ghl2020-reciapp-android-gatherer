package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.domain.repositories.UserNameRepository

class HomeUseCase(
    private val userNameRepository: UserNameRepository
) {
    fun getUserName() = userNameRepository.getUserName()
}