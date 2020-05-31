package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.uc.RoutesUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.MyRoutesState

class MyRoutesViewModel(
    private val routesUseCase: RoutesUseCase,
    private val _myRoutesState: MutableLiveData<MyRoutesState>
) : BaseViewModel() {

    fun getMyRoutesStateLiveData(): LiveData<MyRoutesState> = _myRoutesState

    fun getMyRoutes() {
        addDisposable(
            routesUseCase.getMyRoutes()
                .doOnSubscribe {
                    _myRoutesState.postValue(MyRoutesState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    _myRoutesState.value = MyRoutesState.Success(it)
                }, {
                    _myRoutesState.value = MyRoutesState.Failure

                })
        )
    }
}