package view.brian;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import view.claire.*;
import model.ismail.*;
import model.Junaid.*;
import model.*;

public class MenuBar{
  JMenuBar menuBar;
  JMenu mainMenu;
  JMenuItem saveGame, loadGame, exit, rules, settings;

  public class rulesListener implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      JDialog textBox = new JDialog("Rules");
      textBox.setVisible(true);
    }
  }

  public MenuBar(){

    menuBar = new JMenuBar();
    mainMenu = new JMenu("Main Menu");

    rules = new JMenuItem("Rules");
    rules.setActionCommand("Rules");
    rules.addActionListener(new rulesListener());

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
