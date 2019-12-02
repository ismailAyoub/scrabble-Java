package view.brian;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import view.claire.*;
import model.ismail.*;
import model.Junaid.*;
import model.*;

/**
  The MenuBar class is a menu bar which allow players to access certain
  menus such as load game, save game, settings, or rules.
  @author Brian Huynh
*/
public class MenuBar extends JMenuBar{
  JMenuBar menuBar;
  JMenu mainMenu;
  JMenuItem saveGame, loadGame, exit, rules, settings;

  /**
    The rulesListener is an ActionListener for the rules JButton.
    This will bring up the JDialog rules window containing the rules.
  */
  /*public class rulesListener implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      JDialog textBox = new JDialog("Rules");
      textBox.setVisible(true);
    }
  }*/

  /**
  The default constructor for MenuBar. Sets up the menubar to be displayed in
  the GameBoardGUI
  */
  public MenuBar(){

    menuBar = new JMenuBar();
    mainMenu = new JMenu("Main Menu");

    rules = new JMenuItem("Rules");
    rules.setActionCommand("Rules");
    rules.addActionListener(new NavigationListener());

    settings = new JMenuItem("Settings");
    settings.setActionCommand("OptionsMenu");
    settings.addActionListener(new NavigationListener());

    saveGame = new JMenuItem("Save Game");
    saveGame.setActionCommand("SaveGameMenu");
    saveGame.addActionListener(new NavigationListener());

    loadGame = new JMenuItem("Load Game");
    loadGame.setActionCommand("LoadGameMenu");
    loadGame.addActionListener(new NavigationListener());

    exit = new JMenuItem("Exit Main Menu");
    exit.setActionCommand("StartMenu");
    exit.addActionListener(new NavigationListener());

    mainMenu.add(saveGame);
    mainMenu.add(loadGame);
    mainMenu.add(exit);

    menuBar.add(mainMenu);
    menuBar.add(rules);
    menuBar.add(settings);
	
    }
}
