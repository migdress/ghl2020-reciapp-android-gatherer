package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.uc.RoutesUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.RoutesAvailableState

class RoutesAvailableViewModel(
    private val routesUseCase: RoutesUseCase,
    private val _routesAvailableState: MutableLiveData<RoutesAvailableState>
) : BaseViewModel() {

    fun getRoutesAvailableStateLiveData(): LiveData<RoutesAvailableState> = _routesAvailableState

    fun getRoutesAvailable() {
        addDisposable(
            routesUseCase.getRoutesAvailable()
                .doOnSubscribe {
                    _routesAvailableState.postValue(RoutesAvailableState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    _routesAvailableState.value = RoutesAvailableState.Success(it)

                }, {
                    _routesAvailableState.value = RoutesAvailableState.Failure

                })
        )
    }
}