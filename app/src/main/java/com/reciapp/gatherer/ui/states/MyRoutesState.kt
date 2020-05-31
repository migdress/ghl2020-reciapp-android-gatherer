package com.reciapp.gatherer.ui.states

import com.reciapp.gatherer.domain.models.Route

sealed class MyRoutesState {
    object Loading : MyRoutesState()
    data class Success(val routes: List<Route>) : MyRoutesState()
    object Failure : MyRoutesState()
}