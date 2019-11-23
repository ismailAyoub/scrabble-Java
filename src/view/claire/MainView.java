package view.claire;
import model.claire.*;
import model.ismail.*;
import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MainView extends JFrame
{
	private JPanel contents; 
	private GameState gameState;

	public MainView(GameState state) 
	{
		this.gameState = state; 
		this.setSize(new Dimension(1060, 660));
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
		if (s.equals("LocalOptionsMenu"))
		{
			
		}
		if (s.equals("LoadGameMenu"))
		{
			
		}
		if (s.equals("SaveGameMenu"))
		{
			
		}
		this.add(contents);
		this.validate();
		this.repaint();
	}
	
	public GameState getGameState()
	{
		return this.gameState;
	}
	
	public static void main(String [] args) throws IOException
	{
		new MainView(new GameState(new GameBoard()));
	}
	

}