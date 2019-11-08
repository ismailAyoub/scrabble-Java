package view.claire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartMenuPanel extends MenuPanel
{
	private JButton [] startButtons;
	private ActionListener buttonListener;
	private String [] buttonTexts = {"New Game", "Load Game", "Options", "Quit"};
	private JPanel menuContents;
	public StartMenuPanel()
	{
		super("Scrabble", 34);
		
		menuContents = new JPanel();
		menuContents.setPreferredSize(new Dimension(400, 580));
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
			startButtons[i].addActionListener(
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
			menuContents.add(Box.createRigidArea(new Dimension(40, 30)));
			menuContents.add(startButtons[i]);
		}
		this.setContents(menuContents);
		
	}
}