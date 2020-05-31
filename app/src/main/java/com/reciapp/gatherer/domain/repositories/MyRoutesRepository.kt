package com.reciapp.gatherer.domain.repositories

import com.reciapp.gatherer.domain.models.Route
import io.reactivex.Single

interface MyRoutesRepository {

    fun getMyRoutes(userId: String): Single<List<Route>>
}