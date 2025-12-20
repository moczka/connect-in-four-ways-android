/*
    Author:     Jessica Nguyen
    Course:     CSI 357 Android Development
    Date:       Dec 19, 2025
 */
package com.example.connectfourways

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameHistoryViewModel : ViewModel() {
    private val gameRepository = GameRepository.get()

    private val _gameList = MutableStateFlow<List<GameRecord>>(emptyList())
    val gameList: StateFlow<List<GameRecord>> = _gameList.asStateFlow()

    init {
        loadGameList()
    }

    private fun loadGameList() {
        viewModelScope.launch {
            _gameList.value = gameRepository.getGameList()
        }
    }
}
