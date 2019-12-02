package view.brian;

import view.claire.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.claire.*;
import model.ismail.*;
import model.*;
import java.io.File;

public SaveGameMenuPanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton back;
  JButton saveSlot1,saveSlot2,saveSlot3,saveSlot4; // represent Saved GameState
  String [] buttonTexts = {"Game Slot 1", "Game Slot 2", "Game Slot 3", "Game Slot 4"};

  public class saveSlotListener1 implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame.txt");
      if(savegame.exists()){
        state.save(savegame);
      }
      parent.loadMenu("GameBoardGUI");
	   }
  }
  public class saveSlotListener2 implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame.txt");
      if(savegame.exists()){
        state.save(savegame);
      }
      parent.loadMenu("GameBoardGUI");
	   }
  }
  public class saveSlotListener3 implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame.txt");
      if(savegame.exists()){
        state.save(savegame);
      }
      parent.loadMenu("GameBoardGUI");
	   }
  }
  public class saveSlotListener4 implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame.txt");
      if(savegame.exists()){
        state.save(savegame);
      }
      parent.loadMenu("GameBoardGUI");
	   }
  }

  public SaveGameMenuPanel(JFrame parentFrame) {
    super("Save Game", 28)

    //set up back button
    back = new JButton("Back");
    back.setActionCommand("StartMenu");
    back.addActionListener(new NavigationListener());
    back.setBackground(Colors.yellow);
    back.setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
    back.setForeground(Colors.red);
    back.setMaximumSize(new Dimension(140, 30));

    //Set up Menu Content
    menuContents = new JPanel();
    menuContents.setLayout(new BoxLayout(menuContents, BoxLayout.Y_AXIS));
    menuContents.setPreferredSize(new Dimension(340, 350));
    menuContents.setMaximumSize(new Dimension(340, 350));
    menuContents.setBackground(Colors.blue);

    //Set up buttons aka save games
	  saveSlot1 = new JButton(buttonTexts[0]);
	  saveSlot1.setAlignmentX(Component.CENTER_ALIGNMENT);
	  saveSlot1.setPreferredSize(new Dimension(175, 75));
    saveSlot1.setMaximumSize(new Dimension(175, 75));
    saveSlot1.setFont(saveSlot1.getFont().deriveFont(20f));
    saveSlot1.setBackground(Colors.blue);
    saveSlot1.setForeground(Colors.yellow);
    saveSlot1.addActionListener(new loadSlotListener1());
    menuContents.add(Box.createRigidArea(new Dimension(40, 30)));
    menuContents.add(saveSlot1);

    saveSlot2 = new JButton(buttonTexts[1]);
    saveSlot2.setAlignmentX(Component.CENTER_ALIGNMENT);
    saveSlot2.setPreferredSize(new Dimension(175, 75));
    saveSlot2.setMaximumSize(new Dimension(175, 75));
    saveSlot2.setFont(saveSlot2.getFont().deriveFont(20f));
    saveSlot2.setBackground(Colors.blue);
    saveSlot2.setForeground(Colors.yellow);
    saveSlot2.addActionListener(new loadSlotListener2());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(saveSlot2);

    saveSlot3 = new JButton(buttonTexts[2]);
    saveSlot3.setAlignmentX(Component.CENTER_ALIGNMENT);
    saveSlot3.setPreferredSize(new Dimension(175, 75));
    saveSlot3.setMaximumSize(new Dimension(175, 75));
    saveSlot3.setFont(saveSlot3.getFont().deriveFont(20f));
    saveSlot3.setBackground(Colors.blue);
    saveSlot3.setForeground(Colors.yellow);
    saveSlot3.addActionListener(new loadSlotListener3());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(saveSlot3);

    saveSlot4 = new JButton(buttonTexts[3]);
	  saveSlot4.setAlignmentX(Component.CENTER_ALIGNMENT);
    saveSlot4.setPreferredSize(new Dimension(175, 75));
    saveSlot4.setMaximumSize(new Dimension(175, 75));
    saveSlot4.setFont(saveSlot4.getFont().deriveFont(20f));
    saveSlot4.setBackground(Colors.blue);
    saveSlot4.setForeground(Colors.yellow);
    saveSlot4.addActionListener(new loadSlotListener4());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(saveSlot4);

    //Set up main Panel
    mainPanel = new JPanel();
    mainPanel.setMinimumSize(new Dimension(360, 450));
    mainPanel.setMaximumSize(new Dimension(360, 450));
    mainPanel.setBackground(Colors.yellow);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    mainPanel.add(menuContents);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    mainPanel.add(back);

    this.setContents(mainPanel);
  }
}
