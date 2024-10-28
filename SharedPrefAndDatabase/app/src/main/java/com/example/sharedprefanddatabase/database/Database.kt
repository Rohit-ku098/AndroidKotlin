package com.example.sharedprefanddatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(Convertor::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var instance: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "user_db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}