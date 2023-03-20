package finals.project.ui.captcha

class MazeGenerator {
    private val WALL = 1
    private val PATH = 0

    fun generateMaze(rows: Int, cols: Int): Array<IntArray> {
        val maze = Array(rows) { IntArray(cols) }

        // Set all cells to walls
        for (row in maze.indices) {
            for (col in maze[0].indices) {
                maze[row][col] = WALL
            }
        }

        // Pick a random starting cell
        var row = (0 until rows).random()
        var col = (0 until cols).random()
        maze[row][col] = PATH

        // Generate the maze using depth-first search
        generateMazeRecursive(row, col, maze)

        return maze
    }

    private fun generateMazeRecursive(row: Int, col: Int, maze: Array<IntArray>) {
        // Generate a random order to visit the neighbors
        val neighbors = listOf(
            Pair(row - 1, col),
            Pair(row + 1, col),
            Pair(row, col - 1),
            Pair(row, col + 1)
        ).shuffled()

        // Visit each neighbor in a random order
        for (neighbor in neighbors) {
            val newRow = neighbor.first
            val newCol = neighbor.second

            // Check if the neighbor is out of bounds or already visited
            if (newRow < 0 || newRow >= maze.size || newCol < 0 || newCol >= maze[0].size || maze[newRow][newCol] == PATH) {
                continue
            }

            // Carve a path to the neighbor
            if (newRow == row) {
                maze[row][(col + newCol) / 2] = PATH
            } else {
                maze[(row + newRow) / 2][col] = PATH
            }
            maze[newRow][newCol] = PATH

            // Recursively visit the neighbor
            generateMazeRecursive(newRow, newCol, maze)
        }
    }
}
