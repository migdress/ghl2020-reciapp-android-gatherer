package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import io.reactivex.Completable

interface RouteRepository {

    fun assignRoute(request: AssignRouteRequest): Completable
}