package mastermind

import task.evaluateGuess
import task.generateSecret
import task.verifyGuess

/*
This program plays mastermind game with the user:
https://en.wikipedia.org/wiki/Mastermind_(board_game).
The computer generates the secret code consisting of 4 letters
among 'A', 'B', 'C', 'D', 'E' and 'F', for instance, "BCED".
The task of the player is to guess the right code with
the minimum amount of attempts.
On each attempt the computer gives:
 - the number of positions (how many letters are guessed correctly
 with their positions);
 - the number of letters (how many letters are guessed correctly
 ignoring their positions).
For example, if the secret is "BCED" and guess is "BACE"
the computer should give the result:
 - positions: 1 (B)
 - letters: 2 (AE)
The game ends when the player guesses the secret.

You task is implement the function that evaluates each guess (computes
the number of letters and positions), and a couple of other utility functions
(the one that checks errors in input and generates a secret).
 */
fun main(args: Array<String>) {
    playMastermind()
}

data class Evaluation(val positions: Int, val letters: Int) {
    val complete: Boolean
        get() = positions == 4

}

fun playMastermind(
        secret: String = generateSecret()
) {
    var evaluation: Evaluation
    do {
        val guess = guess()
        if (!verifyGuess(guess)) {
            incorrectInput(guess)
            return
        }
        evaluation = evaluateGuess(secret, guess)
        receiveEvaluation(evaluation.complete, evaluation.positions, evaluation.letters)
    } while (!evaluation.complete)
}

fun guess(): String {
    print("Your guess: ")
    return readLine()!!
}

fun receiveEvaluation(complete: Boolean, positions: Int, letters: Int) {
    if (complete) {
        println("You are correct!")
    } else {
        println("Positions: $positions; letters: $letters.")
    }
}

fun incorrectInput(guess: String) {
    println("Incorrect input: $guess. It should consist of 4 letters (A, B, C, D, E, F).")
}