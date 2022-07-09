package com.example.mykotlin

import android.app.Activity

//object表示单例类
object baseActivitycolltor{
    private val list = mutableListOf<Activity>()
    fun add(activity: Activity){
        list.add(activity)
    }
    fun remove(activity: Activity){
        list.remove(activity)
    }
    fun finishall(){
        for(activity in list){
            activity.finish()
        }
        list.clear()
    }
}
