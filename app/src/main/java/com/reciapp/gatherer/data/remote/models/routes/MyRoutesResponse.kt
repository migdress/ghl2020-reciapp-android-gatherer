package com.reciapp.gatherer.data.remote.models.routes

import com.squareup.moshi.Json

data class MyRoutesResponse(
    @Json(name = "assigned_routes")
    val routes: List<RouteResponse>
)