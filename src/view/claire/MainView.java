package view.claire;
import model.claire.*;
import model.ismail.*;
import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import view.brian.*;
import view.claire.*;


import view.temp.*;////WHEN max is done w AppContainer, comment this out...
//import view.max.*;	///And uncomment this.



/**
	The MainView class is the GUI window that displays the menu and game board of the game.
	@author Claire Campbell
*/
public class MainView extends JFrame
{
	private JPanel contents; 
	private GameState gameState;

	/**
		The constructor for the MainView class sets up the frame that displays all GUI components of the game.
		@param state The GameState associated with the MainView.
	*/
	public MainView(GameState state) 
	{
		this.gameState = state; 
		this.setJMenuBar(new view.brian.MenuBar());
		this.setSize(new Dimension(1060, 700));
		this.getContentPane().setBackground(Colors.red);
		contents = new JPanel();
		contents.setPreferredSize(new Dimension(400, 600));
		contents.setMinimumSize(new Dimension(400, 600));
		contents.add(Box.createRigidArea(new Dimension(400, 10)));
		contents.setBackground(Colors.red);
		//contents = new StartMenuPanel();
		contents.add(new StartMenuPanel());
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(contents);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
		The loadMenu() method loads and displays the specified menu in the JFrame.
		@param s A String specifying the menu to load.
	*/
	public void loadMenu(String s)
	{
	
		this.remove(contents);
		contents = new JPanel();
		contents.setPreferredSize(new Dimension(400, 600));
		contents.setMinimumSize(new Dimension(400, 600));
		contents.add(Box.createRigidArea(new Dimension(400, 10)));
		contents.setBackground(Colors.red);
		if (s.equals("StartMenu"))
		{
			this.contents.add(new StartMenuPanel());
		}
		if (s.equals("OptionsMenu"))
		{
			this.contents.add(new OptionsMenuPanel(this));
		}
		if (s.equals("Options"))
		{
			this.contents.add(new OptionsMenuPanel(this));
		}
		if (s.equals("Quit"))
		{
			System.exit(0);
		}
		if (s.equals("LoadGameMenu"))
		{
			this.contents.add(new LoadGameMenuPanel(this));
		}
		if (s.equals("Load Game"))
		{
			this.contents.add(new LoadGameMenuPanel(this));
		}
		if (s.equals("SaveGameMenu"))
		{
			this.contents.add(new SaveGameMenuPanel(this));
		}
		if (s.equals("Rules"))
		{
			this.contents.add(new RulesPanel(this));
		}
		if (s.equals("GameBoardGUI"))
		{
			
			this.contents = new JPanel();
			this.contents.setBackground(Colors.red);
			this.contents.setPreferredSize(new Dimension(1060, 660));
			this.contents.setMinimumSize(new Dimension(1060, 660));
			this.contents.add(new AppContainer(this));
		}
		if (s.equals("Rules"))
		{
			this.contents.add(new RulesPanel(this));
		}
		if (s.equals("New Game"))
		{
			this.contents.add(new SelectGameModePanel(this));
		}
		if (s.equals("multi"))
		{
			this.contents.add(new SelectPlayersMenuPanel(this));
		}
		this.add(contents);
		this.validate();
		this.revalidate();
		this.repaint();
	}
	
	/**
		The getGameState() method retrieves and returns the GameState object associated with the GUI/
			MainView. 
		@return The GameState object associated with the MainView.
	*/
	public GameState getGameState()
	{
		return this.gameState;
	}
	
	/**
		The main method of the application; creates a new MainView and passes a new GameState object to it
			in order to start the application.
		@param args Command line arguments; unused.
		@throws IOException If any method in GameState that reads from a file on disk fails to do so.
	*/
	public static void main(String [] args) throws IOException
	{
		new MainView(new GameState(new GameBoard()));
	}
	

}