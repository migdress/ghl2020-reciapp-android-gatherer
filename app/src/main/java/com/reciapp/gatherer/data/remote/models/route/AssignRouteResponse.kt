package com.reciapp.gatherer.data.remote.models.route

import com.squareup.moshi.Json

data class AssignRouteResponse(
    @Json(name = "assigned_route")
    val route: RouteResponse
)