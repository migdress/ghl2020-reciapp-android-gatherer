package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.domain.repositories.AvailableRoutesRepository
import com.reciapp.gatherer.domain.repositories.MyRoutesRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository

class RoutesUseCase(
    private val availableRoutesRepository: AvailableRoutesRepository,
    private val myRoutesRepository: MyRoutesRepository,
    private val userIdRepository: UserIdRepository
) {

    fun getAvailableRoutes() = availableRoutesRepository.getAvailableRoutes()

    fun getMyRoutes() = myRoutesRepository.getMyRoutes(userIdRepository.getUserId())
}