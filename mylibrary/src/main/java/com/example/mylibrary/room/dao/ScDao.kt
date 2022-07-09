package com.example.mylibrary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mylibrary.room.entity.ScEntity
import com.example.mylibrary.room.entity.UserEntity


@Dao
interface ScDao {

    @Insert
    fun InsertDao(vararg sc: ScEntity)

    @Query("Select * from sc_table")
    fun QuerryAll():List<ScEntity>
}