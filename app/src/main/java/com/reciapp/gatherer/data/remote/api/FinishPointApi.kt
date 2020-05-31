package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.point.FinishPointRequest
import com.reciapp.gatherer.data.remote.models.route.AssignRouteResponse
import com.reciapp.gatherer.data.remote.models.route.RouteResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PUT

interface FinishPointApi {

    @PUT("v1")
    fun finishPointRoute(@Body finishPointRequest: FinishPointRequest): Single<RouteResponse>
}