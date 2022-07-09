package com.example.mykotlin.myService

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.mykotlin.R

class MyService : IntentService("aa") {
    private  val TAG = "MyService"
    private val mBinder = downloadBinder()
    lateinit var notificationManager:NotificationManager
    var mdownloadtask: mDownloadtask? = null

    inner class downloadBinder():Binder(){
        fun startdownload(url:String){
            Log.d("MyService", "startdownload: ")
            if(mdownloadtask == null){
                mdownloadtask = mDownloadtask(listener)
                mdownloadtask!!.execute(url)
                startForeground(1,CreateNotification("正在下载...",0))

            }
        }

        fun getProgress():Int{
            Log.d("MyService", "getProgress: ")
            return 0
        }

    }
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
    }

    //创建service的时候调用
    override fun onCreate() {
        Log.d(TAG, "onCreate: ")
        //创建前台service
        super.onCreate()
    }

    private fun CreateNotification(title:String,progress:Int) :Notification{
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //8.0以上才有通知渠道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1 = NotificationChannel("my_service","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel1)
        }
        var intent = Intent(this,myServiceActivity::class.java)
        var p = PendingIntent.getActivity(this,0,intent,0)
        var notificationBuilder = NotificationCompat.Builder(this,"my_service")
        notificationBuilder.setContentTitle("$title")
        notificationBuilder.setSubText("这是SubText")
        notificationBuilder.setAutoCancel(false)    //点击后不取消
        notificationBuilder.setContentIntent(p)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
        if(progress>0){
            notificationBuilder.setContentText("$progress %")
            notificationBuilder.setProgress(100,progress,false)
        }

        val notification=notificationBuilder.build()
        return notification
    }

    //启动service的时候调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }
    
    //结束service的时候调用
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    var listener = object :mDownloadListener{
        override fun onprogress(progress: Int) {
            notificationManager.notify(1,CreateNotification("正在下载...",progress))
        }

        override fun onsuccess() {
            mdownloadtask = null
            notificationManager.cancel(1)
        }

    }

}