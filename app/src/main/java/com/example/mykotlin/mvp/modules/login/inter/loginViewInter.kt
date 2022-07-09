package com.example.mykotlin.mvp.modules.login.inter

import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

interface loginViewInter {

    // 把结果显示到  Activity / Fragment

    fun loginViewSuccess(loginBean: LoginRegisterResponse?)

    fun loginViewFialure(erroeMsg: String  ?)


}