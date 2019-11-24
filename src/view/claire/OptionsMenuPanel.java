package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.*;
import model.claire.*;
public class OptionsMenuPanel extends MenuPanel
{
	JPanel mainPanel;
	JRadioButton easy;
	JRadioButton hard;
	JRadioButton tutorialsEnabled;
	JRadioButton tutorialsDisabled;
	JPanel menuContents;
	JButton back;
	
	private class DifficultyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			MainView parent = (MainView)SwingUtilities.getWindowAncestor((JRadioButton)ae.getSource());
			GameState state = parent.getGameState();
			if (ae.getActionCommand().equals("easy"))
			{
				state.setDifficulty(Difficulty.EASY);
			}
			else
			{
				state.setDifficulty(Difficulty.HARD);
			}
		}
	}
	
	private class TutorialsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			MainView parent = (MainView)SwingUtilities.getWindowAncestor((JRadioButton)ae.getSource());
			GameState state = parent.getGameState();
			if (ae.getActionCommand().equals("enable"))
			{
				state.setTutorialTooltipsEnabled(true);
			}
			else
			{
				state.setTutorialTooltipsEnabled(false);
			}
		}
	}
	
	
	public OptionsMenuPanel(JFrame parentFrame) //panel within the MenuPanel
	{
		super("Options", 34);
		
		
		
		//Set up mainPanel.
		mainPanel = new JPanel();
		mainPanel.setMinimumSize(new Dimension(360, 450));
		mainPanel.setMaximumSize(new Dimension(360, 450));
		mainPanel.setBackground(Colors.yellow);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		
		
		//The JLabels for the radio buttons.
		JLabel difficulty = new JLabel("Difficulty");
		JLabel tutorialTooltips = new JLabel("Tutorial Tooltips");
		
		
		
		
		//Setup the "Back to Main Menu" JButton
		back = new JButton("Back to Main Menu");
		back.setActionCommand("StartMenu");
		back.addActionListener(new NavigationListener());
		back.setBackground(Colors.yellow);
		back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
		back.setForeground(Colors.red);
		back.setMaximumSize(new Dimension(140, 30));
		
		
		
		
		//Set up easy.hard difficulty radio buttons;
		//Add the easy/hard difficulty radio buttons to the ButtonGroup difficultyButtons 
		ButtonGroup difficultyButtons = new ButtonGroup();
		easy = new JRadioButton("Easy");
		hard = new JRadioButton("Hard");
		easy.setActionCommand("easy");
		hard.setActionCommand("hard");
		easy.addActionListener(new DifficultyButtonListener());
		hard.addActionListener(new DifficultyButtonListener());
		difficultyButtons.add(easy);
		difficultyButtons.add(hard);
		
		//Select the appropriate easy/hard GUI ratio button for the current diffculty
		GameState state = ((MainView)parentFrame).getGameState();
		if (state.getDifficulty() == Difficulty.EASY)
		{
			easy.setSelected(true);
			hard.setSelected(false);
		}
		else
		{
			hard.setSelected(true);
			easy.setSelected(false);
		}
		
		
		
		
		//Add the tutorial tooltip radio buttons to their ButtonGroup (tutorialButtons)
		ButtonGroup tutorialButtons = new ButtonGroup();
		tutorialsEnabled = new JRadioButton("Enable");
		tutorialsDisabled = new JRadioButton("Disable");
		tutorialButtons.add(tutorialsDisabled);
		tutorialButtons.add(tutorialsEnabled);
		tutorialsEnabled.setActionCommand("enable");
		tutorialsDisabled.setActionCommand("disable");
		tutorialsEnabled.addActionListener(new TutorialsButtonListener());
		tutorialsDisabled.addActionListener(new TutorialsButtonListener());
		
		//Select the appropriate tutorials radio button in the GUI for the current tooltips setting
		if (state.isTutorialTooltipsOn())
		{
			tutorialsEnabled.setSelected(true);
			tutorialsDisabled.setSelected(false);
		}
		else
		{
			tutorialsDisabled.setSelected(true);
			tutorialsEnabled.setSelected(false);
		}
		
		
		
		
		//menuContents is the JPanel containing the difficultyPanel and the TutorialPanel
		//The difficultyPanel contains the easy/hard difficulty settings' radio buttons
		//The tutorialPanel contains the tutorial tooltips radio buttons
		menuContents = new JPanel();
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridBagLayout());
		difficultyPanel.setPreferredSize(new Dimension(320, 165));
		difficultyPanel.setMaximumSize(new Dimension(320, 165));
		difficultyPanel.setBackground(Colors.blue);
		
		JPanel tutorialPanel = new JPanel();
		tutorialPanel.setLayout(new GridBagLayout());
		tutorialPanel.setBackground(Colors.blue);
		tutorialPanel.setMaximumSize(new Dimension(320, 165));
		tutorialPanel.setPreferredSize(new Dimension(320, 165));
		
		
		
		
		//Set up the difficultyPanel and the tutorialPanel; 
		//	add the radio buttons and the appropriate JLabel to both
		GridBagConstraints c = new GridBagConstraints();
		
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
		
		
		
		
		//Create the "lines" separating the tutorialPanel and difficultyPanel in the GUI
		JPanel bufferLine = new JPanel();
		bufferLine.setPreferredSize(new Dimension(260, 5));
		bufferLine.setMaximumSize(new Dimension(260, 5));
		bufferLine.setBackground(Colors.red);
		JPanel bufferLine2 = new JPanel();
		bufferLine2.setPreferredSize(new Dimension(300, 5));
		bufferLine2.setMaximumSize(new Dimension(300, 5));
		bufferLine2.setBackground(Colors.red);
		
		
		
		
		//Add the difficultyPanel, "buffer lines", tutorialPanel to the menuContents panel
		menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
		menuContents.setPreferredSize(new Dimension(340, 350));
		menuContents.setMaximumSize(new Dimension(340, 350));
		menuContents.add(difficultyPanel);
		menuContents.add(bufferLine);
		menuContents.add(tutorialPanel);		
		menuContents.setBorder(BorderFactory.createLineBorder(Colors.red, 6));
		menuContents.setBackground(Colors.blue);
		
		
		
		
		//Add the menuContents panel to the mainPanel
		mainPanel.add(Box.createRigidArea(new Dimension(30, 30)));
		mainPanel.add(menuContents);
		mainPanel.add(Box.createRigidArea(new Dimension(250, 15)));
		mainPanel.add(back);
		
		
		
		
		//Set font size and color to the radio buttons and the JLabels that label the radio buttons
		JComponent comps[] = {easy, hard, tutorialsEnabled, tutorialsDisabled};
		fonts(comps, 18, Color.yellow);
		JComponent comps2 [] = {difficulty, tutorialTooltips};
		fonts(comps2, 26, Color.yellow);
	
		
		
		
		//Call the superclass setContents method to add the mainPanel to the GUI menu
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