package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import com.reciapp.gatherer.data.remote.models.route.RouteResponse
import com.reciapp.gatherer.data.remote.models.route.StartRouteRequest
import com.reciapp.gatherer.domain.models.Route
import io.reactivex.Completable
import io.reactivex.Single

interface RouteRepository {

    fun assignRoute(request: AssignRouteRequest): Completable

    fun startRoute(request: StartRouteRequest): Single<Route>
}