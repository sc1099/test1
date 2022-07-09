package com.example.base

import android.app.Application

open class BaseApplication:Application() {
    companion object{
        var sApplication:Application?  = null
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }


}