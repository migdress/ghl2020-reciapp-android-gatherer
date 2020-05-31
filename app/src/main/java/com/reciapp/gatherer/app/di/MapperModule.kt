package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.data.mappers.LoginMapper
import com.reciapp.gatherer.data.mappers.RouteMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        LoginMapper()
    }

    factory {
        RouteMapper()
    }
}