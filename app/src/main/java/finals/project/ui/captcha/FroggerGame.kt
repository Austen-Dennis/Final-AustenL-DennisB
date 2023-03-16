package finals.project.ui.captcha

class FroggerGame(private val width: Int, private val height: Int, private val onUpdate: (CharArray) -> Unit) {
    // ...
    private val board: Array<CharArray> = Array(height) { CharArray(width) }
    private var playerX = width / 2
    private var playerY = height - 1

    init {
        resetBoard()
    }

    private fun resetBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (i % 2 == 0) {
                    board[i][j] = if (j % 2 == 0) ' ' else '-'
                } else {
                    board[i][j] = if (j % 2 == 0) '|' else ' '
                }
            }
        }
        board[playerY][playerX] = 'F'
    }

    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                print(board[i][j])
            }
            println()
        }
    }

    fun movePlayer(dx: Int, dy: Int) {
        val newX = playerX + dx
        val newY = playerY + dy

        if (newX in 0 until width && newY in 0 until height) {
            board[playerY][playerX] = if (playerY % 2 == 0) ' ' else '|'
            playerX = newX
            playerY = newY
            board[playerY][playerX] = 'F'
        }
    }
}