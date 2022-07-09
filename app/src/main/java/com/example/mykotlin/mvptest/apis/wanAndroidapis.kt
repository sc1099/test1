package com.example.mykotlin.mvptest.apis

import com.example.mykotlin.mvptest.po.data
import com.example.mykotlin.mvptest.po.loginDataClass
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface wanAndroidapis {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") name:String,@Field("password") pwd:String):Observable<loginDataClass<data>>
}