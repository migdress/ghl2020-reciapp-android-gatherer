package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.uc.RoutesUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.AvailableRoutesState

class AvailableRoutesViewModel(
    private val routesUseCase: RoutesUseCase,
    private val _availableRoutesState: MutableLiveData<AvailableRoutesState>
) : BaseViewModel() {

    fun getRoutesAvailableStateLiveData(): LiveData<AvailableRoutesState> = _availableRoutesState

    fun getRoutesAvailable() {
        addDisposable(
            routesUseCase.getAvailableRoutes()
                .doOnSubscribe {
                    _availableRoutesState.postValue(AvailableRoutesState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    _availableRoutesState.value = AvailableRoutesState.Success(it)

                }, {
                    _availableRoutesState.value = AvailableRoutesState.Failure

                })
        )
    }
}