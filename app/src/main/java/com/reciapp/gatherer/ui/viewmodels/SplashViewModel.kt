package com.reciapp.gatherer.ui.viewmodels

import com.reciapp.gatherer.domain.uc.SplashUseCase

class SplashViewModel(
    private val splashUseCase: SplashUseCase
) : BaseViewModel() {

    fun isUserLogged() = splashUseCase.isUserLogged()
}