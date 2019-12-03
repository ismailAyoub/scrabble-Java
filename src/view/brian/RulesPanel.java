package view.brian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import view.claire.*;

/**
  The RulesPanel class makes the GUI that displays the Rules to Players.
  @author Brian Huynh
*/
public class RulesPanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton back;
  JTextArea textArea;

  /**
    The RulesPanel constructor sets up the GUI window to contain the Rules Text.
    Sets up the back button. Brings the user back to the GameBoardGUI.
    @param parentFrame The JFrame that will hold the RulesPanel gui.
  */
  public RulesPanel(JFrame parentFrame) {
    super("Rules", 34);

    //Set up for the back button
    back = new JButton("Back");
    back.setActionCommand("GameBoardGUI");
    back.addActionListener(new NavigationListener());
    back.setBackground(Colors.yellow);
    back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
    back.setForeground(Colors.red);
    back.setMaximumSize(new Dimension(140, 30));

    textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.append("Test");

    //Set up for the sub panel to go within Main Panel
    menuContents = new JPanel();
    menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
    menuContents.setPreferredSize(new Dimension(340, 350));
    menuContents.setMaximumSize(new Dimension(340, 350));
    menuContents.setBackground(Colors.blue);
    menuContents.add(Box.createRigidArea(new Dimension(200, 10)));
    menuContents.add(textArea);

    //Set up for the big main panel
    mainPanel = new JPanel();
    mainPanel.setMinimumSize(new Dimension(360, 450));
    mainPanel.setMaximumSize(new Dimension(360, 450));
    mainPanel.setBackground(Colors.yellow);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(Box.createRigidArea(new Dimension(100, 25)));
		mainPanel.add(menuContents);
		mainPanel.add(Box.createRigidArea(new Dimension(250, 15)));
		mainPanel.add(back);

    this.setContents(mainPanel);
  }
}
