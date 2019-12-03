/**
 * <h1>The Scrabble Board</h1>
 * The Board class creates the GUI for the scrabble game board that will be responsible for creating and updating the Scabble Board made up of 225 individual buttons to represent each space
 * @author Max James Rave
 * @version 1.0
 * @since 11-25-2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends AppContainer implements ActionListener{
    private JPanel boardPanel;
    private JButton[][] buttons = new JButton[15][15];
    private static int numTiles = 225;
    public Board() {
        boardPanel = new JPanel();
        boardPanel.setBackground(Color.green);
        boardPanel.setLayout(new GridLayout(15,15));
        int counter = 0;
        for(int i = 0;i< numTiles; i++) {
            int j = 0;
            buttons[j][counter] = new JButton();
            buttons[j][counter].setPreferredSize(new Dimension(75, 50));
            System.out.println(buttons[j][counter]);
            boardPanel.add(buttons[j][counter]);
            buttons[j][counter].addActionListener((ActionListener)this);
            counter++;
            if (counter == 14) {
                j++;
                counter = 0;
                if (j == 14) {
                    break;
                }
            }

        }
        /*
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        add(boardPanel);
         */
        add(boardPanel);
    }
    /**
     * This is the constructor for Board
     * @param a this is the rows
     * @param b this is the colms
     * @param d the character to be put in the new Board
     * @return Nothing.
     */
    public Board(int a,int b, char d) {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(15,15));
        int counter = 0;
        for(int i = 0;i< numTiles; i++) {
            int j = 0;
            buttons[j][counter] = new JButton();
            buttons[j][counter].setPreferredSize(new Dimension(15, 15));
            boardPanel.add(buttons[j][counter]);
            buttons[j][counter].addActionListener((ActionListener)this);
            counter++;
            if (counter == 14) {
                j++;
                counter = 0;
                if (j == 14) {
                    break;
                }
            }

        }
        String tempName;
        tempName = String.valueOf(d);
        buttons[a][b].setName(tempName);
        boardPanel.add(buttons[a][b]);
        add(boardPanel);
    }
    public void actionPerformed(ActionEvent e)
    {
        int j = 0;
        int  counter = 0;
        for (int i = 0; i < numTiles; i++) {
              if(e.getSource() == buttons[j][counter])
              {
                  this.buttons[j][counter].setName("TEST");
                  String temp1 = buttons[j][counter].getName();
                  char c = temp1.charAt(0);
                  buttons[j][counter].revalidate();
                  buttons[j][counter].repaint();
                  super.mainTransfer.cols = counter;
                  super.mainTransfer.rows = j;
                  Board board = new Board(j,counter,c);
              }
             else if(counter == 14) {
                j++;
                counter = 0;
                if (j == 14) {
                    break;
                }
            }
            counter++;
        }
    }
}
