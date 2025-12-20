/*
    Author:     Jessica Nguyen
    Course:     CSI 357 Android Development
    Date:       Dec 19, 2025
 */
package com.example.connectfourways.database

import androidx.room.TypeConverter
import java.util.Date

class GameRecordTypeConverters {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}