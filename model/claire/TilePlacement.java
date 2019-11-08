package model.claire;

public class TilePlacement
{
	private int row;
	private int col;
	private Tile tile;
	
	public TilePlacement(int row, int col, Tile tile)
	{
		this.row = row;
		this.col = col;
		this.tile = tile;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public Tile getTile()
	{
		return tile;
	}
}
