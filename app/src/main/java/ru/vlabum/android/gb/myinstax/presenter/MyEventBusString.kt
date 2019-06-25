package ru.vlabum.android.gb.myinstax.presenter

import io.reactivex.subjects.PublishSubject

object MyEventBusString {

    private val bus: PublishSubject<CharSequence> = PublishSubject.create();

    public fun send(s: CharSequence) {
        bus.onNext(s)
    }

    public fun toObservable(): PublishSubject<CharSequence> {
        return bus
    }

}