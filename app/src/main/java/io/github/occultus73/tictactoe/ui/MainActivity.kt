package io.github.occultus73.tictactoe.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.github.occultus73.tictactoe.R
import io.github.occultus73.tictactoe.model.GameState.State.*
import io.github.occultus73.tictactoe.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dialogBuilder = MaterialAlertDialogBuilder(this)
            .setMessage(getString(R.string.play_another))
            .setNegativeButton(getString(R.string.no)) { _, _ -> finish() }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                mainViewModel.resetGameState()
            }

        mainViewModel.stateOfGame.observe(this) {
            when (it) {
                WON -> {
                    dialogBuilder.setTitle(getString(R.string.you_won)).show()
                }
                LOST -> {
                    dialogBuilder.setTitle(getString(R.string.you_lost)).show()
                }
                DRAWN -> {
                    dialogBuilder.setTitle(getString(R.string.a_draw)).show()
                }
                PLAYING -> {
                    game_board.invalidate()
                }
            }
        }
    }
}