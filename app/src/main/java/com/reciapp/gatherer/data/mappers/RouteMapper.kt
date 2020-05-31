package com.reciapp.gatherer.data.mappers

import com.reciapp.gatherer.data.remote.models.routes.RoutesAvailableResponse
import com.reciapp.gatherer.domain.models.Route

class RouteMapper {

    val mapToDomainRoute = fun(routeResponse: RoutesAvailableResponse.RouteResponse): Route {
        return Route(
            id = routeResponse.id,
            materials = routeResponse.materials,
            sector = routeResponse.sector,
            status = Route.STATUS.AVAILABLE,
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
}