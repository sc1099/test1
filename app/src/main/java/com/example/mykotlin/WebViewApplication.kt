package com.example.mykotlin

import com.example.base.BaseApplication
import com.example.base.loadsir.*
import com.kingja.loadsir.core.LoadSir

class WebViewApplication:BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback( ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback( LoadingCallback())
            .addCallback( TimeoutCallback())
            .addCallback( CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java)
                .commit()

    }

}