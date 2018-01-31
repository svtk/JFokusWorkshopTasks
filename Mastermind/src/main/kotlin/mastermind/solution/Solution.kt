package solution

import mastermind.Evaluation
import java.util.*

val ALPHABET = "ABCDEF"
val CODE_LENGTH = 4

/*
Implement the function that evaluates each guess.
It returns:
- the number of positions (how many letters are guessed correctly
with their positions);
- the number of letters (how many letters are guessed correctly
ignoring their positions).
 */
fun evaluateGuess(secret: String, guess: String): Evaluation {
    val positions = secret.zip(guess).count { it.first == it.second }

    val commonDigits = ALPHABET.sumBy { ch -> Math.min(secret.count { it == ch }, guess.count { it == ch }) }
    return Evaluation(positions, commonDigits - positions)
}

/*
Implement the function that checks that the user input is a string
consisting of 4 letters from 'A', 'B', 'C', 'D', 'E' or 'F'.
 */
fun verifyGuess(guess: String): Boolean {
    val possibleLetters = "ABCDEF".toSet()
    return guess.length == 4 && guess.all { it in possibleLetters }
}

/*
Implement the function that generates a secret String consisting of
4 characters 'A', 'B', 'C', 'D', 'E' or 'F'.
The `differentCharacters` parameter specifies whether all the characters should
be different in this String.
 */
fun generateSecret(differentCharacters: Boolean = false): String {
    val chars = "ABCDEF".toMutableList()
    val random = Random()
    return buildString {
        for (i in 1..CODE_LENGTH) {
            val letter = chars[random.nextInt(chars.size)]
            append(letter)
            if (differentCharacters) {
                chars.remove(letter)
            }
        }
    }
}