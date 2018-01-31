package task

import mastermind.Evaluation

/*
Implement the function that evaluates each guess.
It returns:
- the number of positions (how many letters are guessed correctly
with their positions);
- the number of letters (how many letters are guessed correctly
ignoring their positions).
 */
fun evaluateGuess(secret: String, guess: String): Evaluation {
    // TODO
    return Evaluation(0, 0)
}

/*
Implement the function that checks that the user input is a string
consisting of 4 letters from 'A', 'B', 'C', 'D', 'E' or 'F'.
 */
fun verifyGuess(guess: String): Boolean {
    // TODO
    return true
}

/*
Implement the function that generates a secret String consisting of 
4 characters 'A', 'B', 'C', 'D', 'E' or 'F'.
The `differentCharacters` parameter specifies whether all the characters should 
be different in this String.
 */
fun generateSecret(
        differentCharacters: Boolean = true
): String = "ABCD"