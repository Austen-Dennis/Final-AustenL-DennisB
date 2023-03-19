package finals.project.ui.login

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import androidx.core.content.ContextCompat
import finals.project.R

class MazeGameView(context: Context, attrs: AttributeSet) : View(context, attrs), View.OnKeyListener {

    private val wallChar = '█'
    private val pathChar = ' '
    private val playerChar = 'X'

    private val wallPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.FILL
    }
    private val pathPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.white)
        style = Paint.Style.FILL
    }
    private val playerPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.blue)
        style = Paint.Style.FILL
    }

    private val maze = MazeGenerator().generateMaze(15, 25)
    private var playerRow = 0
    private var playerCol = 0

    init {
        isFocusable = true
        setOnKeyListener(this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cellWidth = width / maze[0].size
        val cellHeight = height / maze.size

        for (row in maze.indices) {
            for (col in maze[0].indices) {
                when (maze[row][col]) {
                    0 -> {
                        canvas.drawRect(col * cellWidth.toFloat(), row * cellHeight.toFloat(),
                            (col + 1) * cellWidth.toFloat(), (row + 1) * cellHeight.toFloat(), pathPaint)
                    }
                    1 -> {
                        canvas.drawRect(col * cellWidth.toFloat(), row * cellHeight.toFloat(),
                            (col + 1) * cellWidth.toFloat(), (row + 1) * cellHeight.toFloat(), wallPaint)
                    }
                }
            }
        }

        canvas.drawText(playerChar.toString(), playerCol * cellWidth.toFloat() + cellWidth / 2f,
            playerRow * cellHeight.toFloat() + cellHeight / 2f, playerPaint)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        // Handle keyboard events to move the player
        when (keyCode) {
            KeyEvent.KEYCODE_W -> if (playerRow > 0 && maze[playerRow - 1][playerCol] == 0) {
                playerRow--
                invalidate()
            }
            KeyEvent.KEYCODE_A -> if (playerCol > 0 && maze[playerRow][playerCol - 1] == 0) {
                playerCol--
                invalidate()
            }
            KeyEvent.KEYCODE_S -> if (playerRow < maze.size - 1 && maze[playerRow + 1][playerCol] == 0) {
                playerRow++
                invalidate()
            }
            KeyEvent.KEYCODE_D -> if (playerCol < maze[0].size - 1 && maze[playerRow][playerCol + 1] == 0) {
                playerCol++
                invalidate()
            }
        }
        return true
    }
}
