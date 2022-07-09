package com.example.mykotlin.MyContentProvider

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.sp.spActivity
import java.util.jar.Manifest

class PActivity : AppCompatActivity() {
    private  val TAG = "PActivity"
    //TODO 类似于java的静态方法,告诉别人怎么跳转到我这个Activity
    companion object{
        fun startPActivity(context: Context, key:String, value:String){
            var intent = Intent(context, PActivity::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p)
        var bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener {
            var permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)
            if(permission == PackageManager.PERMISSION_GRANTED){
                readcontacts()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),1)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1-> {
                if (grantResults.isNotEmpty() &&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readcontacts()
                }
            }
        }
    }

    private fun readcontacts() {
        var cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        if (cursor?.moveToFirst() == true){
            do{
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                Log.d(TAG, "readcontacts:  "+name+"      "+number)
            }  while (cursor.moveToNext())
            cursor.close()
        }
    }


}