package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GlobalOptionsMenuPanel extends MenuPanel
{
	JPanel mainPanel;
	JRadioButton easy;
	JRadioButton hard;
	JRadioButton tutorialsEnabled;
	JRadioButton tutorialsDisabled;
	JPanel menuContents;
	JButton back;
	ActionListener listener;
	
	public GlobalOptionsMenuPanel() //panel within the MenuPanel
	{
		super("Options", 34);
		
		mainPanel = new JPanel();
		mainPanel.setMinimumSize(new Dimension(360, 450));
		mainPanel.setMaximumSize(new Dimension(360, 450));
		mainPanel.setBackground(Colors.yellow);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel difficulty = new JLabel("Difficulty");
		JLabel tutorialTooltips = new JLabel("Tutorial Tooltips");
		JLabel customTiles = new JLabel("Custom Tile Bag");
		back = new JButton("Back to Main Menu");
		
		listener = (
			new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
					parent.getController().requestMenu(ae.getActionCommand());
					//parent.execute(ae.getActionCommand());
				}
			}
		);
		
		back.addActionListener(listener);
		back.setBackground(Colors.yellow);
		back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
		back.setForeground(Colors.red);
		back.setMaximumSize(new Dimension(140, 30));
		back.setActionCommand("StartMenu");
		
		ButtonGroup difficultyButtons = new ButtonGroup();
		easy = new JRadioButton("Easy");
		hard = new JRadioButton("Hard");
		difficultyButtons.add(easy);
		difficultyButtons.add(hard);
		
		ButtonGroup tutorialButtons = new ButtonGroup();
		tutorialsEnabled = new JRadioButton("Enable");
		tutorialsDisabled = new JRadioButton("Disable");
		tutorialButtons.add(tutorialsDisabled);
		tutorialButtons.add(tutorialsEnabled);
		
		menuContents = new JPanel();
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridBagLayout());
		difficultyPanel.setLayout(new GridBagLayout());
		JPanel tutorialPanel = new JPanel();
		tutorialPanel.setLayout(new GridBagLayout());
		//JPanel tileBagPanel = new JPanel();
		
		GridBagConstraints c = new GridBagConstraints();
		
		
		difficultyPanel.setPreferredSize(new Dimension(320, 165));
		//difficultyPanel.setMinimumSize(new Dimension(300, 175));
		difficultyPanel.setMaximumSize(new Dimension(320, 165));
		difficultyPanel.setBackground(Colors.blue);
		tutorialPanel.setBackground(Colors.blue);
		tutorialPanel.setMaximumSize(new Dimension(320, 165));
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 10, 0, 0);
		difficultyPanel.add(difficulty, c);
		c.insets = new Insets(5, 30, 0, 10);
		c.gridy = 1;
		difficultyPanel.add(easy, c);
		c.gridy = 2;
		difficultyPanel.add(hard, c);
		
		c.gridy = 0;
		c.insets = new Insets(10, 10, 0, 0);
		tutorialPanel.add(tutorialTooltips, c);
		c.insets = new Insets(5, 30, 0, 10);
		c.gridy = 1;
		tutorialPanel.add(tutorialsEnabled, c);
		c.gridy = 2;
		tutorialPanel.add(tutorialsDisabled, c);
		
		
		JPanel bufferLine = new JPanel();
		bufferLine.setPreferredSize(new Dimension(260, 5));
		bufferLine.setMaximumSize(new Dimension(260, 5));
		bufferLine.setBackground(Colors.red);
		JPanel bufferLine2 = new JPanel();
		bufferLine2.setPreferredSize(new Dimension(300, 5));
		bufferLine2.setMaximumSize(new Dimension(300, 5));
		bufferLine2.setBackground(Colors.red);
		
		menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
		menuContents.setPreferredSize(new Dimension(340, 350));
		menuContents.setMaximumSize(new Dimension(340, 350));
		menuContents.add(difficultyPanel);
		menuContents.add(bufferLine);
		menuContents.add(tutorialPanel);
		//menuContents.add(bufferLine2);
		
		
		//menuContents.add(tileBagPanel);
		menuContents.setBorder(BorderFactory.createLineBorder(Colors.red, 6));
		menuContents.setBackground(Colors.blue);
		
		mainPanel.add(Box.createRigidArea(new Dimension(30, 30)));
		mainPanel.add(menuContents);
		mainPanel.add(Box.createRigidArea(new Dimension(250, 15)));
		mainPanel.add(back);
		JComponent comps[] = {easy, hard, tutorialsEnabled, tutorialsDisabled};
		fonts( comps, 18, Color.yellow);
		JComponent comps2 [] = {difficulty, tutorialTooltips};
		fonts(comps2, 26, Color.yellow);
		
		this.setContents(mainPanel);
	}
	
	private void fonts(JComponent [] arr, float fontSize, Color color)
	{
		for (int i = 0;  i < arr.length; i++)
		{
			arr[i].setFont(arr[i].getFont().deriveFont(fontSize));
			arr[i].setForeground(Colors.yellow);
			arr[i].setBackground(Colors.blue);
		}
	}
	
}