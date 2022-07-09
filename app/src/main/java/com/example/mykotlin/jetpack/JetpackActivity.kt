package com.example.mykotlin.jetpack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlin.R
import com.example.mykotlin.md.mdActivity
import com.example.mylibrary.myActivity
import kotlinx.android.synthetic.main.activity_jetpack.*

class JetpackActivity : AppCompatActivity() {
    private  val TAG = "JetpackActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startJetpackActivity(context: Context, key:String, value:String){
            var intent = Intent(context, JetpackActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    private val myViewModle by lazy {
        ViewModelProvider(this).get(MyViewModle::class.java)
    }

    var i = 0;
    val handler = Handler(Looper.myLooper()!!)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        // TODO: 测试lifecycle
        lifecycle.addObserver(MyLifeCycle())

        // TODO: 测试livedata
       var liveData =  MutableLiveData<String>()
        val liveDataNew=Transformations.map(liveData,object :Function<String,String>{
            override fun apply(input: String?): String {
                return input+"用到了map"
            }
        })
        liveDataNew.observe(this,object : Observer<String>{
            override fun onChanged(t: String?) {
                tv.text = t
            }
        })
       bt.setOnClickListener {
            liveData.value = "这是我第 $i 次点击button"
            i++
       }
        bt2.setOnClickListener {
            handler.postDelayed({
                liveData.value = "延时点击，看在stop生命周期下livedata会不会停止"
            },3000)
        }
        bt3.setOnClickListener {
            Thread({
//                //下面不行，要用post传递
//                liveData.value ="子线程点击"
                liveData.postValue("子线程点击")
            }).start()
        }


        //todo ViewModle用于存储ui相关数据
        //todo 旋转屏幕数据会丢失，可以使用ViewModle
        myViewModle.getdata().observe(this,object :Observer<Int>{
            override fun onChanged(t: Int?) {
                tv2.text = t.toString()
            }
        })
        bt4.setOnClickListener {
           myViewModle.addone()
        }





        bt5.setOnClickListener {
            myActivity.startmyActivity(this,"Sc","sc")
        }


    }





}