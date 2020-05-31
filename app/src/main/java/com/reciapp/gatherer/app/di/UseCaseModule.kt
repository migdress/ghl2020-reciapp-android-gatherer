package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.domain.uc.LoginUseCase
import com.reciapp.gatherer.domain.uc.SplashUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {

    factory {
        LoginUseCase(
            loginRepository = get(),
            userRepository = get()
        )
    }

    factory {
        SplashUseCase(
            userIdRepository = get()
        )
    }
}