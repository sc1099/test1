package com.example.mykotlin.mvp.modules.login

import android.content.Context
import android.util.Log
import com.example.mykotlin.mvp.modules.login.inter.loginModuleInter
import com.example.mykotlin.mvp.modules.login.inter.loginPresenterInter
import com.xiangxue.kotlinproject.api.WanAndroidAPI
import com.xiangxue.kotlinproject.config.Flag
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse
import com.xiangxue.kotlinproject.net.APIClient
import com.xiangxue.kotlinproject.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// Model 层  的 实现
class LoginModelImpl : loginModuleInter {

    // 取消请求
    override fun cancelRequest() {
        TODO("Not yet implemented")
    }

    override fun login(
        context: Context,
        username: String,
        password: String,
        onLoginListener: loginPresenterInter.OnLoginListener
    ) {

        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)

            // 全部都是RxJava知识了
            .loginAction(username, password)  // 起点  往下流  ”包装Bean“
            .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作，分配异步线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作，分配main线程
            .subscribe(object: APIResponse<LoginRegisterResponse>(context)
            {
                override fun success(data: LoginRegisterResponse ?) {
                    // 成功  data UI
                    Log.e(Flag.TAG, "success: $data")
                    onLoginListener.loginPresenterSuccess(data)
                }

                override fun failure(errorMsg: String?) {
                    // 失败 msg UI
                    Log.e(Flag.TAG, "failure: errorMsg:$errorMsg")
                    onLoginListener.loginPresenterFialure(errorMsg)
                }

            })

    }


}