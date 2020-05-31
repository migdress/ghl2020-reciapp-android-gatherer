package com.reciapp.gatherer.data.remote.models.route

import com.squareup.moshi.Json

data class RouteResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "materials")
    val materials: List<String>,
    @Json(name = "sector")
    val sector: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "shift")
    val shift: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "picking_points")
    val pickingPoints: List<PickingPointResponse>
)