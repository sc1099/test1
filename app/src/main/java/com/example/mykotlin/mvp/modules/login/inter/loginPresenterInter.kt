package com.example.mykotlin.mvp.modules.login.inter

import android.content.Context
import com.xiangxue.kotlinproject.base.IBasePresenter
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

interface loginPresenterInter :IBasePresenter{
    // 登录
    fun loginPresenterAction(context: Context, username: String, password: String)

    // 监听回调
    interface OnLoginListener {

        fun loginPresenterSuccess(loginBean: LoginRegisterResponse?)

        fun loginPresenterFialure(erroeMsg: String  ?)

    }
}