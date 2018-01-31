package solution

import board.Direction
import board.GameBoard
import board.reversed
import board.Game
import java.util.*

/*
This function should return the parity of the permutation.
true - the permutation is even
false - the permutation is odd
https://en.wikipedia.org/wiki/Parity_of_a_permutation

If the game of fifteen is started with the wrong parity, you can't get the correct result
  (numbers sorted in the right order, empty cell at last).
Thus the initial permutation should be correct.
 */
fun countParity(permutation: List<Int>): Boolean {
    var parity = 0
    val indices = permutation.indices
    for (i in indices) {
        for (j in indices) {
            if (i < j && permutation[i] > permutation[j]) {
                parity++
            }
        }
    }
    return parity % 2 == 0
}

/*
Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
When you finish, you can play the game by executing 'PlayGameOfFifteen' (or choosing the corresponding run configuration).
*/
fun newGameOfFifteen(): Game = GameOfFifteen()

class GameOfFifteen : Game {
    private val width = 4
    private val board: GameBoard<Int> = GameBoardImpl(width)

    override fun initialize() {
        val permutation = (0..15).toMutableList()
        Collections.shuffle(permutation)
        val iterator = permutation.iterator()
        val parity = countParity(permutation)
        if (parity) {
            Collections.swap(permutation, 0, 1)
        }
        for ((i, j) in board.getAllCells()) {
            val value = iterator.next()
            board[i, j] = if (value != 0) value else null
        }
    }

    override fun canMove() = true

    override fun hasWon(): Boolean {
        val values = mutableListOf<Int?>()
        for ((i, j) in board.getAllCells()) {
            values += board[i, j]
        }
        return values == ((1..15).toList() + (null as Int?))
    }

    override fun processMove(direction: Direction) {
        val cell = board.find { it == null }!!
        val cellToMove = with(board) { cell.getNeighbour(direction.reversed()) } ?: return
        board[cell] = board[cellToMove]
        board[cellToMove] = null
    }

    override fun get(i: Int, j: Int): Int? {
        return board[i, j]
    }
}