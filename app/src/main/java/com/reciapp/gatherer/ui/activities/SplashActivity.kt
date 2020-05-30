package com.reciapp.gatherer.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reciapp.gatherer.R
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

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
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    LoginActivity.launch(this)
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