package tests

import board.Cell
import board.Direction
import board.SquareBoard
import org.junit.Assert
import org.junit.Test
import kotlin.comparisons.compareBy
import kotlin.comparisons.thenBy

abstract class AbstractTestSquareBoard {
    abstract fun createSquareBoard(width: Int): SquareBoard

    fun Cell?.print() = if (this != null) "($i, $j)" else ""

    fun Collection<Cell>.print() = joinToString { it.print() }

    @Test
    fun testAllCells() {
        val board = createSquareBoard(2)
        val cells = board.getAllCells().sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })
        Assert.assertEquals("(1, 1), (1, 2), (2, 1), (2, 2)", cells.print())
    }

    @Test
    fun testCell() {
        val board = createSquareBoard(4)
        val cell = board.getCellOrNull(1, 3)
        Assert.assertEquals(1, cell?.i)
        Assert.assertEquals(3, cell?.j)
    }

    @Test
    fun testNoCell() {
        val board = createSquareBoard(4)
        val cell = board.getCellOrNull(10, 10)
        Assert.assertEquals(null, cell)
    }

    @Test
    fun testRow() {
        val board = createSquareBoard(4)
        val row = board.getRow(1, 1..2)
        Assert.assertEquals("(1, 1), (1, 2)", row.print())
    }

    @Test
    fun testColumn() {
        val board = createSquareBoard(4)
        val row = board.getColumn(1..2, 3)
        Assert.assertEquals("(1, 3), (2, 3)", row.print())
    }

    @Test
    fun testRowReversedRange() {
        val board = createSquareBoard(4)
        val row = board.getRow(1, 4 downTo 1)
        Assert.assertEquals("(1, 4), (1, 3), (1, 2), (1, 1)", row.print())
    }

    @Test
    fun testColumnReversedRange() {
        val board = createSquareBoard(4)
        val row = board.getColumn(2 downTo 1, 1)
        Assert.assertEquals("(2, 1), (1, 1)", row.print())
    }

    @Test
    fun testNeighbour() {
        with (createSquareBoard(4)) {
            val cell = getCellOrNull(2, 2)
            Assert.assertNotNull(cell)
            Assert.assertEquals("(1, 2)", cell!!.getNeighbour(Direction.UP).print())
            Assert.assertEquals("(3, 2)", cell.getNeighbour(Direction.DOWN).print())
            Assert.assertEquals("(2, 1)", cell.getNeighbour(Direction.LEFT).print())
            Assert.assertEquals("(2, 3)", cell.getNeighbour(Direction.RIGHT).print())
        }
    }

    @Test
    fun testNullableNeighbour() {
        with (createSquareBoard(4)) {
            val cell = getCellOrNull(1, 1)
            Assert.assertNotNull(cell)
            Assert.assertEquals(null, cell!!.getNeighbour(Direction.UP))
            Assert.assertEquals(null, cell.getNeighbour(Direction.LEFT))
            Assert.assertEquals("(2, 1)", cell.getNeighbour(Direction.DOWN).print())
            Assert.assertEquals("(1, 2)", cell.getNeighbour(Direction.RIGHT).print())
        }
    }
}
