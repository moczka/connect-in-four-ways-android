/*
    Author:     Javier Steven Guerrero
    Course:     CSI 357 Android Development
    Date:       Dec 16, 2025

 */
package com.example.connectfourways

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

private const val BOARD_NUM_COL = 7
class GamePlayViewModel : ViewModel() {
    var activePlayer = "P1"
    val boardColumns = Array(BOARD_NUM_COL){ mutableListOf<String>() }

    val player1Moves = mutableListOf<String>()
    val player2Moves = mutableListOf<String>()
    var hasGameStarted = false

    var hasGameEnded = false
    private val _gameTime = MutableStateFlow(0)
    val gameTime: StateFlow<Int> = _gameTime
    private var timerJob: Job? = null

    fun finalizeGame(winner: String) {
        // update game state variables
        hasGameEnded = true
        // Pause game time
        stopTimer()
        // write game record to database
        viewModelScope.launch {
            GameRepository.get().addGameRecord(
                GameRecord(UUID.randomUUID(),
                    "P1", "P2",
                    winner, gameTime.value,
                    Date(), player1Moves.joinToString(","),
                    player2Moves.joinToString(",")
                )
            )
        }
    }
    fun startTimer() {
        timerJob?.cancel() // Cancel any existing job
        timerJob = viewModelScope.launch {
            // Emits an item every 1 second (1000L milliseconds)
            while (isActive) {
                delay(1000L)
                _gameTime.value++
            }
        }
    }
    fun stopTimer() {
        timerJob?.cancel()
    }
}