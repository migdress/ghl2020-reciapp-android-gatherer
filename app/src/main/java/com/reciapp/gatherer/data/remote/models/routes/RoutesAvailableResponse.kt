package com.reciapp.gatherer.data.remote.models.routes

import com.reciapp.gatherer.data.remote.models.route.RouteResponse
import com.squareup.moshi.Json

data class RoutesAvailableResponse(
    @Json(name = "routes")
    val routes: List<RouteResponse>
)