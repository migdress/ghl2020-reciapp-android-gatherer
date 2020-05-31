package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.data.local.preferences.NamesPreferences
import com.reciapp.gatherer.data.local.PreferencesHelper
import com.reciapp.gatherer.data.local.preferences.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val preferencesModule: Module = module {

    single {
        UserPreferences(
            PreferencesHelper.customPrefs(
                androidContext(),
                NamesPreferences.USER
            )
        )
    }
}