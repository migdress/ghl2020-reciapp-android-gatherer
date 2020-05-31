package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.domain.models.Route
import io.reactivex.Single

interface AvailableRoutesRepository {

    fun getAvailableRoutes(): Single<List<Route>>
}