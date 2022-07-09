package com.example.mykotlin.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLifeCycle :LifecycleObserver {
    private   val TAG = "MyLifeCycle"
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun sc_creat(){
        Log.d(TAG, "sc_creat:*********************************** ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun sc_resume(){
        Log.d(TAG, "sc_resume: *********************************** ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun sc_stop(){
        Log.d(TAG, "sc_stop: *********************************** ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun sc_destroy(){
        Log.d(TAG, "sc_destroy:***********************************  ")
    }



}