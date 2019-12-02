package view.brian;

import view.claire.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.*;
import model.claire.*;
import model.ismail.*;

/**
  The SelectGameModePanel is the GUI that allow player(s) to choose
  which gamemode to play: Single-Player or Multi-player
  @author Brian Huynh
*/
public class SelectGameModePanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton singlePlayer;
  JButton multiPlayer;
  JButton back;

  /**
    The SinglePLayerListener is the ActionListener for the single Player
    JButton. Clicking "Single Player" will bring the player to GameBoard.
    @author Brian Huynh
  */
  public class SinglePLayerListener implements ActionListener {
      public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
      GameState state = parent.getGameState();
      state.addPlayer("Player 1", 0);
      parent.loadMenu(ae.getActionCommand());
    }
  }

  /**
    The default constructor for SelectGameModePanel adds two JRadioButtons
    each represeting "Single Player" and "Multi-Player".
    multiPlayer JButton brings the players to SelectPlayersMenuPanel.
    @param parentFrame The JFrame that will contain the SelectGameModePanel
    @author Brian Huynh
  */
  public SelectGameModePanel(JFrame parentFrame) {
    super("Scrabble", 34);

    JLabel subTitle = new JLabel("Select Game Mode:");
    subTitle.setFont(subTitle.getFont().deriveFont(20f));
    subTitle.setForeground(Colors.yellow);

    //Set up for the back button
    back = new JButton("Back");
    back.setActionCommand("StartMenu");
    back.addActionListener(new NavigationListener());
    back.setBackground(Colors.yellow);
    back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
    back.setForeground(Colors.red);
    back.setMaximumSize(new Dimension(140, 30));

    //Set up for Single Player Button
    singlePlayer = new JButton("Single Player");
    singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    singlePlayer.setPreferredSize(new Dimension(180,50));
    singlePlayer.setMaximumSize(new Dimension(180,50));
    singlePlayer.setFont(singlePlayer.getFont().deriveFont(20f));
    singlePlayer.setBackground(Colors.blue);
    singlePlayer.setForeground(Colors.yellow);
    singlePlayer.setActionCommand("GameBoardGUI");
    singlePlayer.addActionListener(new SinglePLayerListener);
    //singlePlayer.addActionListener(new NavigationListener());

    //Set up for Multiplayer Button
    multiPlayer = new JButton("Multi Player");
    multiPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    multiPlayer.setPreferredSize(new Dimension(180, 50));
    multiPlayer.setMaximumSize(new Dimension(180, 50));
    multiPlayer.setFont(multiPlayer.getFont().deriveFont(20f));
    multiPlayer.setBackground(Colors.blue);
    multiPlayer.setForeground(Colors.yellow);
    multiPlayer.setActionCommand("multi");
    multiPlayer.addActionListener(new NavigationListener());

    //Set up for the sub panel to go within Main Panel
    menuContents = new JPanel();
    menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
    menuContents.setPreferredSize(new Dimension(340, 350));
    menuContents.setMaximumSize(new Dimension(340, 350));
    menuContents.setBackground(Colors.blue);
    menuContents.add(subTitle);
    menuContents.add(singlePlayer);
    menuContents.add(multiPlayer);

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
