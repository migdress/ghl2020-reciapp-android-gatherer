package com.reciapp.gatherer.ui.states

import com.reciapp.gatherer.domain.models.Route

sealed class AvailableRoutesState {
    object Loading : AvailableRoutesState()
    data class Success(val routes: List<Route>) : AvailableRoutesState()
    object Failure : AvailableRoutesState()
}