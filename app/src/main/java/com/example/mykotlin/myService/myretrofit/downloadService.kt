package com.example.mykotlin.myService.myretrofit

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface downloadService {
    @Streaming
    @GET("eclipse-inst-jre-win64.exe&mirror_id=1287")
    fun interface_getContentlength():Call<ResponseBody>

}