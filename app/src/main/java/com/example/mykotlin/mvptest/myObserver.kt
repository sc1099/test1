package com.example.mykotlin.mvptest

import android.util.Log
import com.example.mykotlin.mvptest.po.data
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class myObserver<T> ():Observer<T>{
    private  val TAG = "myObserver"
    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe: 订阅")
    }

    override fun onNext(t: T) {
        Log.d(TAG, "onNext: ")
    }

    override fun onError(e: Throwable) {
        Log.d(TAG, "onError: 出错了..."+e.stackTrace)
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }

}