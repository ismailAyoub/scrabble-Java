/**
 * <h1>The AppContainer/h1>
 * The scoreBoard class creates and updates the GUI Panel to display the Player scores
 * @author Max James Rave
 * @version 1.0
 * @since 11-24-2019
 */

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppContainer extends JPanel{
    TransferTile mainTransfer;
    private static void createAndShowGUI(){
        AppContainer appContainer = new AppContainer();
        JFrame Mainframe = new JFrame("Scrabble");
        Mainframe.setSize(400,400);
        Mainframe.setLayout(new BorderLayout());
        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainframe.add(appContainer);
        Mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        /*
            GUI component variables
         */

        PlayerTurn turnBoard = new PlayerTurn();
        tileRack tileBoard = new tileRack();
        Board board = new Board();
        finalizeTurn turnButton = new finalizeTurn();
        scoreBoard scores = new scoreBoard();
        /*
            Adding components to AppContainer Frame
         */
        Mainframe.add(board,BorderLayout.CENTER);
        Mainframe.add(tileBoard,BorderLayout.PAGE_END);

        Mainframe.add(turnBoard,BorderLayout.EAST);
        Mainframe.setBackground(Color.cyan);
        Mainframe.add(scores,BorderLayout.WEST);
        Mainframe.add(turnButton,BorderLayout.NORTH);
        Mainframe.pack();
        Mainframe.setVisible(true);
    }
    public void TransferTiles(char c)
    {
        mainTransfer.tileChar = c;
    }
    public void TransferTiles(int x,int y)
    {
        mainTransfer.rows = x;
        mainTransfer.cols = y;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
