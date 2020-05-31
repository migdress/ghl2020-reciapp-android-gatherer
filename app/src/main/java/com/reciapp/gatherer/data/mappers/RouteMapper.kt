package com.reciapp.gatherer.data.mappers

import com.reciapp.gatherer.data.remote.models.route.RouteResponse
import com.reciapp.gatherer.domain.models.Route

class RouteMapper {

    val mapToDomainRoute = fun(routeResponse: RouteResponse): Route {
        return Route(
            id = routeResponse.id,
            materials = routeResponse.materials,
            sector = routeResponse.sector,
            status = getStatus(routeResponse.status),
            shift = routeResponse.shift,
            date = routeResponse.date,
            pickingPoints = routeResponse.pickingPoints.map {
                Route.PickingPoint(
                    country = it.country,
                    city = it.city,
                    addressFirst = it.addressFirst,
                    addressSecond = it.addressSecond,
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
        )
    }

    private fun getStatus(status: String): Route.STATUS {
        return when (status) {
            "assigned" -> {
                Route.STATUS.ASSIGNED
            }
            "initiated" -> {
                Route.STATUS.INITIATED
            }
            "finished" -> {
                Route.STATUS.FINISHED
            }
            else -> {
                Route.STATUS.AVAILABLE
            }
        }
    }
}