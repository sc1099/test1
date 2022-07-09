package com.example.mylibrary.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mylibrary.room.dao.ScDao
import com.example.mylibrary.room.dao.UserDao
import com.example.mylibrary.room.entity.ScEntity
import com.example.mylibrary.room.entity.UserEntity

@Database(entities = [UserEntity::class,ScEntity::class], version = 2)
abstract class UserDatabase:RoomDatabase() {

    abstract fun getUserDao():UserDao
    abstract fun getScDao():ScDao


    companion object {
        val migration1To2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Create table sc_table ( id integer primary key autoincrement not null," +
                        "name text not null," +
                        "age integer not null)")
            }
        }

        private var INSTANCE: UserDatabase? = null
        // Application
        fun getDatabase(context: Context): UserDatabase? {

            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, UserDatabase::class.java, "UserDatabase.db")
                        .allowMainThreadQueries() // 允许在主线程运行
                        .addMigrations(migration1To2)
                        .build()
            }

            return INSTANCE

        }
    }

//    fun getUserDatabase():UserDatabase ?=INSTANCE


}