package finals.project.data.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View

class MazeGameView : View, View.OnKeyListener {
    // The maze layout
    private val maze = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1)
    )

    // The player's starting position
    private var playerRow = 1
    private var playerCol = 1

    // The paint used to draw the maze and player
    private val paint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        // Set up the paint
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL_AND_STROKE

        // Set up the key listener
        isFocusableInTouchMode = true
        requestFocus()
        setOnKeyListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw the maze
        for (i in maze.indices) {
            for (j in maze[i].indices) {
                val x = j * 50f
                val y = i * 50f

                if (maze[i][j] == 1) {
                    canvas?.drawRect(x, y, x + 50, y + 50, paint)
                }
            }
        }

        // Draw the player
        paint.color = Color.GREEN
        val x = playerCol * 50f
        val y = playerRow * 50f
        canvas?.drawCircle(x + 25, y + 25, 20f, paint)
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
            else -> return false
        }
        return true
    }
}
