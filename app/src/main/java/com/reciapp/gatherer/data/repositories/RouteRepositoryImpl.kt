package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.mappers.RouteMapper
import com.reciapp.gatherer.data.remote.api.AssignRouteApi
import com.reciapp.gatherer.data.remote.api.StartRouteApi
import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import com.reciapp.gatherer.data.remote.models.route.StartRouteRequest
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.repositories.RouteRepository
import io.reactivex.Completable
import io.reactivex.Single

class RouteRepositoryImpl(
    private val assignRouteApi: AssignRouteApi,
    private val startRouteApi: StartRouteApi,
    private val mapper: RouteMapper
) : RouteRepository {

    override fun assignRoute(request: AssignRouteRequest): Completable =
        assignRouteApi.assignRoute(request)

    override fun startRoute(request: StartRouteRequest): Single<Route> =
        startRouteApi.startRoute(request).map { mapper.mapToDomainRoute(it.route) }
}