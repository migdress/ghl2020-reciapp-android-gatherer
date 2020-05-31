package com.reciapp.gatherer.app

import android.app.Application
import com.reciapp.gatherer.app.di.apiModule
import com.reciapp.gatherer.app.di.mapperModule
import com.reciapp.gatherer.app.di.preferencesModule
import com.reciapp.gatherer.app.di.repositoryModule
import com.reciapp.gatherer.app.di.useCasesModule
import com.reciapp.gatherer.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AppController)
            modules(
                listOf(
                    apiModule,
                    preferencesModule,
                    repositoryModule,
                    mapperModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }
}