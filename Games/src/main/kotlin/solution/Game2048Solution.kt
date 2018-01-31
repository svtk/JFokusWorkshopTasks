package solution

import board.Cell
import board.Direction
import board.Game
import board.GameBoard
import java.util.*

/*
Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game)
Implement the helper function first (moveAndMergeEqual in SurnameNameGame2048Helper.kt), then extension functions below.

Try to use methods of SquareBoard and GameBoard instead of reimplementing them.
(You may use and add extensions like SquareBoard.indices() as well).

When you finish, you can play the game by executing 'PlayGame2048' (or choosing the corresponding run configuration).
 */
fun newGame2048(): Game = Game2048()

class Game2048 : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.addRandomValue()
        board.addRandomValue()
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addRandomValue()
        }
    }

    override fun get(i: Int, j: Int): Int? = board[i, j]
}

/*
This function moves all the non-null elements to the beginning of the list (by removing nulls) and merges equal elements.
The parameter 'double' specifies the way how to merge equal elements:
it returns a new element that should be present in the result list instead of two merged elements.

If the function double("a") returns "aa",
then the function moveAndMergeEqual transforms the input in the following way:
  a, a, b -> aa, b
  b, null, a, a -> b, aa
  a, a, null, a -> aa, a
  a, null, a, a -> aa, a
Examples and tests in TestMoveAndMergeValues.kt
 */
fun <T : Any> List<T?>.moveAndMergeEqual(double: (T) -> T): List<T> =
        filterNotNull().mergeEqualConsecutiveValues(double)

fun <T : Any> List<T>.mergeEqualConsecutiveValues(double: (T) -> T): List<T> {
    val result = mutableListOf<T>()
    val used = hashSetOf<Int>()
    for ((index, current) in this.withIndex()) {
        if (index in used) continue

        val next = this.getOrNull(index + 1)
        if (current == next) {
            result += double(current)
            used += (index + 1)
        } else {
            result += current
        }
    }
    return result
}

val random = Random()
fun generateRandomStartValue() = if (random.nextInt(10) == 9) 4 else 2

/*
Add a random value to a free cell in a board.
The value should be 2 for 90% cases, 4 for the rest of the cases.
Use the generateRandomStartValue function above.
Examples and tests are in TestAddRandomValue.
 */
fun GameBoard<Int?>.addRandomValue() {
    val newValue = generateRandomStartValue()
    val emptyCells = filter { it == null }.toList()
    val cell = emptyCells[Random().nextInt(emptyCells.size)]
    this[cell.i, cell.j] = newValue
}

/*
Move values in a specified rowOrColumn only.
Use the helper function 'moveAndMergeEqual' (in SurnameNameGame2048Helper.kt).
The values should be moved to the beginning of the row (or column), in the same manner as in the function 'moveAndMergeEqual'.
Examples and tests in TestMoveValuesInRowOrColumn.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val values = rowOrColumn.map { this[it] }
    val movedValues = values.moveAndMergeEqual { it * 2 }
    val movedValuesWithNulls = (0 until width).map { movedValues.getOrNull(it) }

    if (values == movedValuesWithNulls) return false

    for ((index, cell) in rowOrColumn.withIndex()) {
        this[cell] = movedValuesWithNulls[index]
    }
    return true
}

/*
Move values by the rules of the 2048 game to the specified direction.
Use the moveValuesInRowOrColumn function above.
Examples and tests in TestMoveValues.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    val range = when (direction) {
        Direction.UP, Direction.LEFT -> 1..width
        Direction.DOWN, Direction.RIGHT -> width downTo 1
    }
    val getRowOrColumn: (Int) -> List<Cell> = when (direction) {
        Direction.UP, Direction.DOWN -> { i -> getColumn(range, i) }
        Direction.RIGHT, Direction.LEFT -> { i -> getRow(i, range) }
    }
    var move = false
    (1..width).forEach {
        val rowOrColumn = getRowOrColumn(it)
        if (moveValuesInRowOrColumn(rowOrColumn)) {
            move = true
        }
    }
    return move
}