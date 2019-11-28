/**
 * @author Junaid Khan
 * Player class for storing and getting name. Adding, setting, and getting points.
 * adding tiles and removing tiles. Also getting tile rack from the Tile class.
 */
public class Player {
    private String name;
    private int playerScore;
    private Tile[] tileRack;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }
    /**
     * @author Junaid Khan
     * @param tile
     */
    public void addTile(Tile tile) {
        tiles += tile;
        // or tiles.add(tile);
    }

    /**
     * @author Junaid Khan
     * @param tile
     */
    public void removeTile(char tile) {
        tiles.remove(n);
    }

    /**
     * @author Junaid Khan
     * @param tile
     */
    public void removeTile(int tile) {
        tiles.remove(n);
    }

    /**
     * @author Junaid Khan
     * @return tiles gets tiles from the Tile class
     */
    public Tile[] getTitleRack() {
        return tiles.get(n);
    }

    /**
     * @author Junaid Khan
     * @param p
     */
    public void addPoints(int p) {
        score += p;
    }

    /**
     * @author Junaid Khan
     * @param p
     */
    public void setPoints(int p) {
        this.points = points;
    }

    /**
     * @author Junaid Khan
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @author Junaid Khan
     * @return name
     */
    public String getName() {
        return name;
    }
}
