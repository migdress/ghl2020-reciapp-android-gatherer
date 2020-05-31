package com.reciapp.gatherer.domain.uc

import com.reciapp.gatherer.data.remote.models.LoginRequest
import com.reciapp.gatherer.domain.repositories.LoginRepository
import com.reciapp.gatherer.domain.repositories.UserRepository
import io.reactivex.Completable

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository
) {
    fun login(username: String, password: String): Completable =
        loginRepository.login(LoginRequest(username, password))
            .flatMapCompletable {
                userRepository.saveUser(it)
                Completable.complete()
            }
}