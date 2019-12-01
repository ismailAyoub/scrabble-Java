package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
	The JPanel-derived GUI container class containing the Start Menu. This panel is displayed when the 
		application is started.
	@author Claire Campbell
*/
public class StartMenuPanel extends MenuPanel
{
	private JButton [] startButtons;
	private ActionListener buttonListener;
	private String [] buttonTexts = {"New Game", "Load Game", "Options", "Quit"};
	private JPanel menuContents;
	
	/**
		The constructor for the StartMenuPanel. Sets up the Start Menu that is displayed to the user
			when the application is started up. 
	*/
	public StartMenuPanel()
	{
		super("Scrabble", 34);
		
		menuContents = new JPanel();
		menuContents.setPreferredSize(new Dimension(400, 450));
		menuContents.setMinimumSize(new Dimension(400, 450));
		menuContents.setMaximumSize(new Dimension(400, 450));
		menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
		menuContents.setBackground(Colors.yellow);
		startButtons = new JButton[buttonTexts.length];
		menuContents.add(Box.createRigidArea(new Dimension(40, 40)));
		for (int i = 0; i < buttonTexts.length; i++)
		{
			startButtons[i] = new JButton(buttonTexts[i]);
			startButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);	//Center the JButtons in the panel.
																		//By default, JButtons have left-alignment (Component.LEFT_ALIGNMENT)
			startButtons[i].setPreferredSize(new Dimension(180, 50));
			startButtons[i].setMaximumSize(new Dimension(180, 50));
			startButtons[i].setFont(startButtons[i].getFont().deriveFont(20f));
			startButtons[i].setBackground(Colors.blue);
			startButtons[i].setForeground(Colors.yellow);
			startButtons[i].setActionCommand(startButtons[i].getText());
			startButtons[i].addActionListener( new NavigationListener()
				/*new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
						parent.loadMenu(ae.getActionCommand());
						//parent.execute(ae.getActionCommand());
					}
				}*/
			);
			menuContents.add(Box.createRigidArea(new Dimension(40, 30)));
			menuContents.add(startButtons[i]);
		}
		this.setContents(menuContents);
		
	}
}