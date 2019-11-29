/**
 * @author Junaid Khan
 * TileBag class to store, draw, swap, and add tiles from the Tile class.
 */

import java.util.ArrayList;

public class TileBag {
    private ArrayList<Tile> tiles;

    public void TileBag(ArrayList<Tile> tilesArg) {
        this.tiles = new ArrayList<Tile>();
    }

    /**
     * @author Junaid Khan
     * @return returns random tiles from the Tile class.
     */
    public Tile drawRandomTile() {
        random.tiles = new ArrayList<Tile>();
    }

    /**
     * @author Juanid Khan
     * @param tile
     * @return swaps tiles with ArrayList from the Tile class
     */
    public Tile swapTile(Tile tile) {
        swap.tiles = new ArrayList<Tile>();
    }

    /**
     * @author Junaid Khan
     * @param tilesArg
     */
    public void addTiles(ArrayList<Tile> tilesArg) {
        add.tiles = new ArrayList<Tile>();
    }

    /**
     * @author Junaid Khan
     * @return gets all tiles from the Tile class using ArrayList.
     */
    public ArrayList<Tile> getAllTiles() {
        get.tiles = new ArrayList<Tile>();
    }
}
