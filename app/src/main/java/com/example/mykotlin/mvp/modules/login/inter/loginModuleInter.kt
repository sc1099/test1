package com.example.mykotlin.mvp.modules.login.inter

import android.content.Context

interface loginModuleInter {
    // 取消请求 动作
    fun cancelRequest()

    // 登录
    fun login(
        context: Context,
        username: String,
        password: String,

        // 接口回调，把data 结果，给P层
        onLoginListener: loginPresenterInter.OnLoginListener
    )
}