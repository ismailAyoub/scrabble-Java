package model;

import model.ismail.*;
import model.claire.*;
import java.util.ArrayList;
import java.io.*;

public class GameState 
{
	private GameBoard board;
	private Difficulty difficulty;
	private boolean tutorialTooltipsEnabled;
	//private TileBag tileBag;
	
	
	//Do not save these fields in the save game files!!///////
	private ScrabbleTrie scrabbleTrie;
	private WordChecker wordChecker;
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
		
		//tileBag = new TileBag();
		//tileBag.init(new File("tile_distribution_default.txt"));
		//tileBag.printTileBag();
		
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