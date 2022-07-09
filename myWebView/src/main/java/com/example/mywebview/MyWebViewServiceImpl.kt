package com.example.mywebview

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.common.MyWebviewService
import com.google.auto.service.AutoService


@AutoService(MyWebviewService::class)
class MyWebViewServiceImpl : MyWebviewService {


    override fun startWebViewActivity(context: Context, url: String, canNativeRefresh: Boolean) {
        val intent = Intent(context, MyWebViewActivity::class.java)
        intent.putExtra(WEBVIEW_ACTIVITY_URL, url)
        intent.putExtra(WEBVIEW_Activity_CAN_NATIVE_REFRESH, canNativeRefresh)
        context.startActivity(intent)
    }

    override fun getWebViewFragment(url: String, canNativeRefresh: Boolean): Fragment {
        return XiangxueWebViewFragment.newInstance(url, canNativeRefresh)
    }


}