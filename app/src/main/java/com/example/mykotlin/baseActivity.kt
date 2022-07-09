package com.example.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class baseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        baseActivitycolltor.add(this)
    }

    override fun onDestroy() {
        baseActivitycolltor.remove(this)
        super.onDestroy()
    }
}