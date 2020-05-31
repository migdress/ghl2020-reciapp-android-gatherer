package com.reciapp.gatherer.data.remote.api

import com.reciapp.gatherer.data.remote.models.routes.MyRoutesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MyRoutesApi {

    @GET("v1/{userId}")
    fun getMyRoutes(@Path("userId") userId: String): Single<MyRoutesResponse>
}