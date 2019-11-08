package model.claire;
import model.ismail.*;
import java.util.ArrayList;

public class WordChecker
{
	private ScrabbleTrie trie;
	private ArrayList<TilePlacement> tilesPlaced;
	private GameBoard board;
	private boolean valid;
	private int wordPoints;
	private String alignment;
	
	public WordChecker(ScrabbleTrie trie, GameBoard board)
	{
		this.trie = trie;
		this.board = board;
		tilesPlaced = new ArrayList<TilePlacement>();
		valid = false;
		wordPoints = 0;
	}
	
	public void reset()
	{
		tilesPlaced.clear();
		valid = false;
		wordPoints = 0;
	}
	
	public void addTilePlaced(TilePlacement tile)
	{
		tilesPlaced.add(tile);
	}
	
	public boolean validatePlacement()
	{
		int minCol = 15, minRow = 15, maxCol = 0, maxRow = 0;
		for (TilePlacement p : tilesPlaced)
		{
			if (p.getCol() < minCol) {
				minCol = p.getCol();
			}
			if (p.getRow() < minRow) {
				minRow = p.getRow();
			}
			if (p.getCol() > maxCol) {
				maxCol = p.getCol();
			}
			if (p.getRow() > maxRow) {
				maxRow = p.getRow();
			}
		}
		
		if (minCol == maxCol)
		{
			alignment = "vertical";
			valid = true;
		}
		else if (minRow == maxRow)
		{
			alignment = "horizontal";
			valid = true;
		}
		
		if (tilesPlaced.size() == 1)
		{
			alignment = "single";
			valid = true;
		}
		else if (alignment.equals("horizontal") == false && alignment.equals("vertical") == false)
		{
			alignment = "invalid";
			valid = false;
		}
		return valid;
	}
	
	public static String convertToString(ArrayList<Tile> array)
	{
		String s = "";
		for (Tile t : array)
		{
			s += t.getLetter();
		}
		return s;
	}
	
	public ArrayList<ArrayList<Tile> > validateWord()
	{
		
		if (tilesPlaced.size() == 0)	//No words placed?
		{
			valid = true;
			wordPoints = 0;
		}
		
		validatePlacement();
		ArrayList<ArrayList<Tile> > words = new ArrayList<ArrayList<Tile> >();
		int arrIndex = 0;
		switch (alignment) 
		{
			case "single":
				Tile temp;
				words.add(new ArrayList<Tile>());
				words.add(new ArrayList<Tile>());
				TilePlacement tilePlaced = tilesPlaced.get(0);
				words.get(0).add(tilePlaced.getTile());
				for (int i = tilePlaced.getRow() - 1; i >= 0; i--)
				{
					if (!board.getNode(i, tilePlaced.getCol()).isEmpty())
					{
						//If tiles adjacent above placed tile, add to the beginning of the first ArrayList of tiles
						words.get(0).add(0, board.getTile(i, tilePlaced.getCol()));
					}
					else
					{
						break;
					}
				}
				for (int i = tilePlaced.getRow() + 1; i <= 14; i++)
				{
					//If tiles extending downwards from placed tile on board, add to end of the first ArrayList of tiles
					if (!board.getNode(i, tilePlaced.getCol()).isEmpty())
					{
						//If tiles adjacent above placed tile, add to the beginning of the first ArrayList of tiles
						words.get(0).add(0, board.getTile(i, tilePlaced.getCol()));
					}
					else
					{
						break;
					}
				}
				
				words.get(1).add(tilePlaced.getTile());
				for (int j = tilePlaced.getCol() - 1; j >=0; j--)
				{
					if (!board.getNode(tilePlaced.getRow(), j).isEmpty())
					{
						//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
						words.get(1).add(0, board.getTile(tilePlaced.getRow(), j));
					}
					else
					{
						break;
					}
				}
				for (int j = tilePlaced.getCol() + 1; j <= 14; j++)
				{
					if (!board.getNode(tilePlaced.getRow(), j).isEmpty())
					{
						//If tiles adjacent to the right of placed tile, add to the end of the second ArrayList of tiles
						words.get(1).add(board.getTile(tilePlaced.getRow(), j));
					}
					else
					{
						break;
					}
				}
				
				if (words.get(1).size() == 1)
				{
					words.get(1).clear();
				}
				if (trie.isWord(convertToString(words.get(0)), Difficulty.WORD))
				{
					valid = true;
					return words;
				}
				
				
				break;
			case "horizontal":
				
				break;
			case "vertical":
				
				break;
		}
		
		return new ArrayList<ArrayList<Tile> >();
	}
	
	public boolean isValid()
	{
		return valid;
	}
	
	public int getPoints()
	{
		return wordPoints;
	}
	
	public String getAlignment()
	{
		return alignment;
	}
}