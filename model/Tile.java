package model;

public class Tile
{
	private int points;
	private char letter;
	
	public Tile(int points, char letter)
	{
		this.points = points;
		this.letter = letter;
	}
	
	public char getLetter()
	{
		return letter;
	}
	
	public int getPoints()
	{
		return points;
	}
}