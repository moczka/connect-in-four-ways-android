/**
 * Filename: GameDao.kt
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
import androidx.room.Query
import androidx.room.Update
import com.example.connectfourways.Game
import com.example.connectfourways.Move
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getGame() : Game

    @Query("SELECT movesHistory FROM Game WHERE id=(:id)")
    suspend fun getMovesHistory(id: UUID): Flow<MutableList<Move>?>

    @Query("SELECT gameRecordingFilename FROM Game WHERE id=(:id)")
    suspend fun getGameRecording(id: UUID): String?

    @Update
    suspend fun updateGame(game: Game)
}