package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import com.reciapp.gatherer.domain.repositories.RouteRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository

class RouteUseCase(
    private val routeRepository: RouteRepository,
    private val userIdRepository: UserIdRepository
) {

    fun assignRoute(routeId: String) =
        routeRepository.assignRoute(
            AssignRouteRequest(
                userId = userIdRepository.getUserId(),
                routeId = routeId
            )
        )
}