package com.reciapp.gatherer.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reciapp.gatherer.R
import com.reciapp.gatherer.extensions.rx.applySchedulers
import com.reciapp.gatherer.ui.viewmodels.SplashViewModel
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        launchLogin()
    }

    private fun launchLogin() {
        compositeDisposable.add(
            Completable.complete()
                .delay(DELAY_VIEW, TimeUnit.SECONDS)
                .applySchedulers()
                .subscribe {
                    if (splashViewModel.isUserLogged()) {
                        HomeActivity.launch(this)
                    } else {
                        LoginActivity.launch(this)
                    }
                    finish()
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        private const val DELAY_VIEW = 3L
    }
}