package com.example.mylibrary.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityTestBinding
import com.example.mylibrary.mydatabinding.mydatabindingActivity

class testActivity : AppCompatActivity() {
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun starttestActivity(context: Context, key:String, value:String){
            var intent = Intent(context, testActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityTestBinding>(this,R.layout.activity_test)

        databinding.rv.adapter = RvAdaptor()
        databinding.rv.layoutManager = LinearLayoutManager(this)
    }
}