package com.example.mykotlin.CamAndPhoto

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.mykotlin.Activity2
import com.example.mykotlin.MainActivity
import com.example.mykotlin.R
import com.example.mykotlin.Utils.showdialog
import com.example.mykotlin.fragmenttest.sp.spActivity
import java.io.File
import java.io.FileDescriptor
import java.util.jar.Manifest

class CActivity : AppCompatActivity() {
    private  val TAG = "CActivity"
    var channelIdFirst = "channel_sc"
    var channelIdSecond = "channel_yzq"
    var channelNameFirst = "沈超相关"
    var channelNameSecond = "杨竹青相关"
    var permission = mutableListOf<String>(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_CALENDAR,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    var denyMessage = mutableListOf<String>("需要打开相机权限,现在去设置","需要打开相册权限,现在去设置","需要打开读取sd卡权限,现在去设置")
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startCActivity(context: Context, key:String, value:String){
            var intent = Intent(context, CActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
    lateinit var notificationManager: NotificationManager
    var id = 0
    lateinit var imageUri:Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var bt : Button = findViewById(R.id.bt)
        bt.setOnClickListener {
            //判断需要申请哪些权限
            mycheckpermission()
            if(permission.size ==0 || Build.VERSION.SDK_INT<=Build. VERSION_CODES.M){
                doCamera()
            }else{
                ActivityCompat.requestPermissions(this, permission.toTypedArray(),1)
            }
        }
        val bt2: Button = findViewById(R.id.bt2)
        bt2.setOnClickListener {
            getFromAlbum()
        }
        val bt3: Button = findViewById(R.id.bt3)
        bt3.setOnClickListener {
            normal()
        }
        val bt4: Button = findViewById(R.id.bt4)
        bt4.setOnClickListener {
            myimportant()
        }

        //8.0以上才有通知渠道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1 = NotificationChannel(channelIdFirst,channelNameFirst,NotificationManager.IMPORTANCE_DEFAULT)
            val channel2 = NotificationChannel(channelIdSecond,channelNameSecond,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }

    }

    private fun doCamera() {
        val file = File(externalCacheDir,"camera_pic.png")
        if(file.exists()){
            file.delete()
        }
        file.createNewFile()

        //获取这张图片的uri
        imageUri = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
           FileProvider.getUriForFile(this,"sc.camrea.fileprovider",file)
        }else{
            Uri.fromFile(file)
        }
        Log.d(TAG, "doCamera:...... imageUri = "+imageUri)
        //启动相机程序
        var intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(intent,1)
    }

    fun getFromAlbum(){
        //启动相册
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent,2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if(resultCode == Activity.RESULT_OK){
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    var im = findViewById<ImageView>(R.id.im)
                    im.setImageBitmap(bitmap)
                }
            }
            2 -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data.let {uri->
                        Log.d(TAG, "相册照片的uri：    $uri")
                        val fileDescriptor : FileDescriptor? = uri?.let { contentResolver.openFileDescriptor(it,"r")?.fileDescriptor }
                        val bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                        var im = findViewById<ImageView>(R.id.im)
                        im.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun mycheckpermission() {
        var len = permission.size
        var i = 0
        //todo remove之后，list中元素会自动往前补,所以需要进行处理
        while (i<len){
            if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,permission[i])){
                denyMessage.removeAt(i)
                permission.removeAt(i)
                len--
            }
            i++
        }
    }

    fun goToAppSetting(){
        var intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        var uri = Uri.fromParts("package",packageName,null)
        intent.data = uri
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "加入onRequestPermissionsResult...... ")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                var shouldDoCamera = true
                var len = permission.size
                for (i in 0 until len) {
                    var shouldshow = shouldShowRequestPermissionRationale(permission[i])
                    Log.d(TAG, permission[i]+"   这个权限显示：   "+shouldshow)
                    if (grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED != grantResults[i]) {
                        shouldDoCamera = false
                        if(shouldshow){
                            return
                        } else{
                            myshowdialog(i)
                        }
                    }
                }
                if (shouldDoCamera) {
                    doCamera()
                }
            }
        }
    }

    private fun myshowdialog(i:Int) {
        AlertDialog.Builder(this)
            .setTitle("权限设置")
            .setMessage(denyMessage[i])
            .setCancelable(false)
            .setNegativeButton("取消"){ dialog, _ ->
                dialog.dismiss()

            }
            .setPositiveButton("设置"){dialog,_->
                goToAppSetting()
                dialog.dismiss()
            }
            .show()
    }


    fun normal(){
        val intent = Intent(this,MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        var notification =  NotificationCompat.Builder(this,channelIdFirst)
            .setContentTitle("这是title")
            .setContentText("这是ContentText")
            .setSubText("这是SubText")
            .setAutoCancel(true)
            .setContentIntent(pi)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(id++,notification)
    }

    fun myimportant(){
        val intent = Intent(this,MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        var notification =  NotificationCompat.Builder(this,channelIdSecond)
            .setContentTitle("这是title")
            .setContentText("这是ContentText")
            .setSubText("这是SubText")
            .setAutoCancel(true)
            .setContentIntent(pi)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(id++,notification)
    }

}