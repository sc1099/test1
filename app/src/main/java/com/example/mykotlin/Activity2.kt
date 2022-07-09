package com.example.mykotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mykotlin.Mylistview.listActivity

class Activity2 : baseActivity() {
    private  val TAG = "Activity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val intent2 = Intent();
        intent2.putExtra("Activity2","sc_Activity2")
        setResult(RESULT_OK,intent2)
        if(savedInstanceState != null){

        }
        val bt2: Button = findViewById(R.id.bt)
        bt2.setOnClickListener {
            //显示跳转
            var intent = Intent(this,MainActivity::class.java)
            intent.putExtra("sc","sc_value")
            startActivityForResult(intent,1)
            //隐式跳转
//            startActivity(Intent("sc"))
        }


    }
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startActivity2(context: Context,key:String,value:String){
            var intent = Intent(context,Activity2::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    //这个方法，在activity被回收前一定会被调用
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


    override fun onNewIntent(intent: Intent?) {
        Log.d(TAG, "onNewIntent: "+intent?.getStringExtra("sc"))
//        setIntent(intent)
        super.onNewIntent(intent)
    }
    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }
    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: "+intent?.getStringExtra("sc"))
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

}