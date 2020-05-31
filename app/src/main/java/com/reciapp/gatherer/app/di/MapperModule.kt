package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.data.mappers.LoginMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        LoginMapper()
    }
}