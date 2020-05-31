package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.uc.RouteUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.AssignRouteState
import com.reciapp.gatherer.ui.states.StartRouteState

class RouteViewModel(
    routeInitial: Route,
    private val routeUseCase: RouteUseCase,
    private val _routeAssignState: MutableLiveData<AssignRouteState>,
    private val _routeStartState: MutableLiveData<StartRouteState>
) : BaseViewModel() {

    var route: Route = routeInitial
        private set

    fun getAssignRouteStateLiveData(): LiveData<AssignRouteState> = _routeAssignState

    fun getStartRouteStateLiveData(): LiveData<StartRouteState> = _routeStartState

    fun assignRoute() {
        addDisposable(
            routeUseCase.assignRoute(route.id)
                .doOnSubscribe {
                    _routeAssignState.postValue(AssignRouteState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    route.status = Route.STATUS.ASSIGNED
                    _routeAssignState.value = AssignRouteState.Success
                }, {
                    _routeAssignState.value = AssignRouteState.Failure
                })
        )
    }

    fun startRoute() {
        addDisposable(
            routeUseCase.startRoute(route.id)
                .doOnSubscribe {
                    _routeStartState.postValue(StartRouteState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    route = it
                    _routeStartState.value = StartRouteState.Success
                }, {
                    _routeStartState.value = StartRouteState.Failure
                })

        )
    }

    fun markPointComplete() {
        // Service mark point
    }
}