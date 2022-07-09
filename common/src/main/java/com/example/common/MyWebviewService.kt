package com.example.common


import android.content.Context

interface MyWebviewService {
    fun startWebViewActivity(
        context: Context,
        url: String,
        canNativeRefresh: Boolean = true
    )
    fun getWebViewFragment(
        url: String,
        canNativeRefresh: Boolean = true
    ): androidx.fragment.app.Fragment
}