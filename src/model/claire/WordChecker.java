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
		alignment = "no";
	}
	
	public void reset()
	{
		tilesPlaced.clear();
		valid = false;
		wordPoints = 0;
		alignment = "invalid";
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
				//Tile temp;
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
				
				for (int i = 0; i < words.size(); i++)
				{
					if (words.get(i).size() == 1)
					{
						words.get(i).clear();
						words.remove(i);
					}
					else if (trie.isWord(convertToString(words.get(i)), Difficulty.WORD))
					{
						valid = true;
					}
				}
				break;
				
			case "horizontal":
				sortTilesPlaced();
				ArrayList<Integer> horizontalGaps = new ArrayList<Integer>();	//Stores column indexes of tiles on the board that the user placed tiles around in the current turn
				
				//Find the columns in the current "line" of tiles n the board that hold tiles from previous turns.
				//These columns will not hold tiles placed in the current turn, but the user USED these tiles in these columns in order to form a word on the board.
				for (int i = tilesPlaced.get(0).getCol(); i < tilesPlaced.get(tilesPlaced.size() - 1).getCol(); i++)
				{
					if (board.getTile(tilesPlaced.get(0).getRow(), i) != null)
					{
						horizontalGaps.add(i);
					}
				}
				
				
				ArrayList<Tile> temp = new ArrayList<Tile>();	//Holds the word formed on the board as a series of Tiles
				
				//(gap = tile not placed in current turn in this space, but the space holds a tile from a previous turn)
				//Merge the tilesPlaced and the tiles in the horizontal gaps on the board into the word formed by the user
				int tilesPlacedIndex = 0;
				for (int j = tilesPlaced.get(0).getCol(); j < tilesPlaced.get(tilesPlaced.size() - 1).getCol(); j++)
				{
					if (horizontalGaps.contains(j))
					{
						temp.add(board.getTile(tilesPlaced.get(0).getRow(), j));
					}
					else
					{
						temp.add(tilesPlaced.get(tilesPlacedIndex).getTile());
						tilesPlaced++;
					}
				}
				/*
				for (int i = 0 ; i < tilesPlaced.size() + horizontalGaps.size(); )
				{
					if (horizontalGaps.contains(i))
					{
						temp.add(board.getTile(tilesPlaced.get(0).getRow(), horizontalGaps.get(hgapIndex)));
						hgapIndex++;
					}
					else
					{
						temp.add(tilesPlaced.get(i).getTile());
						i++;
					}
				}
				*/
				
				//Search to the right of the tiles placed to see if the tiles play off adjacent tiles to the left previously placed on the board.
				for (int j = tilesPlaced.get(0).getCol() - 1; j >= 0; j--)
				{
					if (!board.getNode(tilesPlaced.get(0).getRow(), j).isEmpty())
					{
						//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
						temp.add(0, board.getTile(tilesPlaced.get(0).getRow(), j));
					}
					else
					{
						break;
					}
				}
				
				
				for (int j = tilesPlaced.get(tilesPlaced.size() - 1).getCol() + 1; j <= 14; j++)
				{
					if (!board.getNode(tilesPlaced.get(0).getRow(), j).isEmpty())
					{
						//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
						temp.add(0, board.getTile(tilesPlaced.get(0).getRow(), j));
					}
					else
					{
						break;
					}
				}
				
				words.add(temp);
				
				for (int j = 0; j < tilesPlaced.size(); j++)
				{
					temp = new ArrayList<Tile>();
					System.out.println("Checking: " + tilesPlaced.get(j).getTile().getLetter());
					temp.add(tilesPlaced.get(j).getTile());
					Tile dummy = board.getTile(tilesPlaced.get(j).getRow() - 1, tilesPlaced.get(j).getCol());
					System.out.println("Above: " + (dummy == null ? "No tile" : dummy.getLetter()));
					for (int i = tilesPlaced.get(j).getRow() - 1; i >= 0; i--)
					{
						//If row i, col j on board is NOT empty...
						System.out.println("Looking at tile " + i + ", " + tilesPlaced.get(j).getCol());
						System.out.println(board.getTile(i, tilesPlaced.get(j).getCol()));
						if (board.getTile(i, tilesPlaced.get(j).getCol()) != null)
						{
							System.out.println("Above: " + board.getNode(i, tilesPlaced.get(j).getCol()).getTile().getLetter());
							//If tiles adjacent to the top of placed tile, add to the beginning of the new ArrayList of tiles
							temp.add(0, board.getTile(i, tilesPlaced.get(j).getCol()));
						}
						else
						{
							break;
						}
					}
					for (int i = tilesPlaced.get(j).getRow() + 1; i <= 14; i++)
					{
						if (!board.getNode(i, tilesPlaced.get(j).getCol()).isEmpty())
						{
							//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
							temp.add(board.getTile(i, tilesPlaced.get(j).getCol()));
						}
						else
						{
							break;
						}
					}
					if (temp.size() > 1)
					{
						words.add(temp);
					}
				}
				
				break;
				
			case "vertical":
				//sortTilesPlaced();
				break;
		}
		
		return words;
	}
	
	public void dumpTilesToBoard()
	{
		for (TilePlacement tp : tilesPlaced)
		{
			board.setTile(tp.getRow(), tp.getCol(), tp.getTile());
		}
	}
	
	private void sortTilesPlaced()
	{
		TilePlacement temp;
		int min = 0;
		int minIndex = 0;
		for (int i = 0; i < tilesPlaced.size(); i++)
		{
			
			minIndex = i;
			if (alignment.equals("vertical"))
			{
				min = tilesPlaced.get(i).getRow();
			}
			else if (alignment.equals("horizontal"))
			{
				min = tilesPlaced.get(i).getCol();
			}
			
			for (int i2 = i; i2 < tilesPlaced.size(); i2++)
			{
				if (alignment.equals("vertical"))
				{
					if (tilesPlaced.get(i2).getRow() < min)
					{
						minIndex = i2;
						min = tilesPlaced.get(i2).getRow();
					}
				}
				else if (alignment.equals("horizontal"))
				{
					if (tilesPlaced.get(i2).getCol() < min)
					{
						minIndex = i2;
						min = tilesPlaced.get(i2).getCol();
					}
				}
			}
			temp = tilesPlaced.get(i);
			tilesPlaced.set(i, tilesPlaced.get(minIndex));
			tilesPlaced.set(minIndex, temp);
			
			for (TilePlacement ie : tilesPlaced)
			{
				System.out.print(ie.getTile().getLetter());
			}
			System.out.println("Sorting");
		}
		System.out.println("Out of sort method" + "\n\n");
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