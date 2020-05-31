package com.reciapp.gatherer.domain.models

data class Route(
    val id: String,
    val materials: List<String>,
    val sector: String,
    val shift: String,
    val date: String,
    val pickingPoints: List<PickingPoint>
) {
    data class PickingPoint(
        val country: String,
        val city: String,
        val addressFirst: String,
        val addressSecond: String,
        val latitude: Double,
        val longitude: Double
    )
}