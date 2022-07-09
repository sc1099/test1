package com.example.mykotlin.MyContentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.mykotlin.fragmenttest.sp.sqliteHelper

class MyContentProvider :ContentProvider() {
    private val bookdir = 0
    private val bookitem = 1
    private val authority = "com.example.kotlin.myprovider"
    private val path_dir = "book"
    private val path_item = "book/#"
    var dbhelp:sqliteHelper? = null
    private val uriMatch by lazy {
        val match = UriMatcher(UriMatcher.NO_MATCH)
        match.addURI(authority,path_dir,bookdir)
        match.addURI(authority,path_item,bookitem)
        match
    }

    override fun onCreate(): Boolean {
        if(context == null) return false
        dbhelp = context?.let { sqliteHelper(it,"BookStore.db",4) }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var db = dbhelp?.readableDatabase
        var cursor = when(uriMatch.match(uri)){
            bookdir->{
                db?.query("book",projection,selection,selectionArgs,null,null,sortOrder)
            }
            bookitem->{
                val bookid = uri.pathSegments[1]
                db?.query("book", projection,"id = ?", arrayOf(bookid),null,null,sortOrder)
            }
            else -> null
        }
        return cursor
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var db = dbhelp?.writableDatabase
        val uriReturn = when(uriMatch.match(uri)){
            bookdir->{
                var newbookId = db?.insert(path_dir,null,values)
                Uri.parse("content://$authority/$path_dir/$newbookId")
            }
            else->null
        }
        return uriReturn
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var db = dbhelp?.writableDatabase
        return when(uriMatch.match(uri)){
            bookdir->{
                db?.delete(path_dir,selection,selectionArgs)
            }
            bookitem->{
                var deleteId = uri.pathSegments[1]
                db?.delete(path_dir,"id = ?", arrayOf(deleteId))
            }
            else->0
        }?:0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var db = dbhelp?.writableDatabase
        return when(uriMatch.match(uri)){
            bookdir->{
                db?.update(path_dir,values,selection,selectionArgs)
            }
            bookitem->{
                var updateId = uri.pathSegments[1]
                db?.update(path_dir,values,"id = ?", arrayOf(updateId))
            }
            else->0
        }?:0
    }


    override fun getType(uri: Uri): String? {
        return when(uriMatch.match(uri)){
            bookdir-> "vnd.android.cursor.dir/vnd."+authority+"."+path_dir
            bookitem-> "vnd.android.cursor.item/vnd."+authority+"."+path_dir
            else->null
        }
    }
}