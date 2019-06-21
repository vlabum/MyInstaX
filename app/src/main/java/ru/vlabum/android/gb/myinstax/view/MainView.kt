package ru.vlabum.android.gb.myinstax.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setViewText1(value: Int)
    fun setViewText2(value: Int)
    fun setViewText3(value: Int)
}