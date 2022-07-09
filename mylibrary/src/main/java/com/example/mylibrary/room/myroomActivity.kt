package com.example.mylibrary.room

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityMyroomBinding
import com.example.mylibrary.mydatabinding.mydatabindingActivity
import com.example.mylibrary.room.database.UserDatabase
import com.example.mylibrary.room.entity.ScEntity
import com.example.mylibrary.room.entity.UserEntity

class myroomActivity : AppCompatActivity() {
    private  val TAG = "myroomActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object {
        fun startmyroomActivity(context: Context, key: String, value: String) {
            var intent = Intent(context, myroomActivity::class.java)
            intent.putExtra(key, value)
            context.startActivity(intent)
        }
    }

   lateinit var  viewbinding:ActivityMyroomBinding
    var i = 0
    var age = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMyroomBinding.inflate(layoutInflater,null,false)
        setContentView(viewbinding.root)
        val userDao = UserDatabase.getDatabase(this)!!.getUserDao()
        val scDao = UserDatabase.getDatabase(this)!!.getScDao()
        viewbinding.insert.setOnClickListener {
                userDao.InsertDao(UserEntity(name = "sc ", age = 18 ))
        }
        viewbinding.querry.setOnClickListener {
                var list:List<UserEntity> = userDao.QuerryAll()
                Log.d(TAG, "onCreate: "+list.toString())

        }

        //todo 更新数据库版本后实验
        viewbinding.scInsert.setOnClickListener {
            scDao.InsertDao(ScEntity(sc_name = "new sc", sc_age = 100))
        }
        viewbinding.scQuerry.setOnClickListener {
            scDao.QuerryAll().forEach {
                Log.d(TAG, "onCreate: "+it.toString())
            }
        }
        viewbinding.update.setOnClickListener {
            userDao.UpdateById(1,"......")
        }

    }
}