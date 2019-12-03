package view.temp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.claire.*;
import view.claire.*;
import model.Junaid.*;
import model.*;
import java.util.ArrayList;

/**
	The AppContainer class contains the GUI game board, the players' point display, and the buttons used to
		place tiles on the GUI game board and end the current turn.
	@author Claire Campbell
*/
public class AppContainer extends JPanel
{
	private JPanel boardPanel;
	private JPanel finalizePanel;
	private MainView view;
	private JPanel tileBagPanel;
	private JPanel tileRackPanel;
	private JPanel tileRack;
	private javax.swing.JButton tileButton[][];
	private Tile currentSelected;
	private Tile [] tileRackArr;
	private JButton rackButtons[];
	private int currentSelectedIndex;
	private ArrayList<TilePlacement> placedTiles;
	private JPanel playerPoints;
	private JTextArea playerPointsLabel;
	private JLabel currentPlayer;
	private JLabel currentPlayerName;


	/**
		This JButton-derived class represents a space on the game board GUI.
		@author Claire Campbell
	*/
	private class TileButton extends JButton
	{
		int posx;
		int posy;

		/**
			The getXVal() method returns the row on the board that this space is in.
			@return The row on the board that this space is in.
		*/
		public int getXVal()
		{
			return posx;
		}

		/**
			The getYVal() method returns the column on the board that this space is in.
			@return The row on the board that this space is in.
		*/
		public int getYVal()
		{
			return posy;
		}

		/**
			The constructor sets the row and column that the current button is in on the board.
			@param x The row on the board.
			@param y The column on the board.
		*/
		public TileButton(int x, int y)
		{
			posx = x;
			posy = y;
		}
	}

	/**
		This class is the action listener for the game board.
		@author Claire Campbell
	*/
	private class BoardListener implements ActionListener{

		/**
			This action listener method places the selected tile/button from the tile rack onto the board.
			@parm ae The event object generated by the board button that the user clicked on.
		*/
		public void actionPerformed(ActionEvent ae)
		{
			TileButton src = (TileButton)ae.getSource();
			if (currentSelected != null)
			{
				////here x=row and y=col lol
				int x = src.getXVal();
				int y = src.getYVal();
				if (tileButton[x][y].getText() == "" && currentSelectedIndex != -1)
				{
					tileButton[x][y].setText("" + tileRackArr[currentSelectedIndex].getLetter());
					currentSelected = null;
					rackButtons[currentSelectedIndex].setText("");
					placedTiles.add(new TilePlacement(x, y, tileRackArr[currentSelectedIndex]));
					tileRackArr[currentSelectedIndex] = null;
					currentSelectedIndex = -1;
					tileRackPanel.revalidate();
					tileRackPanel.repaint();


				}
				else if (tileButton[x][y].getText() != "" && currentSelectedIndex != -1)
				{

					for (int i = 0 ;i < placedTiles.size(); i++)
					{
						if (placedTiles.get(i).getRow() == x && placedTiles.get(i).getCol() == y)
						{
							TilePlacement tp = placedTiles.remove(i);
							placedTiles.add(new TilePlacement(x, y, tileRackArr[currentSelectedIndex]));
							tileButton[x][y].setText("" + tileRackArr[currentSelectedIndex].getLetter() );

							tileRackArr[currentSelectedIndex] = tp.getTile();
							String g = "" + tileRackArr[currentSelectedIndex].getLetter();
							rackButtons[currentSelectedIndex].setText(g);

							currentSelected = null;
							currentSelectedIndex = -1;
							tileRackPanel.revalidate();
							tileRackPanel.repaint();
							break;
						}
					}

				}
				else
				{

				}
			}
		}
	}

	/**
		This class is the action listener for the GUI tile rack's tiles/buttons.
		@author Claire Campbell
	*/
	private class RackListener implements ActionListener
	{
		/**
			This action listener method sets the tile/button in the tilerack that the player clicked on as "selected".
			@parm ae The event object generated by the tile rack button that the user clicked on.
		*/
		public void actionPerformed(ActionEvent ae)
		{
			for (int i = 0; i < 7; i++)
			{
				rackButtons[i].setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
				rackButtons[i].setMinimumSize(new Dimension(40, 40));
				rackButtons[i].setPreferredSize(new Dimension(40, 40));
			}
			currentSelectedIndex = Integer.parseInt(ae.getActionCommand());
			currentSelected = tileRackArr[currentSelectedIndex];
			rackButtons[currentSelectedIndex].setBorder(BorderFactory.createLineBorder(Colors.red, 4));
			tileRackPanel.revalidate();
			tileRackPanel.repaint();
			view.revalidate();
			view.repaint();
		}
	}

