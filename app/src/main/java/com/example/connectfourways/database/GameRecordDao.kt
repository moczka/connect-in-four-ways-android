/**
 * Filename: GameRecordDao.kt
 * Course: CSI357 - Fall 2025
 * Name: Thanh Van Nguyen
 * Date: Dec 18, 2025
 * Final Project (Connect 4)
 * Description: An app that allows 2 players to take turn
 *              and play Connect4 on a device. The game will
 *              be recorded and can be rewatched.
 */

package com.example.connectfourways.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.connectfourways.GameRecord
import java.util.UUID

@Dao
interface GameRecordDao {
    // Return list of completed games
    @Query("SELECT * FROM GameRecord")
    suspend fun getGameList(): List<GameRecord>

    // Return data of a completed game
    @Query("SELECT * FROM GameRecord WHERE id=(:id)")
    suspend fun getGameRecord(id: UUID): GameRecord

    // Add new completed game
    @Insert
    suspend fun addGameRecord(gameRecord: GameRecord)

}