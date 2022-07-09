package com.example.mykotlin.fragmenttest.sp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.vp2.Vp2FragActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset
import kotlin.random.Random

class spActivity : AppCompatActivity() {
    var spname = "mysp"
    private  val TAG = "spActivity"
    lateinit var sp:SharedPreferences
    var version = 4
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startspActivity(context: Context, key:String, value:String){
            var intent = Intent(context, spActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        val list = mutableListOf<String>()
        //返回true会加入新集合
        list.filter {
            true
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp)
        test_dir()
        fileInOut("文件读取测试","文件读取测试,我叫scscsc....")
        setsp(spname,"sp_data1","我是sc")
//        version = sp.getInt("version",1)
//        setsp(spname,"version",version)
        sp.getString("sp_data1","null")?.let { Log.d(TAG, it) }

        //创建一个sqlitehelper
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener {
//            version = sp.getInt("version",0)
            var helper = sqliteHelper(this,"BookStore.db",4)
            sqlitedo().createDb(helper)
        }
        val bt2 = findViewById<Button>(R.id.bt2)
        bt2.setOnClickListener {
//            version = sp.getInt("version",0)
//            version += 1
            var helper2 = sqliteHelper(this,"BookStore.db",5)
//            setsp(spname,"version",version)
            sqlitedo().updateVersion(helper2)
        }

        val bt3 = findViewById<Button>(R.id.bt3)
        bt3.setOnClickListener {
//            version = sp.getInt("version",0)
            var helper = sqliteHelper(this,"BookStore.db",4)
            var price = Random.nextFloat()*10
            sqlitedo().addData(helper,"book","sc",price,false)
        }
        val bt4 = findViewById<Button>(R.id.bt4)
        bt4.setOnClickListener {
            var helper = sqliteHelper(this,"BookStore.db",4)
            sqlitedo().update(helper,"book","sc",100f)
        }

        val bt5 = findViewById<Button>(R.id.bt5)
        bt5.setOnClickListener {
            var helper = sqliteHelper(this,"BookStore.db",4)
            sqlitedo().delete(helper,"book","sc")
        }

        val bt6 = findViewById<Button>(R.id.bt6)
        bt6.setOnClickListener {
            var helper = sqliteHelper(this,"BookStore.db",4)
            sqlitedo().select(helper,"book")
        }

    }

    //TODO 获取泛型class 需要inline和reified
     fun< T> setsp(spname:String,key:String,value: T){
        sp = getSharedPreferences(spname, MODE_PRIVATE)
        val editor = sp.edit()
        when(value){
            is String->editor.putString(key,value)
            is Int ->editor.putInt(key,value)
        }
        //提交修改
        editor.apply()
    }



    fun fileInOut(filename:String,name:String){
        var dir = this.filesDir.toString()+"/$filename"
        var file = File(dir)
        if(!file.exists()){
            file.createNewFile()
        }
        var out = FileOutputStream(file,true)
        out.use {
            it.write(name.toByteArray())
        }

        var get = FileInputStream(file)
        get.use {
            var bytes = file.readBytes()
                Log.d(TAG, "我读出来的: "+bytes.toString(Charset.defaultCharset()))

        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun test_dir(){
        //内部存储
        Log.d(TAG, "filesDir: "+this.filesDir)
        Log.d(TAG, "cacheDir: "+this.cacheDir)

        //外部存储
        Log.d(TAG, "getExternalFilesDir: "+this.getExternalFilesDir(""))
        Log.d(TAG, "externalCacheDir: "+this.externalCacheDir)

        Log.d(TAG, "getExternalStorageState: "+Environment.getExternalStorageState())
        Log.d(TAG, "test_dir: "+Environment.getStorageDirectory())
        Log.d(TAG, "test_dir: "+this.getExternalFilesDir(Environment.DIRECTORY_DCIM))
    }
}