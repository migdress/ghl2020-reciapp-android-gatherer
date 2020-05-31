package com.reciapp.gatherer.data.repositories

import com.reciapp.gatherer.data.mappers.RouteMapper
import com.reciapp.gatherer.data.remote.api.MyRoutesApi
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.repositories.MyRoutesRepository
import io.reactivex.Single

class MyRoutesRepositoryImpl(
    private val myRoutesApi: MyRoutesApi,
    private val routeMapper: RouteMapper
) : MyRoutesRepository {

    override fun getMyRoutes(userId: String): Single<List<Route>> =
        myRoutesApi.getMyRoutes(userId)
            .map { routesAvailable ->
                routesAvailable.routes.map(routeMapper.mapToDomainRoute)
            }
}