package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.domain.repositories.MyRoutesRepository
import com.reciapp.gatherer.domain.repositories.RoutesAvailableRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository

class RoutesUseCase(
    private val routesAvailableRepository: RoutesAvailableRepository,
    private val myRoutesRepository: MyRoutesRepository,
    private val userIdRepository: UserIdRepository
) {

    fun getRoutesAvailable() = routesAvailableRepository.getRoutesAvailable()

    fun getMyRoutes() = myRoutesRepository.getMyRoutes(userIdRepository.getUserId())
}