package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.data.repositories.LoginRepositoryImpl
import com.reciapp.gatherer.data.repositories.UserRepositoryImpl
import com.reciapp.gatherer.domain.repositories.LoginRepository
import com.reciapp.gatherer.domain.repositories.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<LoginRepository> {
        LoginRepositoryImpl(
            loginApi = get(),
            mapper = get()
        )
    }

    factory<UserRepository> {
        UserRepositoryImpl(
            userPreferences = get()
        )
    }
}