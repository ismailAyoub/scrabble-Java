package model;

import model.ismail.*;
import model.claire.*;
//import model.temp.*; 		///remove when junaid completes his files.
import model.Junaid.*;	///uncomment when junaid completes his files.
import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;	//sins against the delegate-model architecture here!

/**
	The GameState class is an aggregate of all classes that comprise the "state" of the application.
		It includes the WordChecker, an array list of Players, the GameBoard, and the various game settings.
	@author Claire Campbell
	@author Ismail Ayoub
*/
public class GameState 
{
	private GameBoard board;
	private Difficulty difficulty;
	private boolean tutorialTooltipsEnabled;
	private TileBag tileBag;
	private ArrayList<Player> players;
	private int currentPlayer;
	private ArrayList<ArrayList<TilePlacement> > wordsPlayedCurrent;
	private boolean firstTurn;
	private WordScoreCalculator wsc;
	private AI trueAI;
	//Do not save these fields in the save game files!!///////
	private ScrabbleTrie scrabbleTrie;
	private WordChecker wordChecker;
	boolean currentTurnValid;
	//////////////////////////////////////////////////////////
	
	/**
		The GameState constructor initializes the GameState with the specified GameBoard, a new ScrabbleTrie(),
			a new array list of Players, a new WordChecker, and a new TileBag.
		@param gb The GameBoard object that the GameState is associated with.
		@throws IOException If the constructor cannot initialize the ScrabbleTrie with words from 
			"CollinsScrabbleWords2019.txt", "Scrabble_EASY.txt", or "Scrabble_HARD.txt".
	*/
	public GameState(GameBoard gb) throws IOException
	{
		this.board = gb;
		wsc = new WordScoreCalculator(gb);
		difficulty = Difficulty.EASY;
		tutorialTooltipsEnabled = false;
		players = new ArrayList<Player>();
		
		//Set up ScrabbleTrie 
		scrabbleTrie = new ScrabbleTrie();
		//System.out.println((new File("Scrabble_EASY.txt")).getAbsolutePath());
		scrabbleTrie.initialize(
			"CollinsScrabbleWords2019.txt",
			"Scrabble_EASY.txt",
			"Scrabble_HARD.txt"
		);
		
		wordChecker = new WordChecker(scrabbleTrie, gb);
		wordsPlayedCurrent = new ArrayList<ArrayList<TilePlacement> >();
		
		tileBag = new TileBag();
		tileBag.init(new File("tile_distribution_default.txt"));
		tileBag.printTileBag();
		
		currentTurnValid = false;
		currentPlayer = 0;
		firstTurn = true;
	}
	
	
	/**
		This is the getter method for the currentTurnValid field;
			it should be called after finalize() has been called for the current turn.
		@return The boolean value true is the current turn is valid; false if not.
		@author Claire Campbell
	*/
	public boolean isCurrentTurnValid()
	{
		return currentTurnValid;
	}
	
	public int getTilesLeft()
	{
		return tileBag.getAllTiles().size();
	}
	
	/**
		The addPlayer() method adds the specified player to the GameState.
		@param s The name of the player to be added.
		@param points The number of points the player starts out with.
		@author Claire Campbell
	*/
	public void addPlayer(String s, int points)
	{
		players.add(new Player(s, 0));
		for (int i = 0; i < 7; i++)
		{
			players.get(players.size() - 1).addTile(tileBag.drawRandomTile());
		}
	}
	
	
	/**
		The nextTurn() method sets the current player to the next player in the array list of Players.
			It should be called after finalize() and drawTiles().
		@author Claire Campbell
	*/
	public void nextTurn()
	{
		currentPlayer += 1;
		if (currentPlayer >= players.size())
		{
			currentPlayer = 0;
		}
		if (players.get(currentPlayer) instanceof AI)
		{
			trueAI = new AI(board, difficulty);
			System.out.println("Adding AI Word:");
			System.out.println("\n\nBeginning of Turn:");
			board.printBoard();
			trueAI.scanBoard(board);
			trueAI.addWord(board);
			//((AI)players.get(currentPlayer)).scanBoard(board);
			//((AI)players.get(currentPlayer)).addWord(board);
			//((AI)players.get(currentPlayer)).scanBoard(board);
			System.out.println("After AI Method:");
			board.printBoard();
			
			wordChecker.readTilesFromBoard();
			
			
			ArrayList<ArrayList<TilePlacement> > words = wordChecker.validateTiles();
			wordsPlayedCurrent = words;
		
			///if the tiles are invalid, rollback the turn.
			if (words.size() == 0)
			{
				board.rollbackTurn();
				wordChecker.reset();
				while (words.size() == 0)
				{
					System.out.println("Adding AI Word:");
					trueAI = new AI(board, difficulty);
					trueAI.scanBoard(board);
					trueAI.addWord(board);
					//((AI)players.get(currentPlayer)).scanBoard(board);
					//((AI)players.get(currentPlayer)).addWord(board);
					//((AI)players.get(currentPlayer)).scanBoard(board);
					wordChecker.readTilesFromBoard();
					words = wordChecker.validateTiles();
					wordsPlayedCurrent = words;
					if (words.size() == 0)
					{
						board.rollbackTurn();
						wordChecker.reset();
					}
					
				}
			}
			///If the tiles are valid, score them and finalize the tiles on the board.
			else
			{
				
			}
			board.finalizeTurn();
			for (int i = 0; i < players.size(); i++)
			{
				if (players.get(i) instanceof AI)
				{
					//((AI)players.get(i)).scanBoard(board);
				}
			}
			int scorePts = scoreWords(words);
			JOptionPane.showMessageDialog(null, "AI earned " + scorePts + " points.");
			wordChecker.reset();
			System.out.println("\n\nEnd of Turn:");
			board.printBoard();
			////score the word.
			nextTurn();
			//((AI)players.get(currentPlayer)).scanBoard(board);
			
		}
	}
	
