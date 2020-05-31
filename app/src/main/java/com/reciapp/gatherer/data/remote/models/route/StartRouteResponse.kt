package com.reciapp.gatherer.data.remote.models.route

import com.squareup.moshi.Json

data class StartRouteResponse(
    @Json(name = "assigned_route")
    val route: RouteResponse
)