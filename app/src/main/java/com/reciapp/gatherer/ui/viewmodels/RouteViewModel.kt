package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.domain.uc.RouteUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.AssignRouteState
import com.reciapp.gatherer.ui.states.FinishPointRouteState
import com.reciapp.gatherer.ui.states.StartRouteState

class RouteViewModel(
    routeInitial: Route,
    private val routeUseCase: RouteUseCase,
    private val _assignRouteState: MutableLiveData<AssignRouteState>,
    private val _startRouteState: MutableLiveData<StartRouteState>,
    private val _finishPointRouteState: MutableLiveData<FinishPointRouteState>
) : BaseViewModel() {

    var route: Route = routeInitial
        private set

    fun getAssignRouteStateLiveData(): LiveData<AssignRouteState> = _assignRouteState

    fun getStartRouteStateLiveData(): LiveData<StartRouteState> = _startRouteState

    fun getFinishPointRouteStateLiveData(): LiveData<FinishPointRouteState> = _finishPointRouteState

    fun assignRoute() {
        addDisposable(
            routeUseCase.assignRoute(route.id)
                .doOnSubscribe {
                    _assignRouteState.postValue(AssignRouteState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    route.status = Route.STATUS.ASSIGNED
                    _assignRouteState.value = AssignRouteState.Success
                }, {
                    _assignRouteState.value = AssignRouteState.Failure
                })
        )
    }

    fun startRoute() {
        addDisposable(
            routeUseCase.startRoute(route.id)
                .doOnSubscribe {
                    _startRouteState.postValue(StartRouteState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    route = it
                    _startRouteState.value = StartRouteState.Success
                }, {
                    _startRouteState.value = StartRouteState.Failure
                })

        )
    }

    fun markPointComplete() {
        addDisposable(
            routeUseCase.finishPointRoute(route.id, route.pickingPoints.first().id)
                .doOnSubscribe {
                    _finishPointRouteState.postValue(FinishPointRouteState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    this.route = it
                    _finishPointRouteState.value = FinishPointRouteState.Success
                }, {
                    _finishPointRouteState.value = FinishPointRouteState.Failure
                })
        )
    }
}