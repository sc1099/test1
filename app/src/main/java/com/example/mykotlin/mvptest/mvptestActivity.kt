package com.example.mykotlin.mvptest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mykotlin.R
import com.example.mykotlin.RxJava.rxJavaActivity
import com.example.mykotlin.mvptest.apis.wanAndroidapis
import com.example.mykotlin.mvptest.po.data
import com.example.mykotlin.mvptest.po.loginDataClass
import com.example.mykotlin.mvptest.utils.apiClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class mvptestActivity : AppCompatActivity() ,View.OnClickListener{
    private  val TAG = "mvptestActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startmvptestActivity(context: Context, key:String, value:String){
            var intent = Intent(context, mvptestActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvptest)

        var api= apiClient().INSTANCE().getRetrofitClient(wanAndroidapis::class.java)
        api.login("sc","123")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<loginDataClass<data>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: loginDataClass<data>) {
                    Log.d(TAG, "onNext: "+t.errorMsg+"  "+t.errorCode)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })




    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}


