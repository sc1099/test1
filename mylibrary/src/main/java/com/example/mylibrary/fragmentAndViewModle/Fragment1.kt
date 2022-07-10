package com.example.mykotlin.jetpack.fragmentAndViewModle

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylibrary.databinding.Fragment1Binding
import androidx.activity.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel


class Fragment1 : Fragment() {
    private  val TAG = "Fragment1"
    // TODO: 简单方式获取viewmodle
//    private val viewModel2:Fragment1ViewModel by activityViewModels()

    companion object {
        fun newInstance() = Fragment1()
    }



    private lateinit var viewModel: Fragment1ViewModel
    lateinit var viewBinding:Fragment1Binding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = Fragment1Binding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(Fragment1ViewModel::class.java) }!!
        viewModel.run { getdata().observe(activity!!,object :Observer<Int>{
            override fun onChanged(t: Int?) {
                viewBinding.bt.text = t.toString()
            }
        }) }
        viewBinding.bt  .setOnClickListener {
            viewModel.addone()
        }

        viewBinding.xc1.setOnClickListener {


// TODO: 推荐
            lifecycleScope.launch {
                Log.d(TAG, "ClickListener: "+Thread.currentThread().name)
                test2()
                // TODO:  会一直跑，无法停止，所以不推荐使用 GlobalScope，防止出现activity没了，协程继续的情况
//                while (true){
//                    delay(1000)
//                    Log.d(TAG, "ClickListener: "+Thread.currentThread().name)
//                }
            }
            viewBinding.xc1.text = "协程实验1结束"
        }
    }


    suspend fun test1(){
        //todo lifecycleScope不会挂起其他协程  coroutineScope会挂起其他协程
        coroutineScope  {
            delay(2000)
            Log.d(TAG, "coroutineScope: "+Thread.currentThread().name)
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            Log.d(TAG, "GlobalScope1: "+Thread.currentThread().name)
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            Log.d(TAG, "GlobalScope2: "+Thread.currentThread().name)
        }
    }

    fun test2(){
        val channel = Channel<String>()
        lifecycleScope.launch {
            repeat(10){channel.send("这是第1个lifecycleScope"+Thread.currentThread().name)}
            channel.close()
        }
        lifecycleScope.launch {
            repeat(10){channel.trySend("这是第2个lifecycleScope"+Thread.currentThread().name)}
            channel.close()
        }
        lifecycleScope.launch {
            for (y in channel){
                Log.d(TAG, channel.receive())
            }
        }
        Log.d(TAG, "test2: 结束"+Thread.currentThread().name)
        Log.d(TAG, "version1.............."+Thread.currentThread().name)
        Log.d(TAG, "version1.............."+Thread.currentThread().name)
    }

}