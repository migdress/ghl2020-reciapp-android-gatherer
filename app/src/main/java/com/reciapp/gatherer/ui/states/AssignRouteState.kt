package com.reciapp.gatherer.ui.states

sealed class AssignRouteState {
    object Loading : AssignRouteState()
    object Success : AssignRouteState()
    object Failure : AssignRouteState()
}