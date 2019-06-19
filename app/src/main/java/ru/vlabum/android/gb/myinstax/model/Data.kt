package ru.vlabum.android.gb.myinstax.model

class Data {

    private val counters = mutableMapOf<Int, Int>()

    fun getAt(key: Int): Int {
        if (!counters.containsKey(key)) {
            counters[key] = 0
        }
        return counters[key]!!
    }

    fun setAt(key: Int, value: Int) {
        counters[key] = value
    }

}