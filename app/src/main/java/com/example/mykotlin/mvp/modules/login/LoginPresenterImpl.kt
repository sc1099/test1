package com.example.mykotlin.mvp.modules.login

import android.content.Context
import com.example.mykotlin.mvp.modules.login.inter.loginModuleInter
import com.example.mykotlin.mvp.modules.login.inter.loginPresenterInter
import com.example.mykotlin.mvp.modules.login.inter.loginViewInter
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

class LoginPresenterImpl(loginView: loginViewInter?) : loginPresenterInter,loginPresenterInter.OnLoginListener {

    //获取view，讲presenter得到的数据传过去
    var logview = loginView


    //获取module   讲函数结果通过回调函数传递给presenter
    var loginModuleImpl: LoginModelImpl = LoginModelImpl()

    override fun loginPresenterAction(context: Context, username: String, password: String) {
        // TODO
        // 可以做很多的事情,用户名不可为空之类的
        // 可以省略很多代码
        // 很多的校验
        // ....
        loginModuleImpl.login(context,username,password,this)
    }

    override fun unAttachView() {
        loginModuleImpl.cancelRequest()
        logview = null
    }

    override fun loginPresenterSuccess(loginBean: LoginRegisterResponse?) {
        logview?.loginViewSuccess(loginBean)
    }

    override fun loginPresenterFialure(erroeMsg: String?) {
        logview?.loginViewFialure(erroeMsg)
    }


}