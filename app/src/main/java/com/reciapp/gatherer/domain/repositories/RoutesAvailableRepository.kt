package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.domain.models.Route
import io.reactivex.Single

interface RoutesAvailableRepository {

    fun getRoutesAvailable(): Single<List<Route>>
}