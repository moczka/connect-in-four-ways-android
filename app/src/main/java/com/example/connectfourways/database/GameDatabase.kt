package com.example.connectfourways.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.connectfourways.Game

@Database(entities = [ Game::class ], version = 1)
@TypeConverters(GameTypeConverters::class)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): Game
}