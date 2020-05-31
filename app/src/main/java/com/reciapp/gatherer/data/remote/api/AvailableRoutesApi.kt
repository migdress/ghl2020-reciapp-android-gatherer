package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.routes.RoutesAvailableResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AvailableRoutesApi {

    @GET("v1")
    fun getAvailableRoutes(): Single<RoutesAvailableResponse>
}