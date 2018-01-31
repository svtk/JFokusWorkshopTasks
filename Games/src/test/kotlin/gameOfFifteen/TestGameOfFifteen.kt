package tests

import board.AbstractTestGame
import board.Direction
import board.Direction.*
import org.junit.Assert
import org.junit.Test

abstract class AbstractTestGameOfFifteen : AbstractTestGame() {
    @Test
    fun testInitialize() {
        Assert.assertEquals("Numbers from 1 to 15 should be on the board",
                (1..15).toSet(), values.filterNotNull().toSet())
        Assert.assertEquals("One empty value (null) should be on the board",
                1, values.filter { it == null }.size)
    }

    @Test
    fun testInitialPermutationIsOdd() {
        Assert.assertFalse("The initial permutation should have the same parity as (1, 2, ... 15, 0), so be odd",
                solution.countParity(values.map { it ?: 0 }))
    }

    private fun getCoordinatesOfEmptyCell() = run {
        val emptyCoordinates = indices.filter { game[it.first, it.second] == null }
        Assert.assertEquals("The board should contain one empty cell", 1, emptyCoordinates.size)
        emptyCoordinates.first()
    }

    @Test
    fun testEmptyCell() {
        getCoordinatesOfEmptyCell()
    }

    @Test
    fun testCanMove() {
        Assert.assertTrue("The move is always possible", game.canMove())
    }

    @Test
    fun testHasWon() {
        // initial permutation should be random, but with the right parity
        Assert.assertFalse("You win when the numbers are in order", game.hasWon())
    }

    @Test
    fun testHasProcessMoveUp() = testProcessMove(UP)

    @Test
    fun testHasProcessMoveDown() = testProcessMove(DOWN)

    @Test
    fun testHasProcessMoveRight() = testProcessMove(RIGHT)

    @Test
    fun testHasProcessMoveLeft() = testProcessMove(LEFT)

    private fun testProcessMove(direction: Direction) {
        for (i in 0..10) {
            setUp()
            testProcessOneMove(direction)
        }
    }

    private fun testProcessOneMove(direction: Direction) {
        val (oldI, oldJ) = getCoordinatesOfEmptyCell()
        game.processMove(direction)
        val (newI, newJ) = getCoordinatesOfEmptyCell()

        fun checkNeighbour(new: Int, old: Int) = when (direction) {
            UP, LEFT -> new == old + 1 || (old == 4 && old == new)
            DOWN, RIGHT -> new == old - 1 || (old == 1 && old == new)
        }

        val neighbourCheck = when (direction) {
            UP, DOWN -> checkNeighbour(newI, oldI)
            RIGHT, LEFT -> checkNeighbour(newJ, oldJ)
        }
        val message = buildString {
            append("The new empty cell should be the neighbour of the previous empty cell ")
            appendln("or be the same (if it's on the border).")
            appendln("Direction: $direction, previous empty cell: ($oldI, $oldJ), new empty cell: ($newI, $newJ).")
        }
        Assert.assertTrue(message, neighbourCheck)
    }
}