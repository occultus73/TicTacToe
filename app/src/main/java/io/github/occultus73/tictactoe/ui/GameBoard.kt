package io.github.occultus73.tictactoe.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.AndroidEntryPoint
import io.github.occultus73.tictactoe.R
import io.github.occultus73.tictactoe.model.GameState
import io.github.occultus73.tictactoe.model.GameState.Square.*
import javax.inject.Inject


@AndroidEntryPoint
class GameBoard(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet), LifecycleObserver {

    @Inject
    lateinit var gameState: GameState

    private val blackStroke = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val zeroIn = 0f
    private val zeroDown = 0f
    private val allTheWayIn by lazy { width.toFloat() }
    private val allTheWayDown by lazy { height.toFloat() }
    private val oneThirdIn by lazy { width.toFloat() / 3 }
    private val oneThirdDown by lazy { height.toFloat() / 3 }
    private val twoThirdsIn by lazy { width.toFloat() * 2 / 3 }
    private val twoThirdsDown by lazy { height.toFloat() * 2 / 3 }
    private val horizontalUnit by lazy { oneThirdIn.toInt() }
    private val verticalUnit by lazy { oneThirdDown.toInt() }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw game board lines.
        canvas.drawLine(oneThirdIn, zeroDown, oneThirdIn, allTheWayDown, blackStroke)
        canvas.drawLine(twoThirdsIn, zeroDown, twoThirdsIn, allTheWayDown, blackStroke)
        canvas.drawLine(zeroIn, oneThirdDown, allTheWayIn, oneThirdDown, blackStroke)
        canvas.drawLine(zeroIn, twoThirdsDown, allTheWayIn, twoThirdsDown, blackStroke)

        // Draw all game pieces in play.
        for (row in 0 until 3) {
            for (item in 0 until 3) {
                when (gameState.squareMatrix[row][item]) {
                    CROSS -> {
                        val cross = getDrawable(context, R.drawable.ic_cross)
                        val startLeft = horizontalUnit * item
                        val startTop = verticalUnit * row
                        val endLeft = startLeft + horizontalUnit
                        val endBottom = startTop + verticalUnit

                        cross?.setBounds(startLeft, startTop, endLeft, endBottom)
                        cross?.draw(canvas)
                    }
                    NOUGHT -> {
                        val circle = getDrawable(context, R.drawable.ic_circle)
                        val startLeft = horizontalUnit * item
                        val startTop = verticalUnit * row
                        val endLeft = startLeft + horizontalUnit
                        val endBottom = startTop + verticalUnit

                        circle?.setBounds(startLeft, startTop, endLeft, endBottom)
                        circle?.draw(canvas)
                    }
                    EMPTY -> {
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if(event?.action == MotionEvent.ACTION_DOWN){
            changeGameState(event.x, event.y)
            true
        } else false
    }

    private fun changeGameState(x: Float, y: Float){
        when{
            y < oneThirdDown -> when {
                x < oneThirdIn -> gameState.playerClick(0,0)
                x < twoThirdsIn -> gameState.playerClick(0,1)
                else -> gameState.playerClick(0,2)
            }

            y < twoThirdsDown -> when {
                x < oneThirdIn -> gameState.playerClick(1,0)
                x < twoThirdsIn -> gameState.playerClick(1,1)
                else -> gameState.playerClick(1,2)
            }

            else -> when {
                x < oneThirdIn -> gameState.playerClick(2,0)
                x < twoThirdsIn -> gameState.playerClick(2,1)
                else -> gameState.playerClick(2, 2)
            }
        }
        invalidate()
    }

}