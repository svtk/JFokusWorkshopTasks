package tests

import board.GameBoard

abstract class AbstractTestGameWithSmallNumbers {
    abstract fun <T> createGameBoard(width: Int): GameBoard<T>

    val width = 4

    fun String.toValues(): List<List<Int>> = trim().split(' ').map { row -> row.map { ch -> (ch - '0').toInt() } }

    fun valuesToString(getElement: (Int, Int) -> Int?) = buildString {
        for (i in 1..width) {
            for (j in 1..width) {
                append(getElement(i, j) ?: 0)
            }
            append(' ')
        }
    }.trim()

    fun createBoard(input: String): GameBoard<Int?> {
        val data = input.toValues()
        val board = createGameBoard<Int?>(width)
        for ((i, j) in board.getAllCells()) {
            val ch = data[i - 1][j - 1]
            if (ch != 0) {
                board[i, j] = ch
            }
        }
        return board
    }

    fun GameBoard<Int?>.print() = valuesToString { i, j -> this[i, j] }
}