	/**
		This class is the action listener for the Finalize button, which checks the current turn for validity and
			then saves the current turn's tiles and score (if the current turn is valid) in the game state.
		@author Claire Campbell
	*/
	private class FinalizeListener implements ActionListener
	{
		/**
			This method checks the current turn for validity and then saves the current turn's tiles and score (if the current turn is valid) in the game state.
			If the tiles placed on the board, are invalid, it removes those tiles from the GUI board.
			It then advances to the next turn by getting the next player and displaying the appropriate info on the GUI
				to indicate whose turn it is.
			@param ae The event object generated by the Finalize button.

		*/
		public void actionPerformed(ActionEvent ae)
		{
			for (int i = 0; i < placedTiles.size(); i++)
			{
				System.out.println(placedTiles.get(i).getTile().getLetter());
			}

			view.getGameState().finalize(placedTiles, tileRackArr);
			if (view.getGameState().isCurrentTurnValid())
			{
				ArrayList<ArrayList<TilePlacement> > tp = view.getGameState().getCurrentWords();
				ArrayList<String> strs = new ArrayList<String>();
				for (int i = 0; i < tp.size(); i++)
				{
					strs.add(WordChecker.convertTilePlacementsToString(tp.get(i)));
				}
				String strs1 = "";
				for (int i = 0; i < strs.size(); i++)
				{
					strs1 += strs.get(i);
				}
				JOptionPane.showMessageDialog(null, "The following words were placed on the board: \n" + strs);
				setPlayerPoints();
				tileRackPanel.revalidate();
				tileRackPanel.repaint();

				view.getGameState().drawTiles(tileRackArr);
				view.getGameState().printBoard();
				view.getGameState().nextTurn();
				setPlayerPoints();
				JOptionPane.showMessageDialog(null, "Please give the computer to " + view.getGameState().getCurrentPlayer().getName());

				tileRackArr = new Tile[7];
				rackButtons = new JButton[7];
				tileRackPanel.removeAll();
				for (int i = 0; i < 7; i++)
				{
					try {
						Tile temp = view.getGameState().getCurrentPlayer().getTileRack()[i];
						tileRackArr[i] = new Tile(temp.getPoints(), temp.getLetter());
						rackButtons[i] = new JButton(" " + temp.getLetter());
						rackButtons[i].setActionCommand("" + i);
						rackButtons[i].setMinimumSize(new Dimension(40, 40));
						rackButtons[i].addActionListener(new RackListener());
						tileRackPanel.add(rackButtons[i]);

						try {
							rackButtons[i].setMinimumSize(new Dimension(40, 40));
							rackButtons[i].setPreferredSize(new Dimension(40, 40));
						}
						catch (Exception e) {}

							tileRackPanel.revalidate();
							tileRackPanel.repaint();
						}
					catch (Exception e) {
						rackButtons[i] = new JButton("");
						rackButtons[i].setVisible(false);
					}
				}


				tileRackPanel.removeAll();
				for (int i = 0; i < rackButtons.length; i++)
				{
					tileRackPanel.add(rackButtons[i]);
				}

				for (int i = 0; i < 15; i++)
				{
					for (int j = 0; j < 15; j++)
					{
						try {
							tileButton[i][j].setText("" + view.getGameState().getTilePlacedAt(i, j).getLetter());
						}
						catch (Exception ie)
						{

						}
					}
				}
				tileRackPanel.revalidate();
				tileRackPanel.repaint();
				view.revalidate();
				view.repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No valid words placed!");
				for (int i = 0; i < placedTiles.size(); i++)
				{
					tileButton[placedTiles.get(i).getRow()][placedTiles.get(i).getCol()].setText("");
				}
				placedTiles.clear();

				tileRackPanel.removeAll();
				view.getGameState().nextTurn();
				setPlayerPoints();
				JOptionPane.showMessageDialog(null, "Please give the computer to " + view.getGameState().getCurrentPlayer().getName());
				tileRackArr = new Tile[7];
				rackButtons = new JButton[7];
				for (int i = 0; i < 7; i++)
				{
					try {
						Tile temp = view.getGameState().getCurrentPlayer().getTileRack()[i];
						tileRackArr[i] = new Tile(temp.getPoints(), temp.getLetter());
						rackButtons[i] = new JButton(" " + temp.getLetter());
						rackButtons[i].setActionCommand("" + i);
						rackButtons[i].addActionListener(new RackListener());
						rackButtons[i].setMinimumSize(new Dimension(40, 40));
						rackButtons[i].setPreferredSize(new Dimension(40, 40));
						tileRackPanel.add(rackButtons[i]);
					}
					catch (Exception e) {
						rackButtons[i] = new JButton("");
						rackButtons[i].setVisible(false);
					}
				}

				//JOptionPane.showMessageDialog(null, "Please give the computer to " + view.getGameState().getCurrentPlayer().getName());
				tileRackPanel.revalidate();
				tileRackPanel.repaint();
			}
			currentPlayerName.setText(view.getGameState().getCurrentPlayer().getName());
			tileRackPanel.revalidate();
			tileRackPanel.repaint();
			view.revalidate();
			view.repaint();
			placedTiles.clear();
			
		}

	}

