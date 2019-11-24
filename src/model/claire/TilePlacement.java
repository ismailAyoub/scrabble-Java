package model.claire;


/**
	The TilePlacement class represents a Scrabble tile along with its position on the game board.
	@author Claire Campbell
*/
public class TilePlacement
{
	private int row;
	private int col;
	private Tile tile;
	
	/**
		The constructor for the TilePlacement class. Creates a TilePlacement by setting the
			row, column, and tile.
		@param row The row that the Tile is placed in on the game board.
		@param col The column that the Tile is placed in on the game board.
		@param tile The tile that was placed on the board.
	*/
	public TilePlacement(int row, int col, Tile tile)
	{
		this.row = row;
		this.col = col;
		this.tile = tile;
	}
	
	/**
		The getter method for the row field. 
		@return The row field; the row that the Tile is placed in.
	*/	
	public int getRow()
	{
		return row;
	}
	
	/**
		The getter method for the col field.
		@return The col field; the column that the Tile is placed in.
	*/
	public int getCol()
	{
		return col;
	}
	
	/**
		The getter method for the tile field.
		@return The tile field; the Tile that was placed on the board.
	*/
	public Tile getTile()
	{
		return tile;
	}
}
