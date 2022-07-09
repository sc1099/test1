package com.example.mykotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykotlin.Mylistview.listActivity

class NPatchActivity2 : AppCompatActivity() {
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startNPatchActivity2(context: Context, key:String, value:String){
            var intent = Intent(context, listActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n_patch2)

    }
} 

//todo 写在class外面的是静态
fun sc111(){

}