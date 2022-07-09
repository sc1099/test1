package com.example.mykotlin.RxJava

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mykotlin.R
import com.example.mykotlin.RxJava.api.WangAndroidApi
import com.example.mykotlin.RxJava.bean.ProjectBean
import com.example.mykotlin.myService.myServiceActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class rxJavaActivity : AppCompatActivity() {
    private  val TAG = "rxJavaActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startrxJavaActivity(context: Context, key:String, value:String){
            var intent = Intent(context, rxJavaActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        //防止抖动实验
        RxView.clicks(bt_anti_shake)
            .throttleFirst(2000,TimeUnit.SECONDS) //todo 两秒钟之内，只响应以此
            .subscribe(Consumer {
                createRetrofit()
            })
    }

    private fun AntiShake() {

    }

    val baseurl = "https://www.wanandroid.com/"
    lateinit var wangAndroidApi:WangAndroidApi
    fun createRetrofit(){
       val okHttpClient =  OkHttpClient.Builder()
           .readTimeout(10000, TimeUnit.SECONDS)
           .connectTimeout(10000, TimeUnit.SECONDS)
           .writeTimeout(10000, TimeUnit.SECONDS)
           .build();
       var retrofit= Retrofit.Builder()
           .client(okHttpClient)
           .baseUrl(baseurl)
           .addConverterFactory(GsonConverterFactory.create())
           // 添加rxjava处理工具
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build()
        wangAndroidApi =retrofit.create(WangAndroidApi::class.java)
        //todo observable是被观察者 observe是观察者
       wangAndroidApi.mgetProject()           //获取bean
           .subscribeOn(Schedulers.io())  //设置上面的操作是异步操作
           .observeOn(AndroidSchedulers.mainThread()) //给下面分配ui线程
           .map {
               "s"
           }
           .map {
               ProjectBean()
           }
           .subscribe(object : Observer<ProjectBean> {           //todo 创建观察者
               override fun onSubscribe(d: Disposable) {
                   Log.d(TAG, "onSubscribe: 第一步，订阅")
               }

               override fun onNext(t: ProjectBean) {
                   Log.d(TAG, "onNext: "+t.toString())
               }

               override fun onError(e: Throwable) {

               }

               override fun onComplete() {

               }
           })
    }
}