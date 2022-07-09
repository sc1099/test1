package com.example.mykotlin.myService

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.mykotlin.myService.myretrofit.downloadService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

open class mDownloadtask() :AsyncTask<String,Int,Int>() {
    constructor(listener: mDownloadListener) : this() {}

    override fun doInBackground(vararg params: String?): Int {
        val downloadurl = params[0]
        var downloadLength = 0L //记录已经下载的文件长度
        val filename = "sc下载"
        val path = "/data/user/0/com.example.mykotlin/files"
        val file = File(path,filename)
        if(file.exists()) {
            downloadLength = file.length()
        }else{
            file.createNewFile()
        }
        getContentLength(downloadurl!!)

        return 0
    }

    private fun getContentLength(baseurl:String):Long{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ds = retrofit.create(downloadService::class.java)
        val response = ds.interface_getContentlength().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.headers().toMultimap().forEach() {
                    Log.d("......获取长度....", "onResponse: " + it.key + "    " + it.value)
                }
                val len = response.body()?.contentLength()?:0
                Log.d("..........", "onResponse: "+len)

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }

        })
       return 0
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
    }

}