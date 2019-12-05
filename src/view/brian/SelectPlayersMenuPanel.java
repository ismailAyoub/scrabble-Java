package view.brian;

import view.claire.*;
import model.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
  The SelectPlayersMenuPanel is the GUI window that allows player to choose
  how many players and enter players' names.
  @author Brian Huynh
*/
public class SelectPlayersMenuPanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton next;
  JButton back;
  JTextField playersNames[] = new JTextField[4]; // Field for players to enter name
  String names[] = new String[4]; // To store players names
  JLabel labels[] = new JLabel[4];

  /**
    The nextListener class is an ActionListener for the "next" JButton.
    Clicking "next" will bring the players to the next screen or gameboard.
    @author Brian Huynh
  */
  public class nextListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      int startAmount = 4; // Variable used to determine how many players
                           // will play. Also used to make sure minimum of 2.
      JButton source = (JButton)ae.getSource();
      MainView parent = (MainView)SwingUtilities.getWindowAncestor(source);
	  try {parent.newGameState();} catch (IOException e) {}
      GameState state = parent.getGameState();
      for(int i = 0; i < 4; ++i) {
        names[i] = playersNames[i].getText();
        if(names[i].trim().isEmpty()) {
          startAmount--;
        }
      }
      if(startAmount >= 2) {
        for(int i = 0; i < 4; ++i) {
          if(names[i].trim().isEmpty()){
            //do nothing
          }else {
            state.addPlayer(names[i], 0);
          }
        }
      }else {
        JOptionPane.showMessageDialog(null, "Please enter minimum of 2 players");
      }
      if(startAmount >= 2){
        parent.loadMenu(ae.getActionCommand());
      }
    }
  }

  /**
    The default constructor for the SelectPlayersMenuPanel adds labels
    corresponding to their textfields to the SelectGameModePanel.
    Players will be able to enter their names.
    @param parentFrame The JFrame that will contain the SelectGameModePanel
  */
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
    //next.addActionListener(new NavigationListener());
    next.addActionListener(new nextListener());
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
