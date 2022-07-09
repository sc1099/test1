package com.example.mylibrary.mydatabinding

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityMyBinding
import com.example.mylibrary.databinding.ActivityMydatabindingBinding

class mydatabindingActivity : AppCompatActivity() {
    private val TAG = "mydatabindingActivity"
    var databinding_name = "sc"

    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startmydatabindingActivity(context: Context, key:String, value:String){
            var intent = Intent(context, mydatabindingActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }

        //todo databinding的自定义属性
        @BindingAdapter(value = ["test_image_url"], requireAll = false)
        @JvmStatic
        fun image_test(imageView: ImageView,_url:String){
            val url = _url
//            val placeholder = _placeholder
//            Glide.with(imageView.context).load(url).placeholder(placeholder).into(imageView)
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityMydatabindingBinding>(
            this,
            R.layout.activity_mydatabinding
        )
        //todo people类首字母大写，做双向绑定处理
        databinding.user =People("scscsc",18,"https://img2.baidu.com/it/u=3296226020,4122579797&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281")
        databinding.lifecycleOwner = this
        databinding.bt2.setOnClickListener {
            databinding.user = People("change",18)
        }

        databinding.bt3.setOnClickListener {
            databinding.user = People("change",18,"https://img1.baidu.com/it/u=2241334221,3271234001&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500")
        }

    }





}