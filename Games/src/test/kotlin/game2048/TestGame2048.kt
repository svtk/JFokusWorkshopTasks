package tests

import board.AbstractTestGame
import board.Direction
import board.Direction.*
import org.junit.Assert
import org.junit.Test

abstract class AbstractTestGame2048: AbstractTestGame() {
    @Test
    fun testInitialize() {
        val nonNullValues = values.filterNotNull()
        Assert.assertEquals("At first two random values should be added to the game board", 2, nonNullValues.size)
        val possibleValues = setOf(2, 4)
        Assert.assertTrue("The values should be 2 or 4", nonNullValues.all { it in possibleValues })
    }

    @Test
    fun testCanMove() {
        Assert.assertTrue("The move is possible if the board isn't full", game.canMove())
    }

    @Test
    fun testCannotMove() {
        while (values.filterNotNull().size < 16) {
            for (direction in Direction.values()) {
                game.processMove(direction)
            }
        }
        Assert.assertFalse("The move isn't possible if the board is full", game.canMove())
    }

    @Test
    fun testHasWon() {
        Assert.assertFalse("You win when you get 2048 :)", game.hasWon())
    }

    @Test
    fun testHasProcessMoveUp() = testProcessMove(UP) { 1 to it }

    @Test
    fun testHasProcessMoveDown() = testProcessMove(DOWN) { 4 to it }

    @Test
    fun testHasProcessMoveRight() = testProcessMove(RIGHT) { it to 4 }

    @Test
    fun testHasProcessMoveLeft() = testProcessMove(LEFT) { it to 1 }

    private fun testProcessMove(direction: Direction, getRowElement: (Int) -> Pair<Int, Int>) {
        game.processMove(direction)
        val row = (1..4).map {
            val (i, j) = getRowElement(it)
            game[i, j]
        }
        // Two values are moved to the row, they might be merged if they are equal
        // One added value can be added to the same row
        // Thus this row might contain from 1 to 3 values
        Assert.assertTrue("After the first move majority of the values should be in one row",
                row.filterNotNull().size in setOf(1, 2, 3))
    }
}