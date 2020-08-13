package io.github.occultus73.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.github.occultus73.tictactoe.model.GameState
import io.github.occultus73.tictactoe.viewmodel.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class MainViewModelTest {
    private lateinit var classUnderTest: MainViewModel

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK()
    lateinit var gameState: GameState

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { gameState.gameState } returns MutableLiveData(GameState.State.PLAYING)
        every { gameState.resetGameState() } just runs
        classUnderTest = MainViewModel(gameState)
    }

    @Test
    fun test_stateOfGame_pathway() {
        assert(classUnderTest.stateOfGame.value == GameState.State.PLAYING)
    }

    @Test
    fun resetGameState(){
        classUnderTest.resetGameState()
        verify { gameState.resetGameState() }
    }



}