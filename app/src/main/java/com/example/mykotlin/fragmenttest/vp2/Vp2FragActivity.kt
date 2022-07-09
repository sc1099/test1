package com.example.mykotlin.fragmenttest.vp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.mykotlin.Activity2
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.frags.basefrag

class Vp2FragActivity : AppCompatActivity() {
    private  val TAG = "Vp2FragActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startVp2FragActivity(context: Context, key:String, value:String){
            var intent = Intent(context, Vp2FragActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    var list = mutableListOf<basefrag>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp2_frag)
        var bt = findViewById<Button>(R.id.bt)
        var vp2 = findViewById<ViewPager2>(R.id.vp2)
        fragListInit()
        var adaptor = vp2FragAdaptor(this,list)
        vp2.adapter = adaptor
        //关闭预加载
//        vp2.offscreenPageLimit = 0
        bt.setOnClickListener {
            adaptor.update(vp2.currentItem,5)

        }
    }

    fun fragListInit(){
        for(i in 0..4){
            var b = basefrag("....f$i")
            list.add(b)
        }
        Log.d(TAG, "fragListInit: "+list.size)
    }
}