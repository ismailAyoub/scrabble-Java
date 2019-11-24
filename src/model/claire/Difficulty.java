package model.claire;

/**
	The Difficulty constants representing the different difficulty settings of the game.
	These constants are used to "mark"/identify strings that represent valid words that can be placed on the game board.
	WORD is used to identify strings that represent words in the Scrabble Dictionary.
	EASY is used to identify strings that represent words that the game's AI is allowed to play while the game is in Easy mode.
	HARD is used to identify strings that represent words that the game's AI is allowed to play while the game is in Hard mode.
	NIGHTMARE is unused as of Scrabble v1.0.
*/
public enum Difficulty {
	WORD, EASY, HARD, NIGHTMARE;
}