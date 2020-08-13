package io.github.occultus73.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.occultus73.tictactoe.model.GameState
import io.mockk.verify
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GameStateTest {
    private lateinit var classUnderTest: GameState


    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        classUnderTest = GameState()
    }

    @Test
    fun `test game playing`(){
        assert(classUnderTest.gameState.value == GameState.State.PLAYING)
    }

    @Test
    fun `test player click`(){
        classUnderTest.playerClick(1,1)
        assert(classUnderTest.squareMatrix[1][1] != GameState.Square.NOUGHT)
    }

    @Test
    fun `test game reset`(){
        classUnderTest.resetGameState()
        classUnderTest.squareMatrix.forEach {
            assertFalse(it.contains(GameState.Square.NOUGHT))
            assertFalse(it.contains(GameState.Square.CROSS))
        }
        assert(classUnderTest.gameState.value == GameState.State.PLAYING)
    }

}