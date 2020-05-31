package com.reciapp.gatherer.ui.states

sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    object Failure : LoginState()
}