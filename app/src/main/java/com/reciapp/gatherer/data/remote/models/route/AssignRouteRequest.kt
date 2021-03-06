package com.reciapp.gatherer.data.remote.models.route

import com.squareup.moshi.Json

data class AssignRouteRequest(
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "route_id")
    val routeId: String
)