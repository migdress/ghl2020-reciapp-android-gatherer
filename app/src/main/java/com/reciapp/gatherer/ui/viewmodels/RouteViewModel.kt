package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.uc.RouteUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.RouteAssignState

class RouteViewModel(
    routeInitial: Route,
    private val routeUseCase: RouteUseCase,
    private val _routeAssignState: MutableLiveData<RouteAssignState>
) : BaseViewModel() {

    var route: Route = routeInitial
        private set

    fun getRouteAssignStateLiveData(): LiveData<RouteAssignState> = _routeAssignState

    fun assignRoute() {
        addDisposable(
            routeUseCase.assignRoute(route.id)
                .doOnSubscribe {
                    _routeAssignState.postValue(RouteAssignState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    route.status = Route.STATUS.ASSIGNED
                    _routeAssignState.value = RouteAssignState.Success
                }, {
                    _routeAssignState.value = RouteAssignState.Failure
                })
        )
    }

    fun markPointComplete() {
        println("Test mark point complete")
    }
}