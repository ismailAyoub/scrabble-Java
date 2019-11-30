package model;

import model.ismail.*;
import model.claire.*;
import model.temp.*; 	///remove when junaid completes his files.
//import model.junaid.*;	///uncomment when junaid completes his files.
import java.util.ArrayList;
import java.io.*;

public class GameState 
{
	private GameBoard board;
	private Difficulty difficulty;
	private boolean tutorialTooltipsEnabled;
	private TileBag tileBag;
	private ArrayList<Player> players;
	private int currentPlayer;
	
	//Do not save these fields in the save game files!!///////
	private ScrabbleTrie scrabbleTrie;
	private WordChecker wordChecker;
	boolean currentTurnValid;
	//////////////////////////////////////////////////////////
	
	
	public GameState(GameBoard gb) throws IOException
	{
		this.board = gb;
		difficulty = Difficulty.EASY;
		tutorialTooltipsEnabled = false;
		
		//Set up ScrabbleTrie 
		scrabbleTrie = new ScrabbleTrie();
		//System.out.println((new File("Scrabble_EASY.txt")).getAbsolutePath());
		scrabbleTrie.initialize(
			"CollinsScrabbleWords2019.txt",
			"Scrabble_EASY.txt",
			"Scrabble_HARD.txt"
		);
		
		tileBag = new TileBag();
		tileBag.init(new File("tile_distribution_default.txt"));
		tileBag.printTileBag();
		
		currentTurnValid = false;
		currentPlayer = 0;
	}
	
	
	
	/**
	
	*/
	public void addAIPlayer()
	{
		
	}
	
	
	
	/**
		
	*/
	public void addPlayer(String s, int points)
	{
		
	}
	
	
	
	/**
		Gets the player whose turn it is.
		@return The Player object representing the player whose turn it is.
		@author Claire Campbell
	*/
	public Player getCurrentPlayer()
	{
		return players.get(currentPlayer);
	}
	
	
	
	/**
		The finalize() method should be called after the current player is done placing tiles on the game board.
			It  validates the tiles placed (represented by the tilesPlaced parameter) on the GUI game board during the current turn.
			If the tiles were placed in a valid arrangement on the board and form one or more valid words, the finalize()
			method scores those words and adds the points to the current player's score.
		@param tilesPlaced The tiles placed on the board, as an ArrayList of TilePlacements.
		@param tileRack The tile rack from the GUI, which the player removed tiles from during their turn.
			The tile rack is an array of Tile objects, with null elements representing tiles that have been 
			removed and placed on the game board.
	*/
	public void finalize(ArrayList<TilePlacement> tilesPlaced, Tile[] tileRack)
	{
		for (int i = 0; i < tilesPlaced.size(); i++)
		{
			board.setTile(tilesPlaced.get(i).getRow(), tilesPlaced.get(i).getCol(), tilesPlaced.get(i).getTile());
		}
		
		wordChecker.readTilesFromBoard();
		ArrayList<ArrayList<TilePlacement> > words = wordChecker.validateTiles();
		
		if (words.size() == 0)
		{
			board.rollbackTurn();
			wordChecker.reset();
			currentTurnValid = false;
		}
		else
		{
			board.finalizeTurn();
			///todo: Score the tiles in "words", add the score to the current player.
			
			
			for (int i = 0; i < tilesPlaced.size(); i++)
			{
				getCurrentPlayer().removeTile(tilesPlaced.get(i).getTile().getLetter());
			}
		}
	}
	
	
	
	/**
		Draws random tiles from the tile bag, and places these tiles in the GUI gameboard's tile rack
			as well as the current Player's tile rack to replace the tiles that they placed on the board
			during the current turn.
		@param tileRack The array representing the tile rack in the GUI, which the player removed tiles from 
			during their turn.
		@author Claire Campbell
	*/
	public void drawTiles(Tile[] tileRack)
	{
		Tile drawn = null;
		for (int i = 0; i < tileRack.length; i++)
		{
			if (tileRack[i] == null)
			{
				drawn = tileBag.drawRandomTile();
				if (drawn != null)
				{
					tileRack[i] = drawn;
					getCurrentPlayer().addTile(drawn);
				}
			}
			
		}
	}
	
	
	
	/**
		Getter method for the collection of all players in the current game.
		@return An ArrayList<Player> representing all players participating in the current game.
		@author Claire Campbell
	*/
	public ArrayList<Player> getAllPlayers()
	{
		return (ArrayList<Player>) players.clone();
	}
	
	
	
	/**
		Enables or disables tutorial tooltips in the game board GUI.
		@param enabled A boolean. If true, tutorial tooltips will be enabled.
			If false, tutorial tooltips will be disabled.
		@author Claire Campbell
	*/
	public void setTutorialTooltipsEnabled(boolean enabled)
	{
		this.tutorialTooltipsEnabled = enabled;
	}
	
	
	
	/**
		Query whether tutorial tooltips are enabled or disabled for the GUI game board.
		@return Boolean true if tutorial tooltips are enabled; false if tooltips are disabled.
		@author Claire Campbell
	*/
	public boolean isTutorialTooltipsOn()
	{
		return this.tutorialTooltipsEnabled;
	}
	
	
	
	/**
		Modify the difficulty setting.
		@param d The new difficulty setting.
		@author Claire Campbell
	*/
	public void setDifficulty(Difficulty d)
	{
		this.difficulty = d;
	}
	
	
	
	/**
		Query the current difficulty setting.
		@return The current difficulty setting as a Difficulty constant.
		@author Claire Campbell
	*/
	public Difficulty getDifficulty()
	{
		return this.difficulty;
	}
}

/*
TILE   COUNT   VALUE
A	9	1
B	2	3
C	2	3
D	4	2
E	12	1
F	2	4
G	3	2
H	2	4
I	9	1
J	1	8
K	1	5
L	4	1
M	2	3
N	6	1
O	8	1
P	2	3
Q	1	10
R	6	1
S	4	1
T	6	1
U	4	1
V	2	4
W	2	4
X	1	8
Y	2	4
Z	1	10
*/