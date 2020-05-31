package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.R
import com.reciapp.gatherer.data.remote.NetworkHelper
import com.reciapp.gatherer.data.remote.api.LoginApi
import com.reciapp.gatherer.data.remote.api.RoutesAvailableApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule: Module = module {

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.login_base_url)
        ).create(LoginApi::class.java)
    }

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.routes_available_base_url)
        ).create(RoutesAvailableApi::class.java)
    }
}