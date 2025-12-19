package com.example.connectfourways

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Game(
    @PrimaryKey val id: UUID,
    val player1: String,
    val player2: String,
    val winner: String,
    val duration: Int,
    val player1MovesHistory: String? = null,
    val player2MovesHistory: String? = null,
    val gameRecordingFilename: String? = null
)