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
	
	
	
	//Do not save these fields in the save game files!!///////
	private ScrabbleTrie scrabbleTrie;
	private WordChecker wordChecker;
	//////////////////////////////////////////////////////////
	
	
	public GameState(GameBoard gb) throws IOException
	{
		this.board = gb;
		difficulty = Difficulty.EASY;
		tutorialTooltipsEnabled = true;
		
		//Set up ScrabbleTrie 
		scrabbleTrie = new ScrabbleTrie();
		//System.out.println((new File("Scrabble_EASY.txt")).getAbsolutePath());
		scrabbleTrie.initialize(
			"CollinsScrabbleWords2019.txt",
			"Scrabble_EASY.txt",
			"Scrabble_HARD.txt"
		);
		
		
		
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