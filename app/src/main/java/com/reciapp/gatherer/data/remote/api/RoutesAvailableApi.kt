package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.routes.RoutesAvailableResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RoutesAvailableApi {

    @GET("v1")
    fun getRoutesAvailable(): Single<RoutesAvailableResponse>
}