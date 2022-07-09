package com.example.mykotlin.Utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Looper.getMainLooper
import android.os.Looper.myLooper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mykotlin.R
fun dip2px(context:Context,f:Float):Int{
    return ((f*context.resources.displayMetrics.density+0.5).toInt())
}

var toast:Toast? = null
val mhandler:Handler = Handler(getMainLooper())
var view: View? = null
var textView:TextView? = null
fun show(context:Context,text:String){
    if(toast != null){
        toast!!.cancel()
    }
    //这一步前toast一定为null
    toast = Toast(context)
    //设置Toast的位置
    toast!!.setGravity(Gravity.CENTER_HORIZONTAL and Gravity.BOTTOM ,0, dip2px(context,150f))
    toast!!.duration = Toast.LENGTH_LONG
    if(view == null){
        view = LayoutInflater.from(context).inflate(R.layout.toast_textview,null)
    }
    textView = view!!.findViewById(R.id.tv_toast_text)
    textView!!.text = text
    toast!!.view = textView
    toast!!.show()
}

fun showToast(context:Context,text:String){
    if(Looper.getMainLooper() == myLooper()){
        show(context,text)
    }else{
        mhandler.post { show(context, text) }

    }
}

