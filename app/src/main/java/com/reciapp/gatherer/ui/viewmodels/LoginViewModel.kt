package com.reciapp.gatherer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reciapp.gatherer.domain.uc.LoginUseCase
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.states.LoginState

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val _loginState: MutableLiveData<LoginState>
) : BaseViewModel() {

    fun getLoginStateLiveData(): LiveData<LoginState> = _loginState

    fun login(username: String, password: String) {
        addDisposable(
            loginUseCase.login(username, password)
                .doOnSubscribe {
                    _loginState.postValue(LoginState.Loading)
                }
                .applySchedulers()
                .subscribe({
                    _loginState.value = LoginState.Success
                }, {
                    _loginState.value = LoginState.Failure
                })
        )
    }
}