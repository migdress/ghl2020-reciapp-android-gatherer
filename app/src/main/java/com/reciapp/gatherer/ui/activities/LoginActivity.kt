package com.reciapp.gatherer.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.reciapp.gatherer.R
import com.reciapp.gatherer.extensions.ui.hideLoaderView
import com.reciapp.gatherer.extensions.ui.hideLoaderViewWithDelay
import com.reciapp.gatherer.extensions.ui.showLoaderView
import com.reciapp.gatherer.ui.states.LoginState
import com.reciapp.gatherer.ui.viewmodels.LoginViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListeners()
        initSubscriptions()
    }

    private fun initListeners() {
        compositeDisposable.addAll(
            tieUserName.textChanges()
                .mergeWith(tieUserPassword.textChanges())
                .subscribe({
                    checkFieldsForEmptyValues()
                }, {
                    it.printStackTrace()
                }),
            btnLogin.clicks()
                .subscribe({
                    loginViewModel.login(
                        tieUserName.text.toString(),
                        tieUserPassword.text.toString()
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun initSubscriptions() {
        loginViewModel.getLoginStateLiveData().observe(this, Observer {
            when (it) {
                is LoginState.Loading -> {
                    showLoaderView()
                }
                is LoginState.Success -> {
                    hideLoaderViewWithDelay()
                    HomeActivity.launch(this)
                    finish()
                }
                is LoginState.Failure -> {
                    hideLoaderView()
                    Toast.makeText(this, R.string.error_server, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, LoginActivity::class.java))
        }
    }

    private fun checkFieldsForEmptyValues() {
        val user: String = tieUserName.text.toString()
        val password: String = tieUserPassword.text.toString()
        btnLogin.isEnabled = !(user.isEmpty() || password.isEmpty())
    }
}
