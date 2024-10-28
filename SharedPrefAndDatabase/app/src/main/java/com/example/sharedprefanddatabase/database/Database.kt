package com.example.sharedprefanddatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UserEntity::class], version = 2)
@TypeConverters(Convertor::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        // Database Migration from version 1 to 2
        /**
         * When making any chenge in the database schema, we need to add migration
         */
        val migration_1_2 = object : Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE users ADD COLUMN isActive INTEGER NOT NULL DEFAULT 1")
            }
        }

        @Volatile
        private var instance: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "user_db"
                        )
                        .addMigrations(migration_1_2)
                        .build()
                }
            }
            return instance!!
        }
    }
}