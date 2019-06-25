package ru.vlabum.android.gb.myinstax.presenter

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Function4
import io.reactivex.functions.Predicate
import timber.log.Timber

class RxStuff {

    private var disposable: Disposable? = null

    private fun getStringObserver(): Observer<String> {
        return object : Observer<String> {

            override fun onComplete() {
                Timber.d("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                Timber.d("onSubscribe")
            }

            override fun onNext(s: String) {
                Timber.d("onNext $s")
            }

            override fun onError(e: Throwable) {
                Timber.d("onError")
            }

        }
    }

    private fun getListStringObserver(): Observer<List<String>> {
        return object : Observer<List<String>> {

            override fun onComplete() {
                Timber.d("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                Timber.d("onSubscribe")
            }

            override fun onNext(t: List<String>) {
                for (s in t) {
                    Timber.d("onNext $s")
                }
                Timber.d("onNext End")
            }

            override fun onError(e: Throwable) {
                Timber.d("onError")
            }

        }
    }

    public fun run() {
//        just()
//        from()
//        interval()
//        take()
//        skip()
//        map()
//        distinct()
//        filter()
//        merge()
//        zip()
        flatMap()
    }

    fun just() {
        val justObservable: Observable<String> = Observable.just("just1", "just2")
        justObservable.subscribe(getStringObserver())
    }

    fun map() {
        val mapObservable: Observable<String> = Observable.fromIterable(arrayListOf("map1", "map2", "map3"))
            .map(object : Function<String, String> {
                override fun apply(t: String): String {
                    return "${t}_$t"
                }
            })
        mapObservable.subscribe(getStringObserver())
    }

    fun distinct() {
        val mapObservable: Observable<String> =
            Observable.fromIterable(arrayListOf("map", "map0", "map1", "map2", "map3"))
                .map(object : Function<String, String> {
                    override fun apply(t: String): String {
                        return if (t.contains("0")) t else "${t}0"
                    }
                })
                .distinct()
        mapObservable.subscribe(getStringObserver())
    }

    fun filter() {
        val filterObservable: Observable<String> =
            Observable.fromIterable(arrayListOf("_filter", "filter", "filter10", "filter22"))
                .filter(object : Predicate<String> {
                    override fun test(t: String): Boolean {
                        return !t.contains("1")
                    }
                })
        filterObservable.subscribe(getStringObserver())
    }

    fun merge() {
        val mergeObservable: Observable<String> =
            Observable.fromIterable(arrayListOf("_merge", "merge1", "merge2", "merge3", "merge4"))
                .skip(1)
                .mergeWith(Observable.fromIterable(arrayListOf("_merge", "merge2", "merge3", "merge4", "merge5")))
        mergeObservable.subscribe(getStringObserver())
    }

    fun zip() {
        val observable1: Observable<String> = Observable.just("zip1")
        val observable2: Observable<String> = Observable.just("zip2")
        val observable3: Observable<String> = Observable.just("zip3")
        val observable4: Observable<String> = Observable.just("zip4")

        val zipObservable: Observable<List<String>> = Observable.zip(
            observable1, observable2, observable3, observable4,
            object : Function4<String, String, String, String, List<String>> {
                override fun apply(t1: String, t2: String, t3: String, t4: String): List<String> {
                    return arrayListOf(t1, t2, t3, t4)
                }
            })

        zipObservable.subscribe(getListStringObserver())
    }

    fun flatMap() {
        val observable1: Observable<String> = Observable.just("flatMap1")
        val observable2: Observable<String> = Observable.just("flatMap2")
        val observable3: Observable<String> = Observable.just("flatMap3")
        val observable4: Observable<String> = Observable.just("flatMap4")

        val observable: Observable<String> = Observable
            .zip(observable1, observable2, observable3, observable4,
                object : Function4<String, String, String, String, List<String>> {
                    override fun apply(t1: String, t2: String, t3: String, t4: String): List<String> {
                        return arrayListOf(t1, t2, t3, t4)
                    }
                })
            .flatMap(object : Function<List<String>, ObservableSource<String>> {
                override fun apply(t: List<String>): ObservableSource<String> {
                    return Observable.fromIterable(t)
                }
            })
        observable.subscribe(getStringObserver())

    }

}