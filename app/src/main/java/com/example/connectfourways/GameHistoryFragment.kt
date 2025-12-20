/*
    Author:     Jessica Nguyen
    Course:     CSI 357 Android Development
    Date:       Dec 19, 2025
 */
package com.example.connectfourways

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.connectfourways.databinding.FragmentGameHistoryBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


class GameHistoryFragment : Fragment() {
    private var _binding: FragmentGameHistoryBinding? = null

    private val gameHistoryViewModel: GameHistoryViewModel by viewModels()

    private val binding
        get() = checkNotNull(_binding) {
            "binding should not be null"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameHistoryBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gameHistoryViewModel.gameList.collect { gameHistory ->
                    // Update UI
                    generateGameHistoryTable(gameHistory)
                }
            }
        }
    }

    fun generateGameHistoryTable(gameHistory: List<GameRecord>) {
        for (game in gameHistory) {
            // Create a new TableRow
            val tableRow = TableRow(context)
            tableRow.setLayoutParams(
                TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
            )

            // Create views for each game record field
            tableRow.addView(
                createTextView(game.winner, Gravity.START)
            )
            tableRow.addView(
                createTextView(
                    getString(
                        R.string.game_players_field,
                        game.player1, game.player2),
                    Gravity.CENTER)
            )

            // Define the desired format pattern
            val pattern = "yyyy-MM-dd HH:mm:ss a" // Example format: 2025-12-19 19:47:45 PM
            // Create a SimpleDateFormat instance with a locale
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            // Format the Date object into a String
            val formattedDate: String = simpleDateFormat.format(game.date)
            tableRow.addView(
                createTextView(formattedDate, Gravity.START)
            )
            tableRow.addView(
                createTextView(getString(
                    R.string.game_duration_field,
                    game.duration.toString()), Gravity.END)
            )

            // Add the TableRow to the TableLayout
            binding.gameHistoryTable.addView(tableRow)
        }
    }

    // Helper function to create and format each TextView element
    // inside the TableLayout's rows
    private fun createTextView(
        text: String,
        gravity: Int = Gravity.START
    ): TextView {
        return TextView(requireContext()).apply {
            this.text = text
            when(text) {
                "P1" -> {
                    this.setTextColor(resources.getColor(R.color.gameplay_board_disc_primary))
                }
                "P2" -> {
                    this.setTextColor(resources.getColor(R.color.gameplay_board_disc_secondary))
                }
            }
            setPadding(resources.getDimension(R.dimen.table_col_heading_vertical_padding).toInt())
            this.gravity = gravity
        }
    }
}
