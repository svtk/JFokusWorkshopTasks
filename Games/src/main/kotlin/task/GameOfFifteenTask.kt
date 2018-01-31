package task

import board.Direction
import board.GameBoard
import board.reversed
import board.Game

/*
This function should return the parity of the permutation.
true - the permutation is even
false - the permutation is odd
https://en.wikipedia.org/wiki/Parity_of_a_permutation

If the game of fifteen is started with the wrong parity, you can't get the correct result
  (numbers sorted in the right order, empty cell at last).
Thus the initial permutation should be correct.
 */
fun countParity(permutation: List<Int>): Boolean {
    TODO()
}

/*
Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
When you finish, you can play the game by executing 'PlayGameOfFifteen' (or choosing the corresponding run configuration).
*/
fun newGameOfFifteen(): Game = TODO()