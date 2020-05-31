package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.mappers.RouteMapper
import com.reciapp.gatherer.data.remote.api.AvailableRoutesApi
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.repositories.AvailableRoutesRepository
import io.reactivex.Single

class AvailableRoutesRepositoryImpl(
    private val routesAvailableApi: AvailableRoutesApi,
    private val routeMapper: RouteMapper
) : AvailableRoutesRepository {

    override fun getAvailableRoutes(): Single<List<Route>> =
        routesAvailableApi.getAvailableRoutes()
            .map { routesAvailable ->
                routesAvailable.routes.map(routeMapper.mapToDomainRoute)
            }
}