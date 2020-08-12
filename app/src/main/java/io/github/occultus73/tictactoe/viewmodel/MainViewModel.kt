package io.github.occultus73.tictactoe.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.occultus73.tictactoe.model.GameState

class MainViewModel
@ViewModelInject
constructor(private val gameState: GameState): ViewModel(){

    val stateOfGame: LiveData<GameState.State> = gameState.gameState

    fun resetGameState() = gameState.resetGameState()
}
