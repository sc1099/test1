package com.example.mykotlin.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// TODO: 如果需要上下文引用，则继承AndroidViewModel

class MyViewModle:ViewModel() {

    private val viewModelLifedata  = MutableLiveData<Int>()
    private var i = 0
    fun getdata():MutableLiveData<Int>{
        return viewModelLifedata
    }


    fun addone(){
        i++
        viewModelLifedata.value = i
    }

}