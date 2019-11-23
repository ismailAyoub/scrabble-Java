package view.claire;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class NavigationListener implements ActionListener 
{
	public void actionPerformed(ActionEvent ae)
	{
		JButton source = (JButton)ae.getSource();
		MainView parent = (MainView)SwingUtilities.getWindowAncestor(source);
		parent.loadMenu(ae.getActionCommand());
	}
}