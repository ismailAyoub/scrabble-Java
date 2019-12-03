/**
 * <h1>The Score Board</h1>
 * The scoreBoard class creates and updates the GUI Panel to display the Player scores
 * @author Max James Rave
 * @version 1.0
 * @since 11-25-2019
 */

public class TransferTile extends  AppContainer{
    //default character to test against
    char tileChar = '$';
    int rows = 0;
    int cols = 0;
    public TransferTile(char c)
    {
        tileChar = c;
    }
    public  TransferTile(int i,int j)
    {
        rows = i;
        cols = j;
    }
    public int getRows()
    {
        return rows;
    }
    public int getCols()
    {
        return cols;
    }
    public char getChar()
    {
        return tileChar;
    }
}
