package com.example.mykotlin.jetpack.fragmentAndViewModle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Fragment1ViewModel : ViewModel() {
    private val viewModelLifedata  = MutableLiveData<Int>()
    private var i = 0
    fun getdata(): MutableLiveData<Int> {
        return viewModelLifedata
    }


    fun addone(){
        i++
        viewModelLifedata.value = i
    }
}