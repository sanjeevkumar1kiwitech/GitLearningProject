package com.example.mvvm_skeleton_demo.database.convertor

import androidx.room.TypeConverter
import java.util.*

class TypeConvertor {

    @TypeConverter
    fun fromDateToLong(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long):Date{
        return Date(value)
    }
}