package mastermind

import org.junit.Assert
import org.junit.Test

abstract class AbstractTestChecker {
    abstract fun verifyGuess(guess: String): Boolean

    @Test
    fun testNoErrors1() = testNoErrors("ABCD")

    @Test
    fun testNoErrors2() = testNoErrors("CDAE")

    @Test
    fun testNoErrors3() = testNoErrors("CEFF")

    @Test
    fun testHasErrors1() = testHasErrors("C")

    @Test
    fun testHasErrors2() = testHasErrors("ABCDEFLKJKHGE")

    @Test
    fun testHasErrors3() = testHasErrors("111")

    @Test
    fun testHasErrors4() = testHasErrors("A!")

    private fun testNoErrors(guess: String) {
        Assert.assertTrue("Input $guess should be correct", verifyGuess(guess))
    }

    private fun testHasErrors(guess: String) {
        Assert.assertFalse("Input $guess shouldn't be correct", verifyGuess(guess))
    }

}