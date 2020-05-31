package com.reciapp.gatherer.app.di

import com.reciapp.gatherer.R
import com.reciapp.gatherer.data.remote.NetworkHelper
import com.reciapp.gatherer.data.remote.api.AssignRouteApi
import com.reciapp.gatherer.data.remote.api.AvailableRoutesApi
import com.reciapp.gatherer.data.remote.api.FinishPointApi
import com.reciapp.gatherer.data.remote.api.LoginApi
import com.reciapp.gatherer.data.remote.api.MyRoutesApi
import com.reciapp.gatherer.data.remote.api.StartRouteApi
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
        ).create(AvailableRoutesApi::class.java)
    }

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.assing_route_base_url)
        ).create(AssignRouteApi::class.java)
    }

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.my_routes_base_url)
        ).create(MyRoutesApi::class.java)
    }

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.start_route_base_url)
        ).create(StartRouteApi::class.java)
    }

    factory {
        NetworkHelper.getRetrofitInstance(
            androidContext().resources.getString(R.string.finish_point_route_base_url)
        ).create(FinishPointApi::class.java)
    }
}