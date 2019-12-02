package view.temp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.claire.*;
import view.claire.*;
import model.Junaid.*;
import model.*;
import java.util.ArrayList;


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
	

	private class TileButton extends JButton
	{
		int posx;
		int posy;
		public int getXVal()
		{
			return posx;
		}
		public int getYVal()
		{
			return posy;
		}
		public TileButton(int x, int y)
		{
			posx = x;
			posy = y;
		}
	}
	private class BoardListener implements ActionListener{
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
	
	private class RackListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			for (int i = 0; i < 7; i++)
			{
				rackButtons[i].setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			}
			currentSelectedIndex = Integer.parseInt(ae.getActionCommand());
			currentSelected = tileRackArr[currentSelectedIndex];
			rackButtons[currentSelectedIndex].setBorder(BorderFactory.createLineBorder(Colors.red, 4));
		}
	}
	
	private class FinalizeListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent ae)
		{
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
				view.getGameState().nextTurn();
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
					}
					catch (Exception e) {
						rackButtons[i] = new JButton("");
						rackButtons[i].setVisible(false);
					}
				}
				
				
				
				tileRackPanel.revalidate();
				tileRackPanel.repaint();
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
			
			placedTiles.clear();
			currentPlayerName.setText(view.getGameState().getCurrentPlayer().getName());
		}
		
	}
	
	public AppContainer(MainView view)
	{
		this.view = view;
		if (view.getGameState().getAllPlayers().size() == 0)
		{
			view.getGameState().addPlayer("User", 0);
		}
		this.setMinimumSize(new Dimension(1000, 660));
		this.setPreferredSize(new Dimension(1000, 660));
		this.setBackground(Colors.blue);
		currentSelected = null;
		currentSelectedIndex = -1;
		placedTiles = new ArrayList<TilePlacement>();
		tileRack = new JPanel();
		tileRackPanel = new JPanel();
		tileRackPanel.setPreferredSize(new Dimension(980, 100));
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
				tileButton[i][j].setMinimumSize(new Dimension(30, 30));
				tileButton[i][j].setBorder(BorderFactory.createLineBorder(Colors.blue, 2));
				tileButton[i][j].addActionListener(new BoardListener());
				boardPanel.add(tileButton[i][j]);
			}
		}
		finalizePanel = new JPanel();
		finalizePanel.setPreferredSize(new Dimension(100, 510));
		finalizePanel.setMaximumSize(new Dimension(100, 510));
		JButton finalize = new JButton("Finalize");
		finalize.addActionListener(new FinalizeListener());
		finalizePanel.add(finalize);
		
		tileBagPanel = new JPanel();
		tileBagPanel.setPreferredSize(new Dimension(200, 510));
		tileBagPanel.setMaximumSize(new Dimension(200, 510));
		
		currentPlayer = new JLabel();
		currentPlayerName = new JLabel();
		currentPlayer.setText("Current Player:");
		currentPlayerName.setText( view.getGameState().getCurrentPlayer().getName());
		playerPoints = new JPanel();
		playerPoints.setPreferredSize(new Dimension(95, 200));
		playerPointsLabel = new JTextArea();
		playerPointsLabel.setEditable(false);
		setPlayerPoints();
		playerPoints.add(currentPlayer);
		playerPoints.add(currentPlayerName);
		playerPoints.add(Box.createRigidArea(new Dimension(94, 20)));
		playerPoints.add(playerPointsLabel);
		tileBagPanel.add(playerPoints);
		this.add(tileBagPanel);
		
		this.add(boardPanel);
		this.add(finalizePanel);
		this.add(tileRackPanel);
		//this.pack();
		this.revalidate();
		this.repaint();
	}
	
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
}