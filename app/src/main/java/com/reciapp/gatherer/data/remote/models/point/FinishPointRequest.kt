package com.reciapp.gatherer.data.remote.models.point

import com.squareup.moshi.Json

data class FinishPointRequest(
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "route_id")
    val routeId: String,
    @Json(name = "picking_point_id")
    val pointId: String
)