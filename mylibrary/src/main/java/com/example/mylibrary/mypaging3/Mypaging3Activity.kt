package com.example.mylibrary.mypaging3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.mynavigation.navigationActivity
import com.example.mylibrary.mypaging3.adaptor.FooterAdapter
import com.example.mylibrary.mypaging3.adaptor.RepoAdapter
import com.example.mylibrary.mypaging3.myviewmodle.MyViewModle
import kotlinx.coroutines.launch

class Mypaging3Activity : AppCompatActivity() {
    private  val TAG = "Mypaging3Activity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startMypaging3Activity(context: Context, key:String, value:String){
            var intent = Intent(context, Mypaging3Activity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
    private val viewModel by lazy { ViewModelProvider(this).get(MyViewModle::class.java) }

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypaging3)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter.withLoadStateFooter(FooterAdapter{repoAdapter.retry()})

        lifecycleScope.launch{
            viewModel.getPagingData().collect{
                repoAdapter.submitData(it)
            }
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                    Log.d(TAG, "NotLoading: ")
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    Log.d(TAG, "Loading: ")
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }



    }
}