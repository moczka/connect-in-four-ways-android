package com.example.connectfourways.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.connectfourways.Move
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Reference: https://www.techyourchance.com/android-room-tutorial-type-converters/#:~:text=Room%20is%20a%20wrapper%20around,converters%20come%20into%20the%20picture.
class GameTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): MutableList<Move>? {
        if (value == null) return null

        // Solution from: https://stackoverflow.com/questions/33381384/how-to-use-typetoken-generics-with-gson-in-kotlin
        val listType = object : TypeToken<MutableList<Move>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toMutableListOfMove(moves: MutableList<Move>?): String? {
        if (moves == null) return null
        return gson.toJson(moves)
    }
}