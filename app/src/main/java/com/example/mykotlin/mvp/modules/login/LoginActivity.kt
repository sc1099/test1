package com.xiangxue.kotlinproject.modules.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlin.R
import com.example.mykotlin.md.mdActivity
import com.example.mykotlin.mvp.modules.login.LoginPresenterImpl
import com.example.mykotlin.mvp.modules.login.inter.loginPresenterInter
import com.example.mykotlin.mvp.modules.login.inter.loginViewInter
import com.xiangxue.kotlinproject.base.mvpBaseActivity
import com.xiangxue.kotlinproject.config.Flag
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

import kotlinx.android.synthetic.main.activity_user_login.*

// 违背单一的原则  C  V  M

// MVVM + JetPack

// 登录界面


// TODO  =========================================   View层  的 实现

class LoginActivity :mvpBaseActivity<loginPresenterInter>(), loginViewInter {
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startLoginActivity(context: Context, key:String, value:String){
            var intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
    private  val TAG = "LoginActivity"
     lateinit var p: loginPresenterInter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        user_login_bt.setOnClickListener(onCLickLister)

         p = LoginPresenterImpl(this)


    }

    private val onCLickLister = View.OnClickListener { view ->
        // id = 内部根据= 知道你是 要 setId，  否则就是getId
        when(view.id) {
            // 登录
            R.id.user_login_bt -> {

                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(Flag.TAG, "userName:$userNameStr,  userPwd:$userPwdStr")
                presenter.loginPresenterAction(this,userNameStr,userPwdStr)
            }

            // else->
        }
    }


    override fun loginViewSuccess(loginBean: LoginRegisterResponse?) {
        Log.d(TAG, "loginViewSuccess: "+loginBean.toString())
    }

    override fun loginViewFialure(erroeMsg: String?) {
        Log.d(TAG, "loginViewFialure: $erroeMsg")
    }

    //设置是哪一个presenter
    override fun createP(): loginPresenterInter =LoginPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }


}
