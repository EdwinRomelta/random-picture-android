package com.edwin.randompicture.cache.db.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


internal class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? =
            if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? =
            date?.time

}