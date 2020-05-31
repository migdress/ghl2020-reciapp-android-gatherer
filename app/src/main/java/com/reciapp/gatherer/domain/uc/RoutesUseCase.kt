package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.domain.repositories.RoutesAvailableRepository

class RoutesUseCase(
    private val routesAvailableRepository: RoutesAvailableRepository
) {

    fun getRoutesAvailable() = routesAvailableRepository.getRoutesAvailable()
}