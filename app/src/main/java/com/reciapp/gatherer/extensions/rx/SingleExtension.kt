package com.reciapp.gatherer.extensions.rx

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline fun <reified T> Single<T>.applySchedulers(
    workerScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): Single<T> {
    return subscribeOn(workerScheduler)
        .observeOn(observerScheduler)
}