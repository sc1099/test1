package com.example.mykotlin.Utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.util.Log

private const val TAG = "DialogUtil"
fun showdialog(activity: Activity, title:String, message:String,):AlertDialog.Builder{
    Log.d(TAG, "showdialog: ")
  return AlertDialog.Builder(activity).apply {
       setTitle(title)
       setMessage(message)
       setCancelable(false)  //可否用back建关闭对话框
       setPositiveButton("确定"){dialog,which->
           showToast(activity,"dialog确定")
           dialog.dismiss()
       }
   }
}