package com.reciapp.gatherer.app.di

import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.ui.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel {
        LoginViewModel(
            loginUseCase = get(),
            _loginState = MutableLiveData()
        )
    }
}