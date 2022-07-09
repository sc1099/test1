@file:JvmName("sss")
package com.example.mykotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.common.MyWebviewService


import com.example.mykotlin.CamAndPhoto.CActivity
import com.example.mykotlin.MyContentProvider.PActivity
import com.example.mykotlin.Mylistview.listActivity
import com.example.mykotlin.RxJava.rxJavaActivity
import com.example.mykotlin.Utils.showToast
import com.example.mykotlin.fragmenttest.fragmentTestActivity
import com.example.mykotlin.fragmenttest.sp.spActivity
import com.example.mykotlin.jetpack.JetpackActivity
import com.example.mykotlin.md.mdActivity
import com.example.mykotlin.mvptest.mvptestActivity
import com.example.mykotlin.myService.myServiceActivity
import com.xiangxue.common.utils.MyWebviewServiceLoader
import com.xiangxue.kotlinproject.modules.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

import java.util.*

class MainActivity : baseActivity() {

    val TAG = "MainActivity"
    var sc = 0;
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        //test
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = Uri.parse("https://www.baidu.com")
//        startActivity(intent)

        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var s = "a"
        var bt :Button= findViewById(R.id.bt)
        bt.setOnClickListener {
            Log.d(TAG, "onCreate: "+it.id)
            Toast.makeText(this,"这是kt第一次尝试",Toast.LENGTH_LONG).show();
            var intent = Intent(this,Activity2::class.java)
            intent.putExtra("sc", "sc_value2")
            startActivityForResult(intent,1)
        }
        val bt2:Button = findViewById(R.id.bt2)
        bt2.setOnClickListener {
            //显示跳转
            var intent = Intent(this,Activity2::class.java)
            intent.putExtra("sc", "sc_value1")
            startActivityForResult(intent,1)
            //隐式跳转
//            startActivity(Intent("sc"))
        }
        var bt3:Button = findViewById(R.id.bt3);
        bt3.setOnClickListener {
            listActivity.startlistActivity(this,"sc","sc_list")
        }
        var bt4:Button = findViewById(R.id.bt4);
        bt4.setOnClickListener {
            NPatchActivity2.startNPatchActivity2(this,"sc","sc_list")
        }
        var bt5:Button = findViewById(R.id.bt5);
        bt5.setOnClickListener {
            fragmentTestActivity.startfragmentTestActivity(this,"sc","sc_list")
        }
        var im = findViewById<ImageView>(R.id.im)
        Glide.with(this).load("https://lmg.jj20.com/up/allimg/tp05/1Z9291QR05927-0-lp.jpg").into(im)
        var bt6:Button = findViewById(R.id.bt6);
        bt6.setOnClickListener {
            spActivity.startspActivity(this,"sc","sc_list")
        }

        var bt7:Button = findViewById(R.id.bt7);
        bt7.setOnClickListener {
            PActivity.startPActivity(this,"sc","sc_list")
        }
        var bt8:Button = findViewById(R.id.bt8);
        bt8.setOnClickListener {
            CActivity.startCActivity(this,"sc","sc_list")
        }
        var md:Button = findViewById(R.id.md);
        md.setOnClickListener {
            mdActivity.startmdActivity(this,"sc","sc_list")
        }

        service.setOnClickListener {
            myServiceActivity.startmyServiceActivity(this,"sc","sc_list")
        }
        RxJava.setOnClickListener {
            rxJavaActivity.startrxJavaActivity(this,"sc","sc_list")
        }
        mvptest.setOnClickListener {
            mvptestActivity.startmvptestActivity(this,"sc","sc_list")
        }

        myWebview.setOnClickListener {
//            var webviewService = MyWebviewServiceLoader.load(MyWebviewService::class.java)
//            webviewService?.startWebViewActivity(this,"https://www.baidu.com")
        }
        login.setOnClickListener {
            LoginActivity.startLoginActivity(this,"sc","sc_list")
        }
        jet.setOnClickListener {
            JetpackActivity.startJetpackActivity(this,"sc","sc_list")
        }
//       val a =  object :View.OnClickListener{
//           override fun onClick(v: View?) {
//               TODO("Not yet implemented")
//           }
//
//       }


        //todo 测试协程
        Log.d(TAG, "onCreate: 测试协程0     "+Thread.currentThread().name)
        var cor =  MainScope()

        val job = Job()
      
        val scope  = CoroutineScope(job)
        scope.launch(Dispatchers.IO){
            Log.d(TAG, "onCreate: 测试协程1     "+Thread.currentThread().name)
            msleep()
            Log.d(TAG, "onCreate: 测试协程2    ")
        }
        Thread.sleep(1000)
        Log.d(TAG, "onCreate: 测试协程3         "+Thread.currentThread().name)
//        job.cancel()   //回收所有协程
    }

//todo  suspend不是实现挂起的作用。只是用来提醒，真正实现挂起的是suspend修饰的函数中的代码。
suspend fun msleep(){
        withContext(Dispatchers.IO){   //withContext会将外部协程挂起
//            delay(10000)
            Log.d(TAG, "msleep: 我在协程中sleep 3秒          "+Thread.currentThread().name)
        }


        Log.d(TAG, "msleep: 我在协程中sleep 3秒                  "+Thread.currentThread().name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            1 -> {
                if (resultCode == RESULT_OK) {
                    val s = data?.getStringExtra("Activity2")
                    Log.d(TAG, "onActivityResult: "+s)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu1,menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_add-> showToast(this,"add")
            R.id.item_mine->showToast(this,"mine")
        }
        return true
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        if (ev != null) {
//            when(ev.action){
//                MotionEvent.ACTION_DOWN->showdialog(this,"dialog测试","你要不要点确定").show()
//            }
//        }

        return super.dispatchTouchEvent(ev)
    }


    override fun onNewIntent(intent: Intent?) {
        Log.d(TAG, "onNewIntent: ")
        super.onNewIntent(intent)
    }
    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }
    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}


