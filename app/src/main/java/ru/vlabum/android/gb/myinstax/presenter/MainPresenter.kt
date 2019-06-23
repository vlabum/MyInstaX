package ru.vlabum.android.gb.myinstax.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vlabum.android.gb.myinstax.model.Data
import ru.vlabum.android.gb.myinstax.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private var model: Data = Data()

    private var observerClick1 = MainObserverClick(viewState::setViewText1)
    private var observerClick2 = MainObserverClick(viewState::setViewText2)
    private var observerClick3 = MainObserverClick(viewState::setViewText3)
    private var observerClick4 = MainObserverClick(viewState::setViewText4)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        RxStuff().run()
    }

    fun counterClick1() {
        val key = 1
        model.getAt(key)
            .observeOn(Schedulers.computation())
            .map { t ->
                val ret = t + 1
                model.setAt(key, ret).subscribe()
                ret
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerClick1)
    }

    fun counterClick2() {
        val key = 2
        model.getAt(key)
            .observeOn(Schedulers.computation())
            .map { t ->
                val ret = t + 1
                model.setAt(key, ret).subscribe()
                ret
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerClick2)
    }

    fun counterClick3() {
        val key = 3
        model.getAt(key)
            .observeOn(Schedulers.computation())
            .map { t ->
                val ret = t + 1
                model.setAt(key, ret).subscribe()
                ret
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerClick3)
    }

    fun onTextChanged(text: CharSequence) {
        MyEventBusString.toObservable()
            .observeOn(Schedulers.computation())
            .map { t ->
                Thread.sleep(1000)
                t
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerClick4)
    }

}