package com.example.mykotlin.Mylistview

fun getList():MutableList<People>{
    val list = mutableListOf<People>()
    for(i in 0..10){
        list.add(People("ssssssscccccccc$i"))
    }
    return list
}

data class People(var name:String) {



}