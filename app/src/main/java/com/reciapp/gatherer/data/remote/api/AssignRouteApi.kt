package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.route.AssignRouteRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.PUT

interface AssignRouteApi {

    @PUT("v1")
    fun assignRoute(@Body assignRouteRequest: AssignRouteRequest): Completable
}