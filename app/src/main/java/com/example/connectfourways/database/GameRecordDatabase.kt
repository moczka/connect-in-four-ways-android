/*
    Author:     Jessica Nguyen
    Course:     CSI 357 Android Development
    Date:       Dec 19, 2025
 */
package com.example.connectfourways.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.connectfourways.GameRecord

@Database(entities = [ GameRecord::class ], version = 1)
@TypeConverters(GameRecordTypeConverters::class)
abstract class GameRecordDatabase: RoomDatabase() {
    abstract fun gameRecordDao(): GameRecordDao
}