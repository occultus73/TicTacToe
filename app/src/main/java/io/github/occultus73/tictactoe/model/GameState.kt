package io.github.occultus73.tictactoe.model

import androidx.lifecycle.MutableLiveData
import io.github.occultus73.tictactoe.model.GameState.Square.*
import io.github.occultus73.tictactoe.model.GameState.State.*
import kotlin.random.Random

class GameState() {
    private val playerSymbol: Square = CROSS
    private val computerSymbol: Square = NOUGHT

    enum class Square { CROSS, NOUGHT, EMPTY }
    enum class State { PLAYING, WON, LOST, DRAWN }

    val gameState = MutableLiveData<State>(PLAYING)

    // Sample input, 3x3 Square Matrix:
    var squareMatrix = arrayOf(
        arrayOf(EMPTY, EMPTY, EMPTY),
        arrayOf(EMPTY, EMPTY, EMPTY),
        arrayOf(EMPTY, EMPTY, EMPTY)
    )

    fun playerClick(row: Int, item: Int) {
        if (squareMatrix[row][item] == EMPTY && gameState.value == PLAYING) {
            squareMatrix[row][item] = playerSymbol
            checkIfWon()
            checkIfDrawn()

            if (gameState.value == PLAYING) computerGo()
        }
    }

    private fun checkIfDrawn() {
        if(gameState.value != PLAYING) return

        squareMatrix.forEach {
            if (it.contains(EMPTY)) return
        }
        gameState.value = DRAWN
    }

    private fun checkIfWon() {
        if(gameState.value != PLAYING) return

        for (row in 0 until 3) {
            when (checkHorizontalStrikes(row)) {
                playerSymbol ->{ gameState.value = WON; return }
                computerSymbol ->{ gameState.value = LOST; return }
                else -> { }
            }
        }
        for (col in 0 until 3) {
            when (checkVerticalStrikes(col)) {
                playerSymbol ->{ gameState.value = WON; return }
                computerSymbol ->{ gameState.value = LOST; return }
                else -> {
                }
            }
        }
        when (checkDiagonalStrikes()) {
            playerSymbol ->{ gameState.value = WON; return }
            computerSymbol ->{ gameState.value = LOST; return }
            else -> { }
        }
    }

    private fun checkHorizontalStrikes(row: Int): Square {
        val symbol = squareMatrix[row][0]
        for (item in 1 until 3) {
            if (squareMatrix[row][item] != symbol) return EMPTY
        }
        return symbol
    }

    private fun checkVerticalStrikes(col: Int): Square {
        val symbol = squareMatrix[0][col]
        for (item in 1 until 3) {
            if (squareMatrix[item][col] != symbol) return EMPTY
        }
        return symbol
    }

    private fun checkDiagonalStrikes(): Square {
        // check top left to bottom right
        return if (squareMatrix[0][0] == squareMatrix[1][1] &&
            squareMatrix[1][1] == squareMatrix[2][2]
        )
            squareMatrix[0][0]

        //check bottom left to top right
        else if (squareMatrix[0][2] == squareMatrix[1][1] &&
            squareMatrix[1][1] == squareMatrix[2][0]
        )
            squareMatrix[0][2]
        else EMPTY
    }


    private fun computerGo() {
        var x = 0
        var y = 0
        do {
            x = Random.nextInt(0, 3)
            y = Random.nextInt(0, 3)
        } while (squareMatrix[x][y] != EMPTY)

        squareMatrix[x][y] = computerSymbol
        checkIfWon()
        checkIfDrawn()
    }

    fun resetGameState(){

        squareMatrix = arrayOf(
            arrayOf(EMPTY, EMPTY, EMPTY),
            arrayOf(EMPTY, EMPTY, EMPTY),
            arrayOf(EMPTY, EMPTY, EMPTY)
        )

        gameState.value = PLAYING
    }
}