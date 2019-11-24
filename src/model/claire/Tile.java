package model.claire;

/**
	The Tile class represents a Scrabble Tile.
	@author Claire Campbell
*/
public class Tile
{
	private int points;
	private char letter;
	
	/**
		The constructor for the Tile. Assigns the Tile a point value and a letter.
		@param points The point value for the Tile.
		@param letter The letter that the Tile represents.
	*/
	public Tile(int points, char letter)
	{
		this.points = points;
		this.letter = letter;
	}
	
	/**
		The getter method for the letter field.
		@return The letter that the tile represents, as a char.
	*/
	public char getLetter()
	{
		return letter;
	}
	
	/**
		The getter method for the points field.
		@return The number of points that the tile is worth, as an int.
	*/
	public int getPoints()
	{
		return points;
	}
}