package com.reciapp.gatherer.data.remote.models.routes

import com.squareup.moshi.Json

data class PickingPointResponse(
    @Json(name = "id")
    val id: String,
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