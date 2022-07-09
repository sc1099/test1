package com.example.mylibrary.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sc_table")
data class ScEntity(@PrimaryKey(autoGenerate = true) val id:Int = 0,
                    @ColumnInfo(name = "name") val sc_name:String,
                    @ColumnInfo(name = "age") val sc_age:Int
                    ) {
}