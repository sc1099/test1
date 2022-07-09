package com.example.mylibrary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mylibrary.room.entity.UpdateNameById
import com.example.mylibrary.room.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun InsertDao(vararg user:UserEntity)

    @Update
    fun UpdataDao(vararg user:UserEntity)

    @Query("Delete from table_user where id = :uid")
    fun DeleteDaoByid(uid:Int)

    @Query("Select * from table_user where name=:uname ")
    fun QuerryDaoByname(uname:String):List<UserEntity>

    @Query("Select * from table_user")
    fun QuerryAll():List<UserEntity>

    @Query("Update table_user set name = :uname where id = :uid")
    fun UpdateById(uid:Int,uname:String)

    @Update(entity = UserEntity::class)
    fun UpdateById2(u:UpdateNameById)

}