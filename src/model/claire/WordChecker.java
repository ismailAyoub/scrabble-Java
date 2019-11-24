package model.claire;

import model.ismail.*;
import java.util.ArrayList;

/**
	The WordChecker is used to verify that the tiles placed on the scrabble board gui by the 
		player during their turn form one or more valid words according to the rules of Scrabble.
	@author Claire Campbell
*/
public class WordChecker
{
	private ScrabbleTrie trie;
	private ArrayList<TilePlacement> tilesPlaced;
	private GameBoard board;
	private boolean valid;
	private String alignment;
	
	
	public void addTiles(ArrayList<TilePlacement> tp)
	{
		tilesPlaced = tp;
	}
	public void addTilePlaced(TilePlacement t)
	{
		tilesPlaced.add(t);
	}
	public boolean validatePlacement() {
		return validatePlacement(tilesPlaced);
	}
	public ArrayList<ArrayList<TilePlacement> > validateTiles()
	{
		return validateTiles(tilesPlaced);
	}
	
	
	/**
		This constructor creates a new WordChecker object connected to the specified ScrabbleTrie and the specified GameBoard.
		@param trie The ScrabbleTrie that the WordChecker will use to validate the tiles placed on the gameboard and check that the tiles form a valid word.
		@param board The game board that the WordChecker will use to check the tiles placed by the user.
	*/
	public WordChecker(ScrabbleTrie trie, GameBoard board)
	{
		this.trie = trie;
		this.board = board;
		tilesPlaced = new ArrayList<TilePlacement>();
		valid = false;
		alignment = "invalid";
	}
	
	/**
		The reset() method resets the WordChecker in preparation for the next user's turn.
		It clears the list of TilePlacements (tilesPlaced) representing the Tiles placed on the board during the current turn,
			sets the valid field to false, and sets the current alignment to invalid.
	*/
	public void reset()
	{
		tilesPlaced.clear();
		valid = false;
		alignment = "invalid";
	}
	
	
	/**
		The readTilesFromBoard() method reads the current turn's tiles (as TilePlacements) from the game board.
		It fills the tilesPlaced ArrayList with the tiles (TilePlacements) placed during the current turn.
	*/
	public void readTilesFromBoard()
	{
		////todo
	}

	
	/**
		The validatePlacement() method checks whether the Tiles placed on the board during the current turn are placed in a valid
			arrangement, i.e. in a straight line either horizontally or vertically. 
			It does not check whether the tiles form a valid word on the board.
		The validatePlacement() method is intended to be called from the validateWord() method.
		@param tilesPlaced An ArrayList of TilePlacements representing the tiles placed on the board during the current turn and those tiles' positions on the board.
		@return The boolean value true if the tiles placed on the board during the current turn (the tilesPlaced parameter)
			are placed in a straight line, either horizontally or vertically, or if the user placed a single tile on the board.
			The boolean value false if the tiles placed on the board are not placed in a valid arrangement according to the Scrabble rules.
	*/	
	public boolean validatePlacement(ArrayList<TilePlacement> tilesPlaced)
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
			sortTilesPlaced();
			
