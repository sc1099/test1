
package com.example.mykotlin.fragmenttest.sp

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.lang.Exception
import java.lang.NullPointerException

class sqlitedo {
    private  val TAG = "sqlitedo"
    fun createDb( helper: sqliteHelper){
        //获取db，如果没有则创建db
        helper.writableDatabase
    }

    fun updateVersion(helper: sqliteHelper){
        helper.writableDatabase
    }

    fun addData(helper: sqliteHelper,table:String,author:String,price:Float,wantSuccess:Boolean){
        val db = helper.writableDatabase
        db.beginTransaction()//开启事务
        try {
            val contentValues1 = ContentValues().apply {
                put("author","yzq")
                put("price",price)
            }
            val result1 = db.insert(table,null,contentValues1)
            Log.d(TAG, "addData: "+result1)
            if(!wantSuccess) throw NullPointerException()
            val contentValues = ContentValues().apply {
                put("author",author)
                put("price",price)
            }
            val result = db.insert(table,null,contentValues)
            Log.d(TAG, "addData: "+result)
            db.setTransactionSuccessful()
        }catch (e:Exception){

        }finally {
            db.endTransaction()
        }

    }


    fun update(helper: sqliteHelper,table:String,author:String,price:Float){
        val db = helper.writableDatabase
        val contentValues = ContentValues().apply {
            put("price",price)
        }
        db.update(table,contentValues,"author = ?", arrayOf("sc"))
    }

    fun delete(helper: sqliteHelper,table:String,author:String){
        val db = helper.writableDatabase
        db.delete(table,"author = ?", arrayOf(author))
    }

    fun select(helper: sqliteHelper,table:String){
        val db = helper.writableDatabase
        val cursor = db.query(table, arrayOf("price"),"author = ?", arrayOf("yzq"),null,null,null)
        if(cursor.moveToFirst()){
            do{
                val value = cursor.getFloat(cursor.getColumnIndex("price"))
                Log.d(TAG, "select: "+value)
            }while (cursor.moveToNext())
        }

    }

}