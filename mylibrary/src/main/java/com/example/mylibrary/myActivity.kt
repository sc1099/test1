package com.example.mylibrary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mykotlin.jetpack.fragmentAndViewModle.Fragment1
import com.example.mykotlin.jetpack.fragmentAndViewModle.Fragment1ViewModel

import com.example.mylibrary.databinding.ActivityMyBinding
import com.example.mylibrary.fragmentAndViewModle.Fragment3
import com.example.mylibrary.mydatabinding.mydatabindingActivity
import com.example.mylibrary.mynavigation.navigationActivity
import com.example.mylibrary.mypaging3.Mypaging3Activity
import com.example.mylibrary.room.myroomActivity
import com.example.mylibrary.test.testActivity

class myActivity : AppCompatActivity() {
    private  val TAG = "myActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startmyActivity(context: Context, key:String, value:String){
            var intent = Intent(context, myActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }


    lateinit var viewBinding: ActivityMyBinding
    val viewModel:Fragment1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyBinding.inflate(layoutInflater,null,false)
        setContentView(viewBinding.root)
        viewModel.getdata().observe(this,object :Observer<Int>{
            override fun onChanged(t: Int?) {
                Log.d(TAG, "onChanged: $t")
            }
        })

        viewBinding.bt.setOnClickListener {
            viewModel.addone()
        }
        fragmentInit()

        viewBinding.bt0.setOnClickListener {
            mydatabindingActivity.startmydatabindingActivity(this,"sc","sc")
        }
        viewBinding.btTest.setOnClickListener {
            testActivity.starttestActivity(this,"sc","sc")
        }
        viewBinding.roomTest.setOnClickListener {
            myroomActivity.startmyroomActivity(this,"sc","Sc")
        }
        viewBinding.navigationTest.setOnClickListener {
            navigationActivity.startnavigationActivity(this,"sc","sc")
        }
        viewBinding.mypaging3.setOnClickListener {
            Mypaging3Activity.startMypaging3Activity(this,"sc","sc")
        }
    }


    fun fragmentInit(){
        val fragment1  = Fragment1.newInstance()
        val fragment2 = Fragment3.newInstance()

        supportFragmentManager.beginTransaction().add(R.id.fm1,fragment1,"f1").commit()
        supportFragmentManager.beginTransaction().add(R.id.fm2,fragment2,"f2").commit()

    }

}