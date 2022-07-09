package com.example.mykotlin.fragmenttest.sp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log

class sqliteHelper(val context: Context,val name:String,val version: Int):SQLiteOpenHelper(context,name,null,version) {
    private  val TAG = "sqliteHelper"
    private val createbook = "create table book(id integer primary key autoincrement ,author text,price real)"
    private val createsc = "create table sc(id integer primary key autoincrement ,author text,price real)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createbook)
        Log.d(TAG, "onCreate: ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(newVersion == 2) db?.execSQL(createsc)
        Log.d(TAG, "onUpgrade: ")
    }
}