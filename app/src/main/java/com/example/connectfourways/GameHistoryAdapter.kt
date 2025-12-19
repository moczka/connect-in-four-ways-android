package com.example.connectfourways

import androidx.recyclerview.widget.RecyclerView
import com.example.connectfourways.databinding.FragmentGameHistoryBinding

class GameHistoryAdapter (
    private var games: List<GameRecord> = emptyList()
    ): RecyclerView.Adapter<GameHistoryAdapter.GameViewHolder>() {

        class GameViewHolder(
            private val binding: FragmentGameHistoryBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(game: GameRecord) {
                binding.playerNames.text =
                    "${game.player1} vs ${game.player2}"

                binding.winner.text =
                    "Winner: ${game.winner}"

                binding.duration.text =
                    "Duration: ${game.duration}s"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
            val binding = ItemGameRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return GameViewHolder(binding)
        }

        override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
            holder.bind(games[position])
        }

        override fun getItemCount(): Int = games.size

        fun submitList(newGames: List<GameRecord>) {
            games = newGames
            notifyDataSetChanged() // OK for small lists
        }

}