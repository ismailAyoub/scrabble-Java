package view.brian;

import view.claire.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.claire.*;
import model.ismail.*;
import model.*;
import java.io.File;

public class LoadGameMenuPanel extends MenuPanel {
  JPanel mainPanel;
  JPanel menuContents;
  JButton back;
  JButton loadSlot1,loadSlot2,loadSlot3,loadSlot4; // represent Saved GameState
  String [] buttonTexts = {"Save Game 1", "Save Game 2", "Save Game 3", "Save Game 4"};

	public class loadSlotListener1 implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame.txt");
      if(savegame.exists()){
        state.load(savegame);
      }
      parent.loadMenu("GameBoardGUI");
	   }
  }
	public class loadSlotListener2 implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame2.txt");
      if(savegame.exists()){
        state.load(savegame);
      }
      parent.loadMenu("GameBoardGUI");
    }
  }
	public class loadSlotListener3 implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame3.txt");
      if(savegame.exists()){
        state.load(savegame);
      }
      parent.loadMenu("GameBoardGUI");
    }
  }
	public class loadSlotListener4 implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      MainView parent = (MainView)SwingUtilities.getWindowAncestor((JButton)ae.getSource());
			GameState state = parent.getGameState();
      //File savegame1 = new File("S:\Users\Brian Huynh\Documents\GitHub\scrabble_cs401\src\model\ismail\saveGame.txt");
      //File savegame1 = new File("S:\...\...\...\...\scrabble_cs401\src\model\ismail\saveGame.txt");
      File savegame = new File("saveGame4.txt");
      if(savegame.exists()){
        state.load(savegame);
      }
      parent.loadMenu("GameBoardGUI");
    }
  }

	public LoadGameMenuPanel(JFrame parentFrame) {
    super("Load Game", 28);

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
	  loadSlot1 = new JButton(buttonTexts[0]);
	  loadSlot1.setAlignmentX(Component.CENTER_ALIGNMENT);
	  loadSlot1.setPreferredSize(new Dimension(175, 75));
    loadSlot1.setMaximumSize(new Dimension(175, 75));
    loadSlot1.setFont(loadSlot1.getFont().deriveFont(20f));
    loadSlot1.setBackground(Colors.blue);
    loadSlot1.setForeground(Colors.yellow);
    loadSlot1.addActionListener(new loadSlotListener1());
    menuContents.add(Box.createRigidArea(new Dimension(40, 30)));
    menuContents.add(loadSlot1);

    loadSlot2 = new JButton(buttonTexts[1]);
    loadSlot2.setAlignmentX(Component.CENTER_ALIGNMENT);
    loadSlot2.setPreferredSize(new Dimension(175, 75));
    loadSlot2.setMaximumSize(new Dimension(175, 75));
    loadSlot2.setFont(loadSlot2.getFont().deriveFont(20f));
    loadSlot2.setBackground(Colors.blue);
    loadSlot2.setForeground(Colors.yellow);
    loadSlot2.addActionListener(new loadSlotListener2());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(loadSlot2);

	  loadSlot3 = new JButton(buttonTexts[2]);
    loadSlot3.setAlignmentX(Component.CENTER_ALIGNMENT);
    loadSlot3.setPreferredSize(new Dimension(175, 75));
    loadSlot3.setMaximumSize(new Dimension(175, 75));
    loadSlot3.setFont(loadSlot3.getFont().deriveFont(20f));
    loadSlot3.setBackground(Colors.blue);
    loadSlot3.setForeground(Colors.yellow);
    loadSlot3.addActionListener(new loadSlotListener3());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(loadSlot3);

    loadSlot4 = new JButton(buttonTexts[3]);
	  loadSlot4.setAlignmentX(Component.CENTER_ALIGNMENT);
    loadSlot4.setPreferredSize(new Dimension(175, 75));
    loadSlot4.setMaximumSize(new Dimension(175, 75));
    loadSlot4.setFont(loadSlot4.getFont().deriveFont(20f));
    loadSlot4.setBackground(Colors.blue);
    loadSlot4.setForeground(Colors.yellow);
    loadSlot4.addActionListener(new loadSlotListener4());
    menuContents.add(Box.createRigidArea(new Dimension(40, 10)));
    menuContents.add(loadSlot4);



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
