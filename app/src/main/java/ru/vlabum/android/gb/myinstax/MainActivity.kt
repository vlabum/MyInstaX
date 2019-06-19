package ru.vlabum.android.gb.myinstax

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.vlabum.android.gb.myinstax.androidx.MvpAppCompatActivity
import ru.vlabum.android.gb.myinstax.presenter.MainPresenter
import ru.vlabum.android.gb.myinstax.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun setViewText1(value: Int) {
        btnCounter1.text = String.format("Количество = %d", value)
    }

    override fun setViewText2(value: Int) {
        btnCounter2.text = String.format("Количество = %d", value)
    }

    override fun setViewText3(value: Int) {
        btnCounter3.text = String.format("Количество = %d", value)
    }

    fun onBtn1Click(button: View) = presenter.counterClick1()

    fun onBtn2Click(button: View) = presenter.counterClick2()

    fun onBtn3Click(button: View) = presenter.counterClick3()

}
