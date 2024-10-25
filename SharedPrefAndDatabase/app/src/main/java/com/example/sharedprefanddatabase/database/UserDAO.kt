package com.example.sharedprefanddatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    suspend fun insert(data: UserEntity)

    @Update
    suspend fun update(data: UserEntity)

    @Delete
    suspend fun delete(data: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUser(): LiveData<MutableList<UserEntity>>
}