package model.Junaid;
import model.ismail.*;
import model.claire.*;
import java.util.ArrayList;
/**
 * @author Junaid Khan
 * Player class for storing and getting name. Adding, setting, and getting points.
 * adding tiles and removing tiles. Also getting tile rack from the Tile class.
 */
public class Player {
    private String name;
    private int score;
    private Tile[] tileRack;

    /**
     * @author Junaid Khan
     * @param name name of the current player
     * @param points points for current player
     */
    public Player(String name, int points) {
        this.name = name;
        score = points;
        tileRack = new Tile[7];
    }

    /**
     * @author Junaid Khan
     * @param tile adds tiles  from the tileRack array.
     */
    public void addTile(Tile tile) {
        for(int i = 0; i < tileRack.length; i++) {
            if (tileRack[i] == null) {
                tileRack[i] = tile;
                return;
            }
        }
    }

    /**
     * @autjor Junaid Khan
     * @param t removes tiles from the array
     */
    public void removeTile(Tile t) {
        for(int i = 0; i < tileRack.length; i++) {
            if (tileRack[i] != null) {
                if (tileRack[i].getLetter() == t.getLetter()) {
                    tileRack[i] = null;
                    return;
                }
            }
        }
    }

    /**
     * @author Junaid Khan
     * @param t variable used to remove tiles from the tileRack array.
     */
    public void removeTile(int t) {
        tileRack[t] = null;
    }

    /**
     * @author Junaid Khan
     * @param t variable used to remove character tiles.
     */
    public void removeTile(char t) {
        for(int i = 0; i < tileRack.length; i++) {
            if (tileRack[i] != null) {
                if (tileRack[i].getLetter() == t) {
                    tileRack[i] = null;
                    return;
                }
            }
        }
    }

    /**
     * @author Junaid Khan
     * @return returns tiles from the tileRack.
     */
    public Tile[] getTileRack() {
        return tileRack;
    }

    /**
     * @author Juanid Khan
     * @param p varaible used to add points
     */
    public void addPoints(int p) {
        score += p;
    }

    /**
     * @author Junaid Khan
     * @param p variable used to set score.
     */
    public void setPoints(int p) {
        score = p;
    }

    /**
     * @author Junaid Khan
     * @return returns score for current player.
     */
    public int getPoints() {
        return score;
    }

    /**
     * @author Junaid Khan
     * @return returns name for current player.
     */
    public String getName() {
        return name;
    }
}
