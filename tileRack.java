/**
 * <h1>The Tile Rack</h1>
 * The tileRack class is the class responsible for creating and updating the GUI for the Rack of tiles represented by JToggleButtons
 * @author Max James Rave
 * @version 1.0
 * @since 11-26-2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**
 * This is the constructor which initilized the buttons and assigns them to a FlowLayout JPanel
 * @return Nothing.
 */
public class tileRack extends AppContainer implements ActionListener{
    private JPanel tilePanel;
    private static int rackLength = 7;
    private JToggleButton[] tiles = new JToggleButton[rackLength];
    TransferTile t1,t2,t3,t4,t5,t6,t7;
    tileRack(){
        tilePanel = new JPanel();
        tilePanel.setBackground(Color.darkGray);
        tilePanel.setPreferredSize(new Dimension(500,100));
        tilePanel.setLayout(new FlowLayout());
        for(int i = 0;i < rackLength;i++) {
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            tiles[i] = new JToggleButton(String.valueOf(c));
            /*
                This is where the Tilebag class would be invoked instead of the random letters put in now
             */
            tilePanel.add(tiles[i]);
            tiles[i].addActionListener((ActionListener) this);
        }
        add(tilePanel);
    }
    /**
     * This is the central ActionListener method that is called when the buttons are selected
     * The Radio buttons untoggle all other buttons when pressed
     * @param ActionEvent e used for finding the source of the ActionEvent i.e. which button
     * @return Nothing.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == tiles[0])
        {
            JRadioButton o = (JRadioButton)e.getSource();
            String name = o.getName();
            char c = name.charAt(0);
            t1 = new TransferTile(c);
            tiles[1].setSelected(false);
            tiles[2].setSelected(false);
            tiles[3].setSelected(false);
            tiles[4].setSelected(false);
            tiles[5].setSelected(false);
            tiles[6].setSelected(false);
            tiles[7].setSelected(false);
            tiles[7].setName(name);
            tilePanel.revalidate();
            tilePanel.updateUI();
            String tempString = String.valueOf(o);
            super.mainTransfer.tileChar = tempString.charAt(0);
        }
        if(e.getSource() == tiles[1])
        {
            tiles[0].setSelected(false);
            tiles[2].setSelected(false);
            tiles[3].setSelected(false);
            tiles[4].setSelected(false);
            tiles[5].setSelected(false);
            tiles[6].setSelected(false);
        }
        if(e.getSource() == tiles[2])
        {
            tiles[1].setSelected(false);
            tiles[0].setSelected(false);
            tiles[3].setSelected(false);
            tiles[4].setSelected(false);
            tiles[5].setSelected(false);
            tiles[6].setSelected(false);
        }
        if(e.getSource() == tiles[3])
        {
            tiles[1].setSelected(false);
            tiles[2].setSelected(false);
            tiles[0].setSelected(false);
            tiles[4].setSelected(false);
            tiles[5].setSelected(false);
            tiles[6].setSelected(false);
        }
        if(e.getSource() == tiles[4])
        {
            tiles[1].setSelected(false);
            tiles[2].setSelected(false);
            tiles[3].setSelected(false);
            tiles[0].setSelected(false);
            tiles[5].setSelected(false);
            tiles[6].setSelected(false);
        }
        if(e.getSource() == tiles[5])
        {
            tiles[1].setSelected(false);
            tiles[2].setSelected(false);
            tiles[3].setSelected(false);
            tiles[4].setSelected(false);
            tiles[0].setSelected(false);
            tiles[6].setSelected(false);
        }
        if(e.getSource() == tiles[6])
        {
            tiles[1].setSelected(false);
            tiles[2].setSelected(false);
            tiles[3].setSelected(false);
            tiles[4].setSelected(false);
            tiles[5].setSelected(false);
            tiles[0].setSelected(false);
        }
    }
}