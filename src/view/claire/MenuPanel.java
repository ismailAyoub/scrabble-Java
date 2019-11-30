package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
	MenuPanel sets up the main "panel" which holds the different menus of the program--
		Start Menu, Options Menu, Load Game Menu, etc. It is meant to be a superclass to all JPanel-derived
		containers that display a menu.
	@author Claire Campbell
*/
public class MenuPanel extends JPanel
{
	private MainView parent;	//MainView is a subclass of JFrame
	private JPanel contents;
	private String title;
	private JPanel labelBar;
	
	/**
		This is the constructor for the MenuPanel. It sets up the MenuPanel container to be the 
			appropriate size (400 by 580) and background color (yellow). It also adds a JPanel displaying
			the title of the menu to the top of the MenuPanel.
		@param title The title to be displayed at the top of the menu panel.
		@param fontSize The font size for the title text.
		@param contents The 400x450 pixel JPanel containing the menu contents.
	*/
	public MenuPanel(String title, int fontSize, JPanel contents)
	{
		this.setMinimumSize(new Dimension(400, 580));
		this.setMaximumSize(new Dimension(400, 580));
		this.setPreferredSize(new Dimension(400, 580));
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		//this.setBorder(BorderFactory.createLineBorder(Colors.red, 6));
		this.setBackground(Colors.yellow);
		
		this.labelBar = createLabelPanel(title, fontSize);
		this.title = title;
		this.contents = contents; 
		this.parent = parent;
		
		this.add(Box.createRigidArea(new Dimension(400, 30)));
		this.add(labelBar);
		this.add(contents);
	}
	
	/**
		This is the constructor for the MenuPanel. It sets up the MenuPanel container to be the 
			appropriate size (400 by 580) and background color (yellow). It also adds a JPanel displaying
			the title of the menu to the top of the MenuPanel.
		@param title The title to be displayed at the top of the menu panel.
		@param fontSize The font size for the title text.
	*/
	public MenuPanel(String title, int fontSize)
	{
		this(title, fontSize, new JPanel());
	}
	
	/**
		Sets the title displayed at the top of the MenuPanel.
		@param title The title to be displayed at the top of the menu.
		@param fontSize The font size for the title text.
	*/
	public void setTitle(String title, int fontSize)
	{
		this.title = title;
		this.remove(labelBar);
		this.labelBar = createLabelPanel(title, fontSize);
		this.add(this.labelBar, 1);
		this.repaint();
	}
	
	/**
		Sets the contents of the MenuPanel to the specified JPanel.
		@param contents The 400x580 pixel JPanel containing the menu contents.
	*/
	public void setContents(JPanel contents)
	{
		this.remove(this.contents);
		this.contents = contents;
		this.add(contents);
		this.repaint();
	}
	
	/**
		Creates the label panel containing the title of the menu.
		@param title The String containing the title to be displayed in the label panel at the top of the menu panel.
		@param fontSize The font size for the title's text.
		@return A JPanel containing the title, formatted to agree with the look-and-feel of the Scrabble application.
	*/
	private JPanel createLabelPanel(String title, float fontSize)
	{
		JPanel returnPanel = new JPanel();			//The panel to be returned.
		JLabel label = new JLabel(title);			//The label within the panel to be returned
		JPanel labelPanel = new JPanel();			//The panel to hold the label
		
		//Setup colors and layouts
		returnPanel.setBackground(Colors.blue);
		returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
		labelPanel.setBackground(Colors.yellow);
		label.setForeground(Colors.red);
		label.setFont(label.getFont().deriveFont(fontSize));
		
		//Setup sizes
		labelPanel.setPreferredSize(new Dimension(160, 50));
		labelPanel.setMaximumSize(new Dimension(160, 60));
		returnPanel.setPreferredSize(new Dimension(400, 100));
		returnPanel.setMaximumSize(new Dimension(400, 100));
		
		//Add the label to the labelPanel and add the labelPanel to the JPanel which will be returned from the method
		returnPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		labelPanel.add(label);
		returnPanel.add(labelPanel);
		
		return returnPanel;
	}
}