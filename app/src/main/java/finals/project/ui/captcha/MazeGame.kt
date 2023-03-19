package finals.project.ui.captcha

import java.util.*


class MazeGame {
    // The player's current position
    private var playerRow: Int
    private var playerCol: Int

    init {
        // Initialize the player's position
        playerRow = START_ROW
        playerCol = START_COL
    }

    fun play() {
        // Display the initial maze
        printMaze()
        // Play until the player reaches the end of the maze
        while (!isGameOver) {
            // Get the next move from the player
            nextMove
            // Display the updated maze
            printMaze()
        }
        // Display the final maze and victory message
        printMaze()
        println("Congratulations, you made it to the end!")
    }

    private fun printMaze() {
        // Print the maze
        for (i in MAZE.indices) {
            for (j in MAZE[i].indices) {
                if (i == playerRow && j == playerCol) {
                    print("P ")
                } else if (MAZE[i][j] == 1) {
                    print("# ")
                } else {
                    print(". ")
                }
            }
            println()
        }
    }

    // Check if the player has reached the end of the maze
    private val isGameOver: Boolean
        private get() =// Check if the player has reached the end of the maze
            playerRow == MAZE.size - 2 && playerCol == MAZE[0].size - 2

    // Get the next move from the player
    private val nextMove: Unit
        private get() {
            // Get the next move from the player
            val scanner = Scanner(System.`in`)
            print("Enter your next move (WASD): ")
            val input = scanner.next()
            when (input) {
                "W" -> if (playerRow > 0 && MAZE[playerRow - 1][playerCol] == 0) {
                    playerRow--
                }
                "A" -> if (playerCol > 0 && MAZE[playerRow][playerCol - 1] == 0) {
                    playerCol--
                }
                "S" -> if (playerRow < MAZE.size - 1 && MAZE[playerRow + 1][playerCol] == 0) {
                    playerRow++
                }
                "D" -> if (playerCol < MAZE[0].size - 1 && MAZE[playerRow][playerCol + 1] == 0) {
                    playerCol++
                }
            }
        }

    companion object {
        // The maze layout
        private val MAZE = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1)
        )

        // The player's starting position
        private const val START_ROW = 1
        private const val START_COL = 1
        @JvmStatic
        fun main(args: Array<String>) {
            // Create a new MazeGame and play it
            val game = MazeGame()
            game.play()
        }
    }
}
