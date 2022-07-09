package com.example.mykotlin.fragmenttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlin.Mylistview.listActivity
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.frags.basefrag
import com.example.mykotlin.fragmenttest.frags.frag1
import com.example.mykotlin.fragmenttest.frags.frag2
import com.example.mykotlin.fragmenttest.vp2.Vp2FragActivity

class fragmentTestActivity : AppCompatActivity() {
    lateinit var  frag_1:frag1
    lateinit var frag_2:frag2
    lateinit var  fragmentManager: FragmentManager
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startfragmentTestActivity(context: Context, key:String, value:String){
            var intent = Intent(context, fragmentTestActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
   lateinit var t1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        t1 = findViewById(R.id.t1)
        init_frag()
        fragmentManager = supportFragmentManager
        //每个事物只能提交一次
        fragmentManager.beginTransaction().add(R.id.fm,frag_1,"frag1").addToBackStack(null)
            .commit()
        var bt:Button = findViewById(R.id.bt)
        bt.setOnClickListener {
            var f = fragmentManager.findFragmentByTag("frag1")
            if (f != null) {
                fragmentManager.beginTransaction().hide(f).add(R.id.fm,frag_2,"frag2").addToBackStack(null).commit()
            }
        }
        var t2 = findViewById<TextView>(R.id.t2)
        t2.setOnClickListener {
            Vp2FragActivity.startVp2FragActivity(this,"sc","value")
        }
    }

    fun init_frag(){
        frag_1 = frag1("frag1")
        var bundle:Bundle = Bundle()
        bundle.putString("sc","value")
        frag_1.arguments = bundle
        frag_1.listener = object :(basefrag.myCallack){
            override fun tback(string: String) {
                t1.text = string
            }
        }
        frag_1.fragToActivity {
            t1.text = it
        }


        frag_2 = frag2("frag2")
        var bundle2:Bundle = Bundle()
        bundle2.putString("sc","value")
        frag_2.arguments = bundle2
    }
}