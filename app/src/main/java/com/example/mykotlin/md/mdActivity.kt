package com.example.mykotlin.md

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.core.content.ContextCompat
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.fragmentTestActivity
import kotlinx.android.synthetic.main.activity_md.*

class mdActivity() : AppCompatActivity() {
    private  val TAG = "mdActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startmdActivity(context: Context, key:String, value:String){
            var intent = Intent(context, mdActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    var m = mutableMapOf<Int,Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        var list = m.map {
            "a"
        }
        list.run {  }
        list.apply {  }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_md)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = ContextCompat.getDrawable(this,R.drawable.back_icon)
        toolbar.setNavigationOnClickListener(){
                Log.d(TAG, "我按下了Navigation返回键 ")
                finish()
        }
    }


//    override fun onCreateOptionsMenu( menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbarmenu,menu)
//        return true
//    }

}