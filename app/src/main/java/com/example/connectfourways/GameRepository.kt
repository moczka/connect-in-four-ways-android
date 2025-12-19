package com.example.connectfourways

import android.content.Context
import androidx.room.Room
import com.example.connectfourways.database.GameRecordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "game-database"
class GameRepository private constructor(
    context: Context,
) {
    private val database: GameRecordDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameRecordDatabase::class.java,
        DATABASE_NAME
    ).build()

    suspend fun getGameList(): List<GameRecord>
        = database.gameRecordDao().getGameList()

    suspend fun getGameRecord(id: UUID): GameRecord
        = database.gameRecordDao().getGameRecord(id)

    suspend fun addGameRecord(gameRecord: GameRecord)
        = database.gameRecordDao().addGameRecord(gameRecord)

    companion object {
        private var INSTANCE: GameRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository(context)
            }
        }

        fun get(): GameRepository {
            return INSTANCE ?:
            throw IllegalStateException("GameRepository must be initialized")
        }
    }
}