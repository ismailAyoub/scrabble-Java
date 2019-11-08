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
	
	
	public void validateWord()
	{
		if (tilesPlaced.size() == 0)	//No words placed?
		{
			valid = true;
			wordPoints = 0;
		}
		
		validatePlacement();
		switch (alignment) 
		{
			case "single":
				
				break;
			case "horizontal":
				
				break;
			case "vertical":
				
				break;
		}
	}
	
	public boolean isValid()
	{
		return valid;
	}
	
	public int getPoints()
	{
		return wordPoints;
	}
}