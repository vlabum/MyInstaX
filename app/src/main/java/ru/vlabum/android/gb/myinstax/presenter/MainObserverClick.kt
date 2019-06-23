package ru.vlabum.android.gb.myinstax.presenter

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MainObserverClick<T>(val func: (T) -> Unit) : Observer<T> {

    override fun onComplete() {}

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(value: T) {
        func(value)
    }

    override fun onError(e: Throwable) {
        Timber.e(e)
    }

}