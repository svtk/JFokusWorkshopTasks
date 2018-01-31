package tests

import board.Direction
import board.Direction.DOWN
import board.Direction.RIGHT
import board.GameBoard
import board.reversed
import org.junit.Assert
import org.junit.Test

abstract class AbstractTestMoveValues : AbstractTestGameWithSmallNumbers() {
    abstract fun moveValues(board: GameBoard<Int?>, direction: Direction): Boolean

    @Test
    fun testSimpleMove() = testAllDirections("0000 0000 0200 0000", "0000 0000 0002 0000")

    @Test
    fun testNoMove() = testAllDirections("0000 0000 0000 2424", "0000 0000 0000 2424", move = false)

    @Test
    fun testSeveralMoves() = testAllDirections("2000 0200 0020 0002", "0002 0002 0002 0002")

    @Test
    fun testMovesInSomeRows() = testAllDirections("2000 0000 0020 2424", "0002 0000 0002 2424")

    @Test
    fun testMoveAndMerge() = testAllDirections("2020 0202 0020 0002", "0004 0004 0002 0002")

    fun testAllDirections(input: String, expected: String, move: Boolean = true) {
        testRegularAndReversedDirections(RIGHT, input, expected, move)
        testRegularAndReversedDirections(DOWN, input.turn(), expected.turn(), move)
    }

    fun testRegularAndReversedDirections(direction: Direction, input: String, expected: String, move: Boolean) {
        testMove(direction, input, expected, move)
        testMove(direction.reversed(), input.reversed(), expected.reversed(), move)
    }

    fun String.turn(): String {
        val values = toValues()
        return valuesToString { i, j -> values[j - 1][i - 1] }
    }

    fun testMove(direction: Direction, input: String, expected: String, expectedMove: Boolean) {
        val board = createBoard(input)
        val actualMove = moveValues(board, direction)
        val result = board.print()
        Assert.assertEquals("Incorrect move to $direction.\nInput:    $input", expected, result)
        Assert.assertEquals("The 'moveValues' method returns incorrect result", expectedMove, actualMove)
    }
}