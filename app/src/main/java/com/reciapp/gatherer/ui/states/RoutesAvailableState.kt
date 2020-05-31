package com.reciapp.gatherer.ui.states

import com.reciapp.gatherer.domain.models.Route

sealed class RoutesAvailableState {
    object Loading : RoutesAvailableState()
    data class Success(val routes: List<Route>) : RoutesAvailableState()
    object Failure : RoutesAvailableState()
}