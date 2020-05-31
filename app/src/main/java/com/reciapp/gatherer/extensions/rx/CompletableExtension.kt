package com.reciapp.gatherer.extensions.rx

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.applySchedulers(
    workerScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): Completable {
    return subscribeOn(workerScheduler)
        .observeOn(observerScheduler)
}