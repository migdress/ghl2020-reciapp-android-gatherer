package com.reciapp.gatherer.data.remote.models.routes

import com.squareup.moshi.Json

data class RoutesAvailableResponse(
    @Json(name = "routes")
    val routes: List<RouteResponse>
)