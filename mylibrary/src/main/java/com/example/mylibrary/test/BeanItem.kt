package com.example.mylibrary.test

import androidx.databinding.ObservableField

data class BeanItem(var name:String) {
    var _name = ObservableField<String>()

    init {
        _name.set(name)
    }


}