package ru.vlabum.android.gb.myinstax.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.vlabum.android.gb.myinstax.model.Data
import ru.vlabum.android.gb.myinstax.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private var model: Data = Data()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun calcValue(key: Int): Int {
        model.setAt(key, model.getAt(key) + 1)
        return model.getAt(key)
    }

    fun counterClick1() = viewState.setViewText1(calcValue(1))

    fun counterClick2() = viewState.setViewText2(calcValue(2))

    fun counterClick3() = viewState.setViewText3(calcValue(3))

}