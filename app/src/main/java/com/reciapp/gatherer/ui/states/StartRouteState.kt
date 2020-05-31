package com.reciapp.gatherer.ui.states

sealed class StartRouteState {
    object Loading : StartRouteState()
    object Success : StartRouteState()
    object Failure : StartRouteState()
}