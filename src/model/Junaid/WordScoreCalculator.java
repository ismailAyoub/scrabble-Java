/**
 * @author Junaid Khan
 * Word Score Calculator class to calculate score of each letter.
 */
package model.Junaid;
import model.claire.TilePlacement;
import model.ismail.GameBoard;
import java.util.ArrayList;

public class WordScoreCalculator {
    private GameBoard board;
    private int score;

    /**
     * @author Junaid Khan
     * @param boardArg
     */
    public WordScoreCalculator(GameBoard boardArg) {
        this.board = board;
        this.score = score;
    }

    /**
     * @author Junaid Khan
     * @param words used for score calculation.
     * @return returns score
     */
    public int scoreWords(ArrayList<ArrayList<TilePlacement>> words) {
        int score = 0;
        int num = 1;
        int num2 = 1;

        boolean bonus = false;
        for(int x = 0; x < words.size(); x++) {
            if(bonus) {
                if(getRand()%4==0&&getRand()%5==0)
                    num = 2;
                else if(getRand()%6==0&&getRand()%4==0&&getRand()%5==0)
                    num = 3;
                else
                    num = 1;
                if(num!=1)
                    System.out.print("Bonus points for the letter " + words.charAt(x) + " ! \7");
            }
            else
                num = 1;
            switch(words.charAt(x)) {
                case 'a': score += 1 * num;
                break;
                case 'b': score += 3 * num;
                break;
                case 'c': score += 3 * num;
                break;
                case 'd': score += 2 * num;
                break;
                case 'e': score += 1 * num;
                break;
                case 'f': score += 4 * num;
                break;
                case 'g': score += 2 * num;
                break;
                case 'h': score += 4 * num;
                break;
                case 'i': score += 1 * num;
                break;
                case 'j': score += 8 * num;
                break;
                case 'k': score += 5 * num;
                break;
                case 'l': score += 1 * num;
                break;
                case 'm': score += 3 * num;
                break;
                case 'n': score += 1 * num;
                break;
                case 'o': score += 1 * num;
                break;
                case 'p': score += 3 * num;
                break;
                case 'q': score += 10 * num;
                break;
                case 'r': score += 1 * num;
                break;
                case 's': score += 1 * num;
                break;
                case 't': score += 1 * num;
                break;
                case 'u': score += 1 * num;
                break;
                case 'v': score += 4 * num;
                break;
                case 'w': score += 4 * num;
                break;
                case 'x': score += 8 * num;
                break;
                case 'y': score += 4 * num;
                break;
                case 'z': score += 10 * num;
                break;
            }
        }
        if(bonus) {
            if(getRand()%5==0&&getRand()%2==0&&getRand()%2==0&&getRand()%2==0)
                num2 = 2;
            if(getRand()%5==0&&getRand()%4==0&&getRand()%3==0&&getRand()%3==0)
                num2 = 3;
            else
                num2 = 1;
            if(getRand()%6==0&&getRand()%5==0&&getRand()%4==0&&getRand()%3==0&&getRand()%2==0) {
                num2 = 0;
                System.out.println("You are very unlucky! You gain 0 points for this word!\7\7");
            }
            if(num2!=1&&num2!=0)
                System.out.println("Gained random bonus points! \7"); 
        }
        else
            num2 = 1;
        return score*num2;
    }

    /**
     * @author Junaid Khan
     * @return returns zero.
     */
    private int getRand() {
        return 0;
    }

    /**
     * @author Junaid Khan
     * @return returns score.
     */
    public int getScore() {
        return score;
    }
}