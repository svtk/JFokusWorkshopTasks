package task

import board.*
import tests.*

class TaskTestGameBoard : AbstractTestGameBoard() {
    override fun <T> createGameBoard(width: Int): GameBoard<T> = task.createGameBoard(width)
}

class TaskTestSquareBoard : AbstractTestSquareBoard() {
    override fun createSquareBoard(width: Int): SquareBoard = task.createSquareBoard(width)
}

class TaskTestMoveAndMergeEqual : AbstractTestMoveAndMergeValues() {
    override fun <T : Any> moveAndMergeEqual(list: List<T?>, double: (T) -> T): List<T> =
            list.moveAndMergeEqual(double)
}

class TaskTestAddRandomValue : AbstractTestAddRandomValue() {
    override fun <T> createGameBoard(width: Int): GameBoard<T> = task.createGameBoard(width)

    override fun addRandomValue(board: GameBoard<Int?>) = board.addRandomValue()
}

class TaskTestMoveValuesInRowOrColumn: AbstractTestMoveValuesInRowOrColumn() {
    override fun <T> createGameBoard(width: Int): GameBoard<T> = task.createGameBoard(width)

    override fun moveValuesInRowOrColumn(gameBoard: GameBoard<Int?>, rowOrColumn: List<Cell>): Boolean =
            gameBoard.moveValuesInRowOrColumn(rowOrColumn)
}

class TaskTestMoveValues: AbstractTestMoveValues() {
    override fun <T> createGameBoard(width: Int): GameBoard<T> = task.createGameBoard(width)

    override fun moveValues(board: GameBoard<Int?>, direction: Direction): Boolean =
            board.moveValues(direction)
}

class TaskTestGame2048: AbstractTestGame2048() {
    override fun newGame(): Game = task.newGame2048()
}

class TaskTestCountParity: AbstractTestCountParity() {
    override fun countParity(permutation: List<Int>): Boolean = task.countParity(permutation)
}

class TaskTestGameOfFifteen : AbstractTestGameOfFifteen() {
    override fun newGame(): Game = task.newGameOfFifteen()
}