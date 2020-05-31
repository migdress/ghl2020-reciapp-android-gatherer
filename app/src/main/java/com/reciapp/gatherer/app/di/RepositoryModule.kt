package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.data.repositories.LoginRepositoryImpl
import com.reciapp.gatherer.data.repositories.RouteRepositoryImpl
import com.reciapp.gatherer.data.repositories.RoutesAvailableRepositoryImpl
import com.reciapp.gatherer.data.repositories.UserRepositoryImpl
import com.reciapp.gatherer.domain.repositories.LoginRepository
import com.reciapp.gatherer.domain.repositories.RouteRepository
import com.reciapp.gatherer.domain.repositories.RoutesAvailableRepository
import com.reciapp.gatherer.domain.repositories.SaveUserRepository
import com.reciapp.gatherer.domain.repositories.UserIdRepository
import com.reciapp.gatherer.domain.repositories.UserNameRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<LoginRepository> {
        LoginRepositoryImpl(
            loginApi = get(),
            mapper = get()
        )
    }

    factory<SaveUserRepository> {
        UserRepositoryImpl(
            userPreferences = get()
        )
    }

    factory<UserIdRepository> {
        UserRepositoryImpl(
            userPreferences = get()
        )
    }

    factory<UserNameRepository> {
        UserRepositoryImpl(
            userPreferences = get()
        )
    }

    factory<RoutesAvailableRepository> {
        RoutesAvailableRepositoryImpl(
            routesAvailableApi = get(),
            routeMapper = get()
        )
    }

    factory<RouteRepository> {
        RouteRepositoryImpl(
            assignRouteApi = get()
        )
    }
}