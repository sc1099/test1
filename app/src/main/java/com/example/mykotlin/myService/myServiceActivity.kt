package com.example.mykotlin.myService

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.webkit.WebViewClient
import com.example.mykotlin.Mylistview.People
import com.example.mykotlin.R
import com.example.mykotlin.md.mdActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_my_service.*
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class myServiceActivity : AppCompatActivity() {
    private  val TAG = "myServiceActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startmyServiceActivity(context: Context, key:String, value:String){
            var intent = Intent(context, myServiceActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }


    val mhandle = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){

            }
        }
    }
    //todo activity和service绑定后，就可以使用service里面的binder方法
    private lateinit var mdownloadBinder:MyService.downloadBinder
    private val connection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mdownloadBinder = service as MyService.downloadBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_service)
        Log.d(TAG, "主线程id: "+Thread.currentThread().id+"  "+Thread.currentThread().name)
        thread {
            Log.d(TAG, "子线程id： "+Thread.currentThread().id+"  "+Thread.currentThread().name)
        }
        bt.setOnClickListener {
            var intent = Intent(this,MyService::class.java)
            startService(intent)
        }
        bt2.setOnClickListener {
            var intent = Intent(this,MyService::class.java)
            stopService(intent)
        }
        bt3.setOnClickListener {
            var intent = Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)
        }
        bt4.setOnClickListener {
            unbindService(connection)
        }
        bt5.setOnClickListener {
            //todo retrofit2 的baseurl必须以/节为
            var url = "https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2022-03/R/"
            mdownloadBinder.startdownload(url)
        }

    }


    fun gsontest_okhttp(){
        var gson = Gson()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://api.openweathermap.org/data/2.5/")
            .build()
        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: ")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onResponse: "+response.body()?.string())
                var p: person = gson.fromJson(response.body().toString(), person::class.java)
                Log.d(TAG, "onResponse: " + p.cod + "   " + p.message)
            }
        })

    }


    fun okhttptest(){
        var client = OkHttpClient()
        var requestBody = FormBody.Builder()
            .add("username","sc")
            .build()
        var request = Request.Builder()
            .url("https://www.baidu.com")
            .post(requestBody)
            .build()
        var code = 0
        var response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: ")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "code:      " + response.code())
                response.headers().toMultimap().forEach {
                    Log.d(TAG, "headers: "+it.key+"    "+it.value)
                }
                Log.d(TAG, "onResponse: " + (response.body()).toString())
            }
        })
    }

    fun webtest(){
        wb.settings.javaScriptEnabled = true
        wb.webViewClient = WebViewClient()
        wb.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
        wb.loadUrl("https://www.baidu.com")
    }
}