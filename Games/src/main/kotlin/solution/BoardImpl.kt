package solution

import board.Cell
import board.Direction
import board.Direction.*
import board.GameBoard
import board.SquareBoard

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

open class SquareBoardImpl(final override val width: Int) : SquareBoard {
    private val cells = mutableMapOf<Pair<Int, Int>, Cell>()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                cells[i to j] = Cell(i, j)
            }
        }
    }

    override fun getCell(i: Int, j: Int): Cell = cells[i to j] ?:
            throw IllegalArgumentException("Incorrect coordinates ($i, $j): should be in 1..$width")

    override fun getCellOrNull(i: Int, j: Int): Cell? = if (i !in 1..width || j !in 1..width) null else getCell(i, j)

    override fun Cell.getNeighbour(direction: Direction) = when (direction) {
        UP -> getCellOrNull(i - 1, j)
        DOWN -> getCellOrNull(i + 1, j)
        RIGHT -> getCellOrNull(i, j + 1)
        LEFT -> getCellOrNull(i, j - 1)
    }

    override fun getRow(i: Int, jRange: IntProgression) =
            jRange.map { j -> getCell(i, j) }

    override fun getColumn(iRange: IntProgression, j: Int) =
            iRange.map { i -> getCell(i, j) }

    override fun getAllCells() = cells.values.toList()
}

class GameBoardImpl<T>(width: Int) : GameBoard<T>, SquareBoardImpl(width) {
    private val data = hashMapOf<Cell, T?>()

    init {
        getAllCells().forEach { data[it] = null }
    }

    override operator fun get(i: Int, j: Int): T? = data[getCell(i, j)]

    override operator fun set(i: Int, j: Int, value: T?) {
        data[getCell(i, j)] = value
    }

    override operator fun get(cell: Cell): T? = data[cell]

    override operator fun set(cell: Cell, value: T?) {
        data[cell] = value
    }

    override fun contains(value: T): Boolean = data.values.contains(value)

    override fun filter(predicate: (T?) -> Boolean): Set<Cell> =
            getAllCells().filter { predicate(data[it]) }.toSet()

    override fun any(predicate: (T?) -> Boolean): Boolean =
            getAllCells().any { predicate(data[it]) }

    override fun all(predicate: (T?) -> Boolean): Boolean =
            getAllCells().all { predicate(data[it]) }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return getAllCells().find { predicate(data[it]) }
    }
}