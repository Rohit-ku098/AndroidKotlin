package com.example.sharedprefanddatabase.database

import androidx.room.TypeConverter
import java.util.Date

class Convertor {
    @TypeConverter
    fun longToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }
}