package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//MenuPanel sets up the main "panel" which holds the different menus of the program--
//	Start Menu, Options Menu, Load Game Menu, etc.

public class MenuPanel extends JPanel
{
	private MainView parent;	//MainView is a subclass of JFrame
	private JPanel contents;
	private String title;
	private JPanel labelBar;
	
	
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
	
	public MenuPanel(String title, int fontSize)
	{
		this(title, fontSize, new JPanel());
	}
	
	public void setTitle(String title, int fontSize)
	{
		this.title = title;
		this.remove(labelBar);
		this.labelBar = createLabelPanel(title, fontSize);
		this.add(this.labelBar, 1);
		this.repaint();
	}
	public void setContents(JPanel contents)
	{
		this.remove(this.contents);
		this.contents = contents;
		this.add(contents);
		this.repaint();
	}

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
		//labelPanel.setMinimumSize(new Dimension(160, 60));
		labelPanel.setPreferredSize(new Dimension(160, 50));
		labelPanel.setMaximumSize(new Dimension(160, 60));
		//returnPanel.setMinimumSize(new Dimension(400, 100));
		returnPanel.setPreferredSize(new Dimension(400, 100));
		returnPanel.setMaximumSize(new Dimension(400, 100));
		
		//Add the label to the labelPanel and add the labelPanel to the JPanel which will be returned from the method
		returnPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		labelPanel.add(label);
		returnPanel.add(labelPanel);
		
		return returnPanel;
	}
}