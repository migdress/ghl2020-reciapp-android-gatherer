package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.route.StartRouteRequest
import com.reciapp.gatherer.data.remote.models.route.StartRouteResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PUT

interface StartRouteApi {

    @PUT("v1")
    fun startRoute(@Body startRouteRequest: StartRouteRequest): Single<StartRouteResponse>
}