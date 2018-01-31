package mastermind

import org.junit.Assert
import org.junit.Test

abstract class AbstractTestGenerator {
    abstract fun generateSecret(differentLetters: Boolean = false): String

    @Test(timeout = 200)
    fun testRepeatingLetters() {
        checkGeneratedCodes(false)
    }

    @Test(timeout = 200)
    fun testDifferentLetters() {
        checkGeneratedCodes(true)
    }

    private fun checkGeneratedCodes(differentLetters: Boolean) {
        val generated = mutableSetOf<String>()
        for (i in 1..100) {
            val secret = generateSecret(differentLetters)
            generated += secret
            checkOneCode(secret, differentLetters)
        }
        if (generated.size < 50) {
            throw AssertionError("generateSecret function should generate different strings")
        }
    }

    private fun checkOneCode(secret: String, differentLetters: Boolean) {
        Assert.assertEquals("Incorrect length of generated string: $secret",
                4, secret.length)
        val possibleLetters = "ABCDEF".toSet()
        Assert.assertTrue("Incorrect letters: $secret",
                secret.all { it in possibleLetters })
        if (differentLetters) {
            Assert.assertTrue("Letters are not different: $secret",
                    secret.toSet().size == secret.length)
        }
    }
}