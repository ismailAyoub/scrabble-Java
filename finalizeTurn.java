/**
 * <h1>The Tile Rack</h1>
 * The tileRack class is the class responsile for creating and updating the GUI for the Rack of tiles represented by JToggleButtons
 * @author Max James Rave
 * @version 1.0
 * @since 11-25-2019
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This is the central ActionListener method that is called when the buttons are selected
 * The Radio buttons untoggle all other buttons when pressed
 * @param ActionEvent e used for finding the source of the ActionEvent i.e. which button
 * @return Nothing.
 */
public class finalizeTurn extends JPanel implements ActionListener{
    private JPanel turnPanel;
    private JButton turnButton;
    finalizeTurn(){
        turnPanel = new JPanel();
        turnButton = new JButton("End Turn");
        turnButton.setBackground(Color.red);
        turnPanel.add(turnButton);
        turnButton.addActionListener((ActionListener) this);
        add(turnPanel);
    }
    public void actionPerformed(ActionEvent e)
    {
        /*
            Gamestate Method to increment player turn
        */
        System.out.println("THis shit works");
    }
}
