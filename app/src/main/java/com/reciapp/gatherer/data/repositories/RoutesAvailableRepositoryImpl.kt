package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.mappers.RouteMapper
import com.reciapp.gatherer.data.remote.api.RoutesAvailableApi
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.repositories.RoutesAvailableRepository
import io.reactivex.Single

class RoutesAvailableRepositoryImpl(
    private val routesAvailableApi: RoutesAvailableApi,
    private val routeMapper: RouteMapper
) : RoutesAvailableRepository {

    override fun getRoutesAvailable(): Single<List<Route>> =
        routesAvailableApi.getRoutesAvailable()
            .map { routesAvailable ->
                routesAvailable.routes.map(routeMapper.mapToDomainRoute)
            }
}