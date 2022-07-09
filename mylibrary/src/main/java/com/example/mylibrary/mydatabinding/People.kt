package com.example.mylibrary.mydatabinding

import androidx.databinding.Observable
import androidx.databinding.ObservableField

data class People(var sc_name:String, var sc_age:Int = 0,var imageurl:String = "") {
    var _sc_name:ObservableField<String> = ObservableField<String>(sc_name)
    var _imageurl=ObservableField<String>()
    init {
        _imageurl.set(imageurl)
    }
}