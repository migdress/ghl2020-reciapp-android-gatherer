package com.reciapp.gatherer.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Route(
    val id: String,
    val materials: List<String>,
    val sector: String,
    var status: STATUS,
    val shift: String,
    val date: String,
    val pickingPoints: List<PickingPoint>
) : Parcelable {
    @Parcelize
    data class PickingPoint(
        val id: String,
        val country: String,
        val city: String,
        val addressFirst: String,
        val addressSecond: String,
        val latitude: Double,
        val longitude: Double
    ) : Parcelable

    enum class STATUS {
        AVAILABLE,
        ASSIGNED,
        INITIATED,
        FINISHED
    }
}