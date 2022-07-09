package com.example.mylibrary.mynavigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityNavigationBinding
import com.example.mylibrary.myActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class navigationActivity : AppCompatActivity() {
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startnavigationActivity(context: Context, key:String, value:String){
            var intent = Intent(context, navigationActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }

    lateinit var databinding:ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityNavigationBinding>(
            this,
            R.layout.activity_navigation
        )
        // 总控制器：设置导航
        val navView: BottomNavigationView = findViewById(R.id.bmnav)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHost!!.findNavController()
//        val findNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController);

    }
}