import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class PlayerTurn extends JPanel{
    private JPanel playerPanel;

    PlayerTurn() {
        /*
            This is where GameState method to get Player Turn would go
         */
        playerPanel = new JPanel();
        try {
            BufferedImage myImage = ImageIO.read(new File("C:\\Users\\mjame\\IdeaProjects\\ScrabbleFinal\\src\\images\\Player1.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myImage));
            picLabel.setSize(25,25);
            playerPanel.add(picLabel);
        }
        catch(IOException e)
        {

        }
        add(playerPanel);
    }
}
