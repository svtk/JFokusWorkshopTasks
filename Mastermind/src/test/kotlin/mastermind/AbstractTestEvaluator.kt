package mastermind

import org.junit.Assert
import org.junit.Test

abstract class AbstractTestMastermind() {
    abstract fun evaluateGuess(secret: String, guess: String): Evaluation

    fun testEvaluation(secret: String, guess: String, positions: Int, letters: Int) {
        val evaluation = evaluateGuess(secret, guess)
        Assert.assertEquals("Incorrect number of positions for secret '$secret' and guess '$guess': ",
                positions, evaluation.positions)
        Assert.assertEquals("Incorrect number of letters for secret '$secret' and guess '$guess': ",
                letters, evaluation.letters)
    }
}

abstract class TestSimpleMastermind : AbstractTestMastermind() {
    @Test(timeout = 200)
    fun testPlay1() {
        testEvaluation("FBAE", "ABCD", 1, 1)
        testEvaluation("FBAE", "AFDC", 0, 2)
        testEvaluation("FBAE", "CBAE", 3, 0)
        testEvaluation("FBAE", "CBFE", 2, 1)
        testEvaluation("FBAE", "FBAE", 4, 0)
    }

    @Test(timeout = 200)
    fun testPlay2() {
        testEvaluation("EBAC", "ABCD", 1, 2)
        testEvaluation("EBAC", "AFCB", 0, 3)
        testEvaluation("EBAC", "CBDF", 1, 1)
        testEvaluation("EBAC", "EBAC", 4, 0)
    }

    @Test(timeout = 200)
    fun testEqual() = testEvaluation("ABCD", "ABCD", 4, 0)

    @Test(timeout = 200)
    fun testSwap() = testEvaluation("ABCD", "ABDC", 2, 2)

    @Test(timeout = 200)
    fun test3Positions() = testEvaluation("ABCD", "ABCF", 3, 0)

    @Test(timeout = 200)
    fun test3Letters() = testEvaluation("DAEF", "FECA", 0, 3)

    @Test(timeout = 200)
    fun testTaskSample() = testEvaluation("ACEB", "BCDF", 1, 1)
}

abstract class AbstractTestEvaluator : TestSimpleMastermind() {
    @Test(timeout = 200)
    fun testSample() = testEvaluation("AAAF", "ABCA", 1, 1)

    @Test(timeout = 200)
    fun testReversedSample() = testEvaluation("ABCA", "AAAF", 1, 1)

    @Test(timeout = 200)
    fun testSecretLetter1() = testEvaluation("AABC", "DEFA", 0, 1)

    @Test(timeout = 200)
    fun testSecretLetter2() = testEvaluation("EDEB", "CBFE", 0, 2)

    @Test(timeout = 200)
    fun testSecretLetter3() = testEvaluation("CFDF", "FCCD", 0, 3)


    @Test(timeout = 200)
    fun testSecretPosition1() = testEvaluation("AABC", "AEFD", 1, 0)

    @Test(timeout = 200)
    fun testSecretPosition2() = testEvaluation("DCFC", "ABEC", 1, 0)

    @Test(timeout = 200)
    fun testSecretPosition3() = testEvaluation("FDCD", "FBAD", 2, 0)

    @Test(timeout = 200)
    fun testGuess() = testEvaluation("DEFA", "AABC", 0, 1)

    @Test(timeout = 200)
    fun testBothOneLetter() = testEvaluation("DAAE", "AABC", 1, 1)

    @Test(timeout = 200)
    fun testBothSeveralLetters1() = testEvaluation("BBDC", "DFBB", 0, 3)

    @Test(timeout = 200)
    fun testBothSeveralLetters2() = testEvaluation("DBFF", "FFDD", 0, 3)

    @Test(timeout = 200)
    fun testBothOnePosition1() = testEvaluation("BDAD", "AAAE", 1, 0)

    @Test(timeout = 200)
    fun testBothOnePosition2() = testEvaluation("FDDB", "CABB", 1, 0)

    @Test(timeout = 200)
    fun testBothPositions() = testEvaluation("BDBC", "DDFC", 2, 0)

    @Test(timeout = 200)
    fun testBothLettersAndPositions() = testEvaluation("ECDE", "CEEE", 1, 2)

    @Test(timeout = 200)
    fun testCrazySolutionWithMap() = testEvaluation("BCDA", "AFEA", 1, 0)
}