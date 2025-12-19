package com.example.connectfourways

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.connectfourways.databinding.GameHistoryItemBinding
import java.util.UUID

private const val TAG = "GameHistoryItemHolder"

class GameHistoryItemHolder (private val binding: GameHistoryItemBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(gameHistoryItem: GameRecord,
        onGameHistoryItemClicked: (UUID) -> Unit)
    {
        binding.apply {
            gameWinner.text = itemView.context.getString(
                R.string.game_record_winner,
                gameHistoryItem.winner
            )
            gameDate.text = gameHistoryItem.date.toString()
            root.setOnClickListener {
                view -> Log.d(TAG, "Tapped ${gameHistoryItem.winner}")
                onGameHistoryItemClicked(gameHistoryItem.id)
            }
        }
    }

}