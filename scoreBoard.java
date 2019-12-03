/**
 * <h1>The Score Board</h1>
 * The scoreBoard class creates and updates the GUI Panel to display the Player scores
 * @author Max James Rave
 * @version 1.0
 * @since 11-25-2019
 */

import javax.swing.*;
import java.awt.*;

public class scoreBoard extends JPanel {
    private JPanel scorePanel;

    private JLabel score1,score2,score3,score4;
    private int sc1,sc2,sc3,sc4 = 0;

    private static JLabel NAMEscore = new JLabel("Score Board");

    /**
     * This is the constructor to create the Labels and Layouts
     * @return Nothing
     */
    scoreBoard () {
        GridLayout grid = new GridLayout(5,1);
        score1 = new JLabel();
        score1.setLayout(grid);
        score1.setText("Player 1 : " +  sc1);
        score2 = new JLabel();
        score2.setLayout(grid);
        score2.setText("Player 2 : " +  sc1);
        score3 = new JLabel();
        score3.setLayout(grid);
        score3.setText("Player 3 : " +  sc1);
        score4 = new JLabel();
        score4.setLayout(grid);
        score4.setText("Player 4 : " +  sc1);
        scorePanel = new JPanel();
        scorePanel.setBackground(Color.cyan);
        scorePanel.setPreferredSize(new Dimension(150,100));
        NAMEscore.setLayout(grid);
        scorePanel.add(NAMEscore,grid);
        scorePanel.add(score1,grid);
        scorePanel.add(score2,grid);
        scorePanel.add(score3,grid);
        scorePanel.add(score4,grid);
        add(scorePanel);
    }
    /**
     * This is the constructor to create the Labels and Layouts
     *
     */
    public void updateScore() {
        /*
            This is where GameState method to access the score will go
         */
    }
}