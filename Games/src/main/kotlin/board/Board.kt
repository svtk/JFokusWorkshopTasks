package board

import board.Direction.*

data class Cell(val i: Int, val j: Int)

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

/*
SquareBoard holds information about the board and lets access parts of lines and
columns. For instance, you can write `board.getRow(1, width downTo 1)`
to get the cells for the first row in the reversed order.
The getCell function should throw IllegalArgumentException for invalid i and j.
Note that the cell numeration starts with 1, not with 0.
You can find examples of usage in tests.
 */
interface SquareBoard {
    val width: Int

    fun getCell(i: Int, j: Int): Cell
    fun getCellOrNull(i: Int, j: Int): Cell?

    fun getAllCells(): Collection<Cell>

    fun getRow(i: Int, jRange: IntProgression): List<Cell>
    fun getColumn(iRange: IntProgression, j: Int): List<Cell>

    fun Cell.getNeighbour(direction: Direction): Cell?
}

/*
GameBoard holds the values for the cells, and allows to update them.
It provides functional style API to manipulate the values (any, all, etc.).
 */
interface GameBoard<T> : SquareBoard {

    operator fun get(cell: Cell): T?
    operator fun set(cell: Cell, value: T?)

    operator fun get(i: Int, j: Int): T?
    operator fun set(i: Int, j: Int, value: T?)

    operator fun contains(value: T): Boolean

    fun filter(predicate: (T?) -> Boolean): Collection<Cell>
    fun find(predicate: (T?) -> Boolean): Cell?
    fun any(predicate: (T?) -> Boolean): Boolean
    fun all(predicate: (T?) -> Boolean): Boolean
}

fun Direction.reversed() = when (this) {
    UP -> DOWN
    DOWN -> UP
    RIGHT -> LEFT
    LEFT -> RIGHT
}