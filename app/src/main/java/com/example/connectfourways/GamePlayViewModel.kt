package com.example.connectfourways

import android.widget.ImageView
import androidx.lifecycle.ViewModel
private const val BOARD_NUM_COL = 7
class GamePlayViewModel : ViewModel() {
    var activePlayer = "P1"
    val boardColumns: Array<MutableList<String>> = Array(BOARD_NUM_COL) { mutableListOf("") }
}