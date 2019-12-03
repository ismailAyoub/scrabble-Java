/**
 * @author Junaid Khan
 * Word Score Calculator class to calculate score of each letter.
 */
package model.Junaid;
import model.claire.TilePlacement;
import model.claire.Tile;
import model.ismail.GameBoard;
import java.util.ArrayList;
import java.util.List;

public class WordScoreCalculator {
    private GameBoard board;
    private int score;

    /**
     * @author Junaid Khan
     * @param boardArg initializes board and score.
     */
    public WordScoreCalculator(GameBoard boardArg) {
        this.board = board;
        this.score = score;
    }

    /**
     * @author Junaid Khan
     * @param words words formed during the players turn are scored.
     */
    public void scoreWords(ArrayList<ArrayList<TilePlacement>> words) {
        int score = 0;
        int num = 1;
        int num2 = 1;
        ArrayList<TilePlacement> s = new ArrayList<TilePlacement>();
        List<Tile> currentTiles = board.getCurrentTiles();
        for(int i = 0; i < currentTiles.size(); i++) {
            Tile currTile = currentTiles.get(i);
            for(int j = 0; j < words.size(); j++) {
                switch (score) {
                    case 'A':
                        score += 1 * num;
                        break;
                    case 'B':
                        score += 3 * num;
                        break;
                    case 'C':
                        score += 3 * num;
                        break;
                    case 'D':
                        score += 2 * num;
                        break;
                    case 'E':
                        score += 1 * num;
                        break;
                    case 'F':
                        score += 4 * num;
                        break;
                    case 'G':
                        score += 2 * num;
                        break;
                    case 'H':
                        score += 4 * num;
                        break;
                    case 'I':
                        score += 1 * num;
                        break;
                    case 'J':
                        score += 8 * num;
                        break;
                    case 'K':
                        score += 5 * num;
                        break;
                    case 'L':
                        score += 1 * num;
                        break;
                    case 'M':
                        score += 3 * num;
                        break;
                    case 'N':
                        score += 1 * num;
                        break;
                    case 'O':
                        score += 1 * num;
                        break;
                    case 'P':
                        score += 3 * num;
                        break;
                    case 'Q':
                        score += 10 * num;
                        break;
                    case 'R':
                        score += 1 * num;
                        break;
                    case 'S':
                        score += 1 * num;
                        break;
                    case 'T':
                        score += 1 * num;
                        break;
                    case 'U':
                        score += 1 * num;
                        break;
                    case 'V':
                        score += 4 * num;
                        break;
                    case 'W':
                        score += 4 * num;
                        break;
                    case 'X':
                        score += 8 * num;
                        break;
                    case 'Y':
                        score += 4 * num;
                        break;
                    case 'Z':
                        score += 10 * num;
                        break;
                }
            }
            //score += tile;
        }
    }

    /**
     * @author Junaid Khan
     * @return returns score.
     */
    public int getScore() {
        return score;
    }
}