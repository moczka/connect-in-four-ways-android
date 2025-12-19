package com.example.connectfourways

import androidx.room.Entity

@Entity
data class Move(
    val playerId: String,
    val column: Int
)
