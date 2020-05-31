package com.reciapp.gatherer.ui.states

sealed class RouteAssignState {
    object Loading : RouteAssignState()
    object Success : RouteAssignState()
    object Failure : RouteAssignState()
}