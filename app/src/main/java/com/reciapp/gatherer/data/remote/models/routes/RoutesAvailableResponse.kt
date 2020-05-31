package com.reciapp.gatherer.data.remote.models.routes

import com.squareup.moshi.Json

data class RoutesAvailableResponse(
    @Json(name = "routes")
    val routes: List<RouteResponse>
) {
    data class RouteResponse(
        @Json(name = "id")
        val id: String,
        @Json(name = "materials")
        val materials: List<String>,
        @Json(name = "sector")
        val sector: String,
        @Json(name = "shift")
        val shift: String,
        @Json(name = "date")
        val date: String,
        @Json(name = "PickingPoints")
        val pickingPoints: List<PickingPointResponse>
    )

    data class PickingPointResponse(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "locationid")
        val locationId: String,
        @Json(name = "country")
        val country: String,
        @Json(name = "city")
        val city: String,
        @Json(name = "latitude")
        val latitude: Double,
        @Json(name = "longitude")
        val longitude: Double,
        @Json(name = "address1")
        val addressFirst: String,
        @Json(name = "address2")
        val addressSecond: String
    )
}