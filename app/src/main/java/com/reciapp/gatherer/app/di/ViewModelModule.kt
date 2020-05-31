package com.reciapp.gatherer.app.di

import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.ui.viewmodels.HomeViewModel
import com.reciapp.gatherer.ui.viewmodels.LoginViewModel
import com.reciapp.gatherer.ui.viewmodels.RouteViewModel
import com.reciapp.gatherer.ui.viewmodels.RoutesAvailableViewModel
import com.reciapp.gatherer.ui.viewmodels.SplashViewModel
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

    viewModel {
        SplashViewModel(
            splashUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            homeUseCase = get()
        )
    }

    viewModel {
        RoutesAvailableViewModel(
            routesUseCase = get(),
            _routesAvailableState = MutableLiveData()
        )
    }

    viewModel { (route: Route) ->
        RouteViewModel(
            routeInitial = route,
            routeUseCase = get(),
            _routeAssignState = MutableLiveData()
        )
    }
}