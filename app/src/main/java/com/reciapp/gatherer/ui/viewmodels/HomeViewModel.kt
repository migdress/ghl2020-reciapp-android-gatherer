package com.reciapp.gatherer.ui.viewmodels

import com.reciapp.gatherer.domain.uc.HomeUseCase

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    fun getUserName() = homeUseCase.getUserName()
}