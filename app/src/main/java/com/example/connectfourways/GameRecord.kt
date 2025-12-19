package com.example.connectfourways

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class GameRecord(
    @PrimaryKey val id: UUID,
    val player1: String,
    val player2: String,
    val winner: String,
    val duration: Int,
    val date: Date,
    val player1MovesHistory: String,
    val player2MovesHistory: String,

    // For future functionality
    //val gameRecordingFilename: String
)