	/**
		The constructor for the AppContainer creates the game board, the player score display, the tile rack, and the
			finalize button and displays them on the panel.
		@param view The JFrame-derived window class that contains the AppContainer panel.
	*/
	public AppContainer(MainView view)
	{
		this.view = view;
		if (view.getGameState().getAllPlayers().size() == 0)
		{
			view.getGameState().addPlayer("User", 0);
		}
		view.setJMenuBar(setMenuBar());
		this.setMinimumSize(new Dimension(1000, 660));
		this.setPreferredSize(new Dimension(1000, 660));
		this.setBackground(Colors.blue);
		currentSelected = null;
		currentSelectedIndex = -1;
		placedTiles = new ArrayList<TilePlacement>();
		tileRack = new JPanel();
		tileRackPanel = new JPanel();
		tileRackPanel.setPreferredSize(new Dimension(510+ 300, 70));
		tileRackPanel.setMinimumSize(new Dimension(980, 100));
		rackButtons = new JButton[7];

		tileRackArr = new Tile[7];
		for (int i = 0; i < 7; i++)
		{
			try {
				Tile temp = view.getGameState().getCurrentPlayer().getTileRack()[i];
				tileRackArr[i] = new Tile(temp.getPoints(), temp.getLetter());
				rackButtons[i] = new JButton(" " + temp.getLetter());
				rackButtons[i].setActionCommand("" + i);
				rackButtons[i].setMinimumSize(new Dimension(40, 40));
				rackButtons[i].addActionListener(new RackListener());

				tileRackPanel.add(rackButtons[i]);
			}
			catch (Exception e) {
				rackButtons[i] = new JButton("");
				rackButtons[i].setVisible(false);
			}
		}

		//this.setLayout(new BorderLayout());
		boardPanel = new JPanel();
		tileButton = new javax.swing.JButton[15][15];
		boardPanel.setLayout(new GridLayout(15, 15));
		boardPanel.setMinimumSize(new Dimension(510, 510));
		boardPanel.setPreferredSize(new Dimension(510, 510));
		boardPanel.setMaximumSize(new Dimension(510, 510));
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				tileButton[i][j] = new TileButton(i, j);
				tileButton[i][j].setText("");
				tileButton[i][j].setVisible(true);
				if (view.getGameState().getNode(i, j).getBounce().equals("dl"))
				{
					tileButton[i][j].setBackground(new Color(180, 180, 250));
				}
				if (view.getGameState().getNode(i, j).getBounce().equals("tl"))
				{
					tileButton[i][j].setBackground(new Color(250, 190, 190));
				}
				tileButton[i][j].setMinimumSize(new Dimension(30, 30));
				tileButton[i][j].setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
				tileButton[i][j].addActionListener(new BoardListener());
				boardPanel.add(tileButton[i][j]);
			}
		}
		finalizePanel = new JPanel();
		finalizePanel.setPreferredSize(new Dimension(150, 510));
		finalizePanel.setMaximumSize(new Dimension(150, 510));
		finalizePanel.add(Box.createRigidArea(new Dimension(145, 40)));
		JButton finalize = new JButton("Finalize");
		finalize.addActionListener(new FinalizeListener());
		finalizePanel.add(finalize);

		tileBagPanel = new JPanel();
		tileBagPanel.setPreferredSize(new Dimension(200, 510));
		tileBagPanel.setMaximumSize(new Dimension(200, 510));
		tileBagPanel.add(Box.createRigidArea(new Dimension(195, 40)));

		currentPlayer = new JLabel();
		currentPlayer.setMinimumSize(new Dimension(150, 300));
		currentPlayerName = new JLabel();
		currentPlayer.setText("Current Player:");
		currentPlayerName.setText( view.getGameState().getCurrentPlayer().getName());
		playerPoints = new JPanel();
		playerPoints.setPreferredSize(new Dimension(150, 200));
		playerPointsLabel = new JTextArea();
		playerPointsLabel.setMinimumSize(new Dimension(120, 100));
		playerPointsLabel.setPreferredSize(new Dimension(120, 100));
		playerPointsLabel.setEditable(false);
		setPlayerPoints();
		playerPoints.add(currentPlayer);
		playerPoints.add(currentPlayerName);
		playerPoints.add(Box.createRigidArea(new Dimension(94, 20)));
		playerPoints.add(playerPointsLabel);
		tileBagPanel.add(playerPoints);

		tileRackPanel.setBackground(Colors.yellow);
		tileRackPanel.setBorder(BorderFactory.createLineBorder(Colors.red, 4));
		playerPoints.setBackground(Colors.yellow);
		playerPoints.setBorder(BorderFactory.createLineBorder(Colors.red, 4));
		finalizePanel.setBackground(Colors.yellow);
		tileBagPanel.setBackground(Colors.yellow);
		//view.setVisible(false);
		//this.view.setJMenuBar(new view.brian.MenuBar());
		//view.setVisible(true);
		JPanel bonuses = new JPanel();
		bonuses.setPreferredSize(new Dimension(140, 150));
		bonuses.setMinimumSize(new Dimension(140, 150));
		JButton dl = new JButton("Double Letter");
		JButton tl = new JButton("Triple Letter");
		dl.setBackground(new Color(180, 180, 250));
		tl.setBackground(new Color(250, 190, 190));
		bonuses.setBackground(Colors.yellow);
		bonuses.setBorder(BorderFactory.createLineBorder(Colors.red, 4));
		bonuses.add(new JLabel("Bonuses:"));
		bonuses.add(Box.createRigidArea(new Dimension(140, 10)));
		bonuses.add(dl);
		bonuses.add(tl);

		finalizePanel.add(Box.createRigidArea(new Dimension(140, 30)));
		finalizePanel.add(bonuses);
		this.add(tileBagPanel);
		this.add(boardPanel);
		this.add(finalizePanel);
		this.add(tileRackPanel);
		//this.pack();
		this.revalidate();
		this.repaint();
		view.revalidate();
		view.repaint();

	}

	/**
		This method updates the player points display to reflect the points earned by all players.
	*/
	private void setPlayerPoints()
	{
		String str = "";
		ArrayList<Player> players = view.getGameState().getAllPlayers();
		for (int i = 0; i < view.getGameState().getAllPlayers().size(); i++)
		{
			str += (players.get(i).getName() + ": " + players.get(i).getPoints() + "\n");
		}
		playerPointsLabel.setText(str);
	}

	/**
		This method sets up the menubar when inside GameBoard GameBoardGUI
		Retuns JMenuBar object to Frame to set up menu bar.
		@author Brian Huynh
	*/
	private JMenuBar setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
    JMenu mainMenu = new JMenu("Main Menu");

    JMenuItem rules = new JMenuItem("Rules");
    rules.setActionCommand("Rules");
    rules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				view.loadMenu("Rules");
			}
		});

    JMenuItem settings = new JMenuItem("Settings");
    settings.setActionCommand("OptionsMenu");
		settings.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				view.loadMenu("OptionsMenu");
			}
		});

    JMenuItem saveGame = new JMenuItem("Save Game");
    saveGame.setActionCommand("SaveGameMenu");
		saveGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				view.loadMenu("SaveGameMenu");
			}
		});

    JMenuItem loadGame = new JMenuItem("Load Game");
    loadGame.setActionCommand("LoadGameMenu");
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				view.loadMenu("LoadGameMenu");
			}
		});

    JMenuItem exit = new JMenuItem("Exit Main Menu");
    exit.setActionCommand("StartMenu");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				view.loadMenu("StartMenu");
			}
		});

    mainMenu.add(saveGame);
    mainMenu.add(loadGame);
    mainMenu.add(exit);

    menuBar.add(mainMenu);
    menuBar.add(rules);
    menuBar.add(settings);

		return menuBar;
	}
}
