package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.remote.api.AssignRouteApi
import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import com.reciapp.gatherer.domain.repositories.RouteRepository
import io.reactivex.Completable

class RouteRepositoryImpl(
    private val assignRouteApi: AssignRouteApi
) : RouteRepository {

    override fun assignRoute(request: AssignRouteRequest): Completable =
        assignRouteApi.assignRoute(request)
}