package com.reciapp.gatherer.ui.states

sealed class FinishPointRouteState  {
    object Loading : FinishPointRouteState()
    object Success : FinishPointRouteState()
    object Failure : FinishPointRouteState()
}