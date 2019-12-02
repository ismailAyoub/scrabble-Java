package view.claire;
package view.brian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import view.claire.*;

public class SelectPlayersMenuPanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton next;
  JButton back;
  JTextField playersNames[] = new JTextField[4]; // Field for players to enter name
  String names[] = new String[4]; // To store players names
  JLabel labels[] = new JLabel[4];

  public class nextListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      JButton source = (JButton)ae.getSource();
      MainView parent = (MainView)SwingUtilities.getWindowAncestor(source);
      for(int i = 0; i < 4; ++i) {
        names[i] = playersNames[i].getText();
        parent.addPlayer(playersNames[i], 0);
      }
  		parent.loadMenu(ae.getActionCommand());
    }
  }

  public SelectPlayersMenuPanel(JFrame parentFrame) {
    super("Scrabble", 34);

    //Set up labels for Text Fields
    for(int i=0;i<4;++i){
      labels[i] = new JLabel("Player " + (i+1));
      labels[i].setForeground(Colors.yellow);
    }

    //Set up for the back button
    back = new JButton("Back");
    back.setActionCommand("New Game");
    back.addActionListener(new NavigationListener());
    back.setBackground(Colors.yellow);
    back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
    back.setForeground(Colors.red);
    back.setMaximumSize(new Dimension(140, 30));

    //Set up for the next buttons
    next = new JButton("Next");
    next.setBackground(Colors.yellow);
    back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
    next.setForeground(Colors.red);
    next.setActionCommand("GameBoardGUI");
    next.addActionListener(new NavigationListener());
    next.setMinimumSize(new Dimension(140, 30));

    //Set up for menuContents
    menuContents = new JPanel();
    menuContents.setLayout(new BoxLayout(menuContents,BoxLayout.Y_AXIS));
    menuContents.setBackground(Colors.blue);

    //Set up text fields
    for (int i = 0; i < 4; ++i) {
      playersNames[i] = new JTextField();
      playersNames[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      menuContents.add(labels[i]);
      menuContents.add(playersNames[i]);
    }

    //Set up main panel
    mainPanel = new JPanel();
    mainPanel.setMinimumSize(new Dimension(360, 450));
    mainPanel.setMaximumSize(new Dimension(360, 450));
    mainPanel.setBackground(Colors.yellow);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(Box.createRigidArea(new Dimension(100, 25)));
    mainPanel.add(menuContents);
    mainPanel.add(Box.createRigidArea(new Dimension(250, 15)));
    mainPanel.add(next);
    mainPanel.add(back);

    this.setContents(mainPanel);
    }
}
