package ru.vlabum.android.gb.myinstax.model

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Data {

    private val counters = mutableMapOf<Int, Int>()

    private fun get(key: Int): Int {
        if (!counters.containsKey(key)) {
            counters[key] = 0
        }
        return counters[key]!!
    }

    fun getAt(key: Int): Observable<Int> {
        return Observable
            .fromCallable { get(key) }
            .subscribeOn(Schedulers.io())
    }

    fun setAt(key: Int, value: Int): Completable {
        return Completable.fromAction {
            counters[key] = value
        }
    }

}