package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.data.mappers.RouteMapper
import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import com.reciapp.gatherer.data.remote.models.route.StartRouteRequest
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.repositories.RouteRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository
import io.reactivex.Single

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

    fun startRoute(routeId: String): Single<Route> =
        routeRepository.startRoute(
            StartRouteRequest(
                userId = userIdRepository.getUserId(),
                routeId = routeId
            )
        )
}