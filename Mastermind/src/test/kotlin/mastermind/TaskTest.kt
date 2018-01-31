package taskTest

import mastermind.AbstractTestChecker
import mastermind.AbstractTestGenerator
import mastermind.AbstractTestEvaluator

class TestTaskGenerator : AbstractTestGenerator() {
    override fun generateSecret(differentLetters: Boolean) = task.generateSecret(differentLetters)
}

class TestTaskEvaluator : AbstractTestEvaluator() {
    override fun evaluateGuess(secret: String, guess: String) = task.evaluateGuess(secret, guess)
}

class TestTaskChecker : AbstractTestChecker() {
    override fun verifyGuess(guess: String): Boolean = task.verifyGuess(guess)
}