			//Check for gaps...
			for (int i = tilesPlaced.get(0).getRow(); i <= tilesPlaced.get(tilesPlaced.size() - 1).getRow(); i++)
			{
				if (board.getTile(i, tilesPlaced.get(0).getCol()) == null)
				{
					valid = false;
					alignment = "invalid";
					return valid;
				}
			}
			valid = true;
		}
		else if (minRow == maxRow)
		{
			alignment = "horizontal";
			sortTilesPlaced();
			
			//Check for gaps...
			for (int j = tilesPlaced.get(0).getCol(); j <= tilesPlaced.get(tilesPlaced.size() - 1).getCol(); j++)
			{
				if (board.getTile(tilesPlaced.get(0).getRow(), j) == null)
				{
					valid = false;
					alignment = "invalid";
					return valid;
				}
			}
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
	
	
	/**
		The convertToString() method is a utility method used to convert an ArrayList of Tile objects to a String.
		@param array the ArrayList of Tiles to convert.
		@return A String object containing the letters/chars within the Tile objects in the ArrayList.
	*/
	public static String convertToString(ArrayList<Tile> array)
	{
		String s = "";
		for (Tile t : array)
		{
			s += t.getLetter();
		}
		return s;
	}
	
	
	/**
		The convertToString() method is a utility method used to convert an ArrayList of TilePlacement objects to a String.
		@param array the ArrayList of TilePlacements to convert.
		@return A String object containing the letters/chars within the TilePlacement objects' Tiles in the ArrayList.
	*/
	public static String convertTilePlacementsToString(ArrayList<TilePlacement> array)
	{
		String s = "";
		for (TilePlacement tp : array)
		{
			s += tp.getTile().getLetter();
		}
		return s;
	}
	
	
	
	
	public ArrayList<ArrayList<TilePlacement> > validateTiles(ArrayList<TilePlacement> tilesPlaced)
	{
		
		if (tilesPlaced.size() == 0)	//No words placed?
		{
			valid = true;
			return new ArrayList<ArrayList<TilePlacement> >();
		}
		
		validatePlacement(tilesPlaced);
		ArrayList<ArrayList<TilePlacement> > words = new ArrayList<ArrayList<TilePlacement> >();
		int arrIndex = 0;
		switch (alignment) 
		{
			///SINGLE
			case "single":
				//Tile temp;
				words.add(new ArrayList<TilePlacement>());
				words.add(new ArrayList<TilePlacement>());
				TilePlacement tilePlaced = tilesPlaced.get(0);
				words.get(0).add(tilePlaced);
				
				//Search for tiles upwards on board
				for (int i = tilePlaced.getRow() - 1; i >= 0; i--)
				{
					if (!board.getNode(i, tilePlaced.getCol()).isEmpty())
					{
						//If tiles adjacent above placed tile, add to the beginning of the first ArrayList of tiles
						words.get(0).add(0, new TilePlacement(i, tilePlaced.getCol(), board.getTile(i, tilePlaced.getCol())));
					}
					else
					{
						break;
					}
				}
				
				//Search for tiles downwards on board
				for (int i = tilePlaced.getRow() + 1; i <= 14; i++)
				{
					//If tiles extending downwards from placed tile on board, add to end of the first ArrayList of tiles
					if (!board.getNode(i, tilePlaced.getCol()).isEmpty())
					{
						//If tiles adjacent below placed tile, add to the end of the first ArrayList of tiles
						words.get(0).add(new TilePlacement(i, tilePlaced.getCol(), board.getTile(i, tilePlaced.getCol())));
					}
					else
					{
						break;
					}
				}
				
				
				
				words.get(1).add(tilePlaced);
				
				//Search to the left on board
				for (int j = tilePlaced.getCol() - 1; j >= 0; j--)
				{
					if (!board.getNode(tilePlaced.getRow(), j).isEmpty())
					{
						//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
						words.get(1).add(0, new TilePlacement(tilePlaced.getRow(), j, board.getTile(tilePlaced.getRow(), j)));
					}
					else
					{
						break;
					}
				}
				
				//Search to the right on board
				for (int j = tilePlaced.getCol() + 1; j <= 14; j++)
				{
					if (!board.getNode(tilePlaced.getRow(), j).isEmpty())
					{
						//If tiles adjacent to the right of placed tile, add to the end of the second ArrayList of tiles
						words.get(1).add(new TilePlacement(tilePlaced.getRow(), j, board.getTile(tilePlaced.getRow(), j)));
					}
					else
					{
						break;
					}
				}
				
				
				//Remove invalid words from the words arraylist. Set valid to appropriate value'=.
				for (int i = 0; i < words.size(); i++)
				{
					if (words.get(i).size() == 1)
					{
						words.get(i).clear();
						words.remove(i);
					}
					else if (trie.isWord(convertTilePlacementsToString(words.get(i)), Difficulty.WORD))
					{
						valid = true;
					}
					else
					{
						words.clear();
						valid = false;
					}
				}
				break;
			
			
			///HORIZONTAL
			case "horizontal":
				sortTilesPlaced();
				ArrayList<TilePlacement> temp = new ArrayList<TilePlacement>();	//Holds the word formed on the board as a series of Tile
				
				break;
				
			case "vertical":
				sortTilesPlaced();
				break;
		}
		
		return words;
	}
	
	/**
		sortTilesPlaced() sorts the TilePlacement objects in the tilesPlaced ArrayList ascending.
			It sorts by row or column depending on the current alignment.
			If alignment is vertical, this method sorts the tilesPlaced by row.
			If alignment is horizontal, this method sorts the tilesPlaced by column.
			This method is called by the validatePlacement() method.
	*/
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
			
			//debug
			/*
			for (TilePlacement ie : tilesPlaced)
			{
				System.out.print(ie.getTile().getLetter());
			}
			System.out.println("Sorting");
			*/
		}
		//System.out.println("Out of sort method" + "\n\n");
	}
	
	
	/**
		The getter method for the valid field.
		Should be called after the validateTiles() method.
		@return The boolean value true if the tiles placed on the board during the current turn	
			form one or more valid words; false if the tiles do not form a valid word.
			Returns the value in the valid field.
	*/
	public boolean isValid()
	{
		return valid;
	}
	
	
	public String getAlignment()
	{
		return alignment;
	}
	
	
	
	
	
	
	/*
		public ArrayList<ArrayList<Tile> > validateTiles(ArrayList<TilePlacement> tilesPlaced)
	{
		
		if (tilesPlaced.size() == 0)	//No words placed?
		{
			valid = true;
			return new ArrayList<ArrayList<Tile> >();
		}
		
		validatePlacement(tilesPlaced);
		ArrayList<ArrayList<Tile> > words = new ArrayList<ArrayList<Tile> >();
		int arrIndex = 0;
		switch (alignment) 
		{
			///SINGLE
			case "single":
				//Tile temp;
				words.add(new ArrayList<Tile>());
				words.add(new ArrayList<Tile>());
				TilePlacement tilePlaced = tilesPlaced.get(0);
				words.get(0).add(tilePlaced.getTile());
				
				//Search for tiles upwards on board
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
				
				//Search for tiles downwards on board
				for (int i = tilePlaced.getRow() + 1; i <= 14; i++)
				{
					//If tiles extending downwards from placed tile on board, add to end of the first ArrayList of tiles
					if (!board.getNode(i, tilePlaced.getCol()).isEmpty())
					{
						//If tiles adjacent below placed tile, add to the end of the first ArrayList of tiles
						words.get(0).add(board.getTile(i, tilePlaced.getCol()));
					}
					else
					{
						break;
					}
				}
				
				
				
				words.get(1).add(tilePlaced.getTile());
				
				//Search to the left on board
				for (int j = tilePlaced.getCol() - 1; j >= 0; j--)
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
				
				//Search to the right on board
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
				
				
				//Remove invalid words from the words arraylist. Set valid to appropriate value'=.
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
					else
					{
						words.clear();
						valid = false;
					}
				}
				break;
			
			
			///HORIZONTAL
			case "horizontal":
				sortTilesPlaced();
				ArrayList<Tile> temp = new ArrayList<Tile>();	//Holds the word formed on the board as a series of Tile
				
				
				//ArrayList<Integer> horizontalGaps = new ArrayList<Integer>();	//Stores column indexes of tiles on the board that the user placed tiles around in the current turn
				/*
				//Find the columns in the current "line" of tiles n the board that hold tiles from previous turns.
				//These columns will not hold tiles placed in the current turn, but the user USED these tiles in these columns in order to form a word on the board.
				for (int i = tilesPlaced.get(0).getCol(); i < tilesPlaced.get(tilesPlaced.size() - 1).getCol(); i++)
				{
					if (board.getTile(tilesPlaced.get(0).getRow(), i) != null)
					{
						horizontalGaps.add(i);
					}
				}
				
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
				/*if (!board.getNode(tilesPlaced.get(0).getRow(), j).isEmpty())
					{
						//If tiles adjacent to the left of placed tile, add to the beginning of the second ArrayList of tiles
						temp.add(0, board.getTile(tilesPlaced.get(0).getRow(), j));
					}
					else
					{
						break;
					}
				
				
				
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
				
				for (int j = tilesPlaced.get(0).getCol(); j <= tilesPlaced.get(tilesPlaced.size() - 1).getCol(); j++)
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
	
	*/
}