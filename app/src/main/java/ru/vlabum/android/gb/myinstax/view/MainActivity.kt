package ru.vlabum.android.gb.myinstax.view

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.vlabum.android.gb.myinstax.R
import ru.vlabum.android.gb.myinstax.presenter.MainPresenter
import ru.vlabum.android.gb.myinstax.presenter.MyEventBusString
import timber.log.Timber

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    private var disposable_tv: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("hello")

        text_input_et.textChanges()
            .subscribeOn(Schedulers.io())
            .map { t ->
                presenter.onTextChanged(text_input_et.text)
                t
            }
            .subscribe(MyEventBusString.toObservable())

        disposable_tv = MyEventBusString.toObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t -> text_out_tv.text = t }
    }

    override fun onDestroy() {
        disposable_tv?.dispose()
        super.onDestroy()
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

    override fun setViewText4(value: CharSequence) {
        text_out_tv2.text = value
    }

    fun onBtn1Click(button: View) = presenter.counterClick1()

    fun onBtn2Click(button: View) = presenter.counterClick2()

    fun onBtn3Click(button: View) = presenter.counterClick3()

}
