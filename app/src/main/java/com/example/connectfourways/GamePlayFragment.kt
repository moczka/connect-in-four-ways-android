package com.example.connectfourways

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.graphics.createBitmap
import androidx.core.view.isEmpty
import androidx.core.view.setPadding
import com.example.connectfourways.databinding.FragmentGamePlayBinding

private const val TAG = "GamePlayFragment"
private const val BOARD_NUM_ROW = 6
private const val BOARD_NUM_COL = 7
class GamePlayFragment : Fragment() {
    private var _binding: FragmentGamePlayBinding? = null;
    private val binding
        get() = checkNotNull(_binding) {
            "binding should not be null"
        }
    private val viewModel: GamePlayViewModel by viewModels()
    // Need to keep track of the id of each cell to drop discs into the columns by updating the slot images
    val boardSlotIds: Array<Array<Int>> = Array(BOARD_NUM_ROW) { Array(BOARD_NUM_COL) { 0 } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGamePlayBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Create game board
        binding.gameboard.post {
            // Only recreate board if it is empty
            if (binding.gameboard.isEmpty())
                createBoard()
        }
    }
    private fun createBoard() {
        for (rowIndex in 0 until BOARD_NUM_ROW) {
            val tableRow = TableRow(context)
            for (colIndex in 0 until BOARD_NUM_COL) {
                // Create board slot
                val slotWidget = ImageView(context)
                val slotId = View.generateViewId()
                slotWidget.id = slotId
                boardSlotIds[rowIndex][colIndex] = slotId
                slotWidget.tag = colIndex
                slotWidget.setOnClickListener(::handleSlotClick)
                slotWidget.setBackgroundColor(resources.getColor(R.color.black))
                slotWidget.setPadding(resources.getDimensionPixelSize(R.dimen.cell_slot_padding))
                slotWidget.setImageBitmap(createSlotImage(binding.gameboard.width, false, getSlotInformation(colIndex, rowIndex)))
                // Add slot to row
                tableRow.addView(slotWidget)
            }
            binding.gameboard.addView(tableRow)
        }
    }

    private fun handleSlotClick(view: View) {
        val colIndex: Int = view.tag as Int
        // Do nothing if column is full
        if (viewModel.boardColumns[colIndex].size == BOARD_NUM_COL)
            return
        Log.d(TAG, "User has dropped disc in column: ${view.tag}")
        // place disc in column
        placeDiscInColumn(colIndex, viewModel.activePlayer)
        // process turn
        processTurn(viewModel.activePlayer, colIndex)
    }

    private fun placeDiscInColumn(colIndex: Int, player: String) {
        // active column
        val selectedColumn = viewModel.boardColumns[colIndex]
        // Compute slot location relative to the graphical board grid
        val rowIndex = (BOARD_NUM_ROW - selectedColumn.size) % BOARD_NUM_ROW
        // update slot in graphical board grid to show placed disc
        val widgetId = boardSlotIds[rowIndex][colIndex]
        val slotImage: ImageView? = view?.findViewById<ImageView>(widgetId)
        // Update slot image
        slotImage?.setImageBitmap(createSlotImage(binding.gameboard.width, true, player))
        // Place disc in column
        selectedColumn.add(player)
    }

    private fun createSlotImage(boardWidth: Int, isSelected: Boolean, player: String): Bitmap {
        val slotDimensions = calculateSlotDimensions(boardWidth)
        val image = createBitmap(slotDimensions.first, slotDimensions.second)
        val canvas = Canvas(image)
        val backgroundColor = getSlotBackgroundColor(player)
        canvas.drawColor(backgroundColor)
        return image;
    }
    private fun getSlotInformation(colIndex: Int, rowIndex: Int): String {
        // Compute the actual index of the slot relative to the played discs
        val boardColumn = viewModel.boardColumns[colIndex]
        val realRowIndex = BOARD_NUM_ROW - rowIndex
        if (realRowIndex < boardColumn.size) {
            return boardColumn[realRowIndex]
        }
        return ""
    }
    private fun calculateSlotDimensions(boardWidth: Int): Pair<Int, Int> {
        val slotWidth = boardWidth / BOARD_NUM_COL
        // Cells must be squared
        return Pair(slotWidth, slotWidth)
    }

    private fun getSlotBackgroundColor(player: String): Int {
        when(player) {
            "P1" -> {
                return resources.getColor(R.color.secondary_positive)
            }
            "P2" -> {
                return resources.getColor(R.color.secondary_negative)
            }
        }
        return resources.getColor(R.color.primary_over_background)
    }

    private fun processTurn(player: String, colIndex: Int) {
        // Update who's turn it is
        viewModel.activePlayer = if (viewModel.activePlayer == "P1") "P2" else "P1"
        // check if there is a winner
        // TODO: ADD ALGORITHM TO CHECK IF THERE IS A WINNER
    }
}