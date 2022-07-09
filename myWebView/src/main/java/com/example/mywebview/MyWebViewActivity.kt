package com.example.mywebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

val WEBVIEW_ACTIVITY_URL = "WEBVIEW_ACTIVITY_URL"
val WEBVIEW_Activity_CAN_NATIVE_REFRESH = "WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH"

class MyWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_web_view)
        var mUrl = intent?.extras?.getString(WEBVIEW_ACTIVITY_URL) ?: "https://www.baidu.com"
        var fm = findViewById<FrameLayout>(R.id.fm)

    }



}