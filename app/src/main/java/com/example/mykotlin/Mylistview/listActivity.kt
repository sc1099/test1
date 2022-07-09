package com.example.mykotlin.Mylistview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.R
private const val TAG = "listActivity"
class listActivity : AppCompatActivity() {

    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startlistActivity(context: Context, key:String, value:String){
            var intent = Intent(context, listActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var rv:RecyclerView = findViewById(R.id.rcv)
        var list = getList()
        Log.d(TAG, "onCreate: "+list.size)
        var adapter = adaptor(list,this)
        adapter.listener = object : rvclicklistener {
            override fun myclick(view: View, pos: Int) {
                view.setOnClickListener {
                    Log.d(TAG, "myclick: $pos")
                }
            }
        }
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        val helpcallback = myItemTouchHelpCallback(list,adapter)
        val help = ItemTouchHelper(helpcallback)
        help.attachToRecyclerView(rv)
    }


}