	/**
		This method scores the words placed on the board during the current turn.
		@param wrds An ArrayList of ArrayLists of TilePlacements containing the words placed
			on the board during the current turn.
	*/
	public int scoreWords(ArrayList<ArrayList<TilePlacement> > wrds)
	{
		int score = 0;
		for (int i = 0; i < wrds.size(); i++)
		{
			for (int j = 0; j < wrds.get(i).size(); j++)
			{
				int temp = wrds.get(i).get(j).getTile().getPoints();
				int row = wrds.get(i).get(j).getRow();
				int col = wrds.get(i).get(j).getCol();
				if (board.getNode(row, col).getBounce().equals("tl") && board.getNode(row, col).getCurrent() == true)
				{
					temp *= 3;
				}
				else if (board.getNode(row, col).getBounce().equals("dl") && board.getNode(row, col).getCurrent() == true)
				{
					temp *= 2;
				}
				score += temp;
			}
			
		}
		getCurrentPlayer().addPoints(score);
		return score;
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
		The getCurrentWords() method returns the ArrayList of ArrayLists of TilePlacement
			objects representing all words placed on the board during the current turn.
			This method shouled be called after finalize() has been called for the current turn.
		@return An ArrayList&lt;ArrayList&lt;TilePlacement&gt;&gt; representing the words placed on the board
			during the current turn. This container is empty is no valid words were placed on the board during the 
			current turn.
	*/
	public ArrayList<ArrayList<TilePlacement> > getCurrentWords()
	{
		return wordsPlayedCurrent;
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
		//put the tiles on the board that were played during the current turn 
		for (int i = 0; i < tilesPlaced.size(); i++)
		{
			board.setTile(tilesPlaced.get(i).getRow(), tilesPlaced.get(i).getCol(), tilesPlaced.get(i).getTile());
		}
		
		//check the tiles for validity
		wordChecker.readTilesFromBoard();
		ArrayList<ArrayList<TilePlacement> > words = wordChecker.validateTiles();
		if (firstTurn)
		{
			words = wordChecker.validateTilesFirstTurn();
			if (words.size() != 0)
				firstTurn = false;
		}
		wordsPlayedCurrent = words;
		
		///if the tiles are invalid, rollback the turn.
		if (words.size() == 0)
		{
			board.rollbackTurn();
			wordChecker.reset();
			currentTurnValid = false;
		}
		///If the tiles are valid, score them and finalize the tiles on the board.
		else
		{
			board.finalizeTurn();
			for (int i = 0; i < players.size(); i++)
			{
				if (players.get(i) instanceof AI)
				{
					((AI)players.get(i)).scanBoard(board);
				}
			}
				
			scoreWords(words);
			///todo: Score the tiles in "words", add the score to the current player.
			
			if (!(players.get(currentPlayer) instanceof AI))
			{
				for (int i = 0; i < tilesPlaced.size(); i++)
				{
					getCurrentPlayer().removeTile(tilesPlaced.get(i).getTile().getLetter());
				}
			}
			currentTurnValid = true;
			wordChecker.reset();
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
		The getter method for the tile rack of the current player.
		@return An array of Tile objects representing the tile rack of the current player.
		@author Claire Campbell
	*/
	public Tile[] getTileRack()
	{
		return getCurrentPlayer().getTileRack();
	}
	
	
	/**
		The isCurrentTurnTile() method checks whether the tile at the given position in the internal game board
			was placed during the current turn.
		@param r The row of the tile to check.
		@param c The column of the tile to check.
		@return The boolean value true if the tile in the specified row and column was placed during the current turn;
			false otherwise.
		@author Claire Campbell
	*/
	public boolean isCurrentTurnTile(int r, int c)
	{
		return board.getNode(r, c).getCurrent();
	}
	
	
	/**
		Getter method for the collection of all players in the current game.
		@return An ArrayList of Player objects representing all players participating in the current game.
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

	/**
		This method prints the contents of the GameBoard object associated with the GameState to console.
	*/
	public void printBoard()
	{
		board.printBoard();
	}
	
	/**
		This method returns the Node object in the GameBoard at the given position.
		@param i The row to retrieve the node from.
		@param j The column to retrieve the node from.
		@return The Node object at row i and column j on the GameBoard.
	*/
	public Node getNode(int i, int j)
	{
		return board.getNode(i, j);
	}
	
	/**
		This method returns the Tile object in the GameBoard at the given position.
		@param i The row to retrieve the Tile from.
		@param j The column to retrieve the Tile from.
		@return The Tile object at row i and column j on the GameBoard.
	*/
	public Tile getTilePlacedAt(int i, int j)
	{
		return board.getTile(i, j);
	}
	
	
	/**
		
	*/
	public void load(File f)
	{
		
	}
	
	
	
	/**
	
	*/
	public void save(File f)
	{
		
	}
	
	
	
	/**
		This method adds the AI Player for single player mode to the game state.
		@author Claire Campbell
	*/
	public void addAIPlayer()
	{
		players.add(new AI(board, difficulty));
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