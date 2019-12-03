package model.ismail;
import model.claire.*;

import java.util.ArrayList;
import java.util.List;

/**
The GameBoard class sets up the game board(not GameBoardGUI).
@author Ismail Ayoub
*/
public class GameBoard {

    private Node board[][] = new Node[15][15];

    /**
    The constructor for GameBoard. Sets up the grid.
    */
    public GameBoard(){

        for(int i=0; i<15; i++){

            for(int j=0; j<15; j++) {
                board[i][j] = new Node();

            }

        }
        setBonus(board);

    }

    /**
    setBonus method sets up the bonus squares for specific letters that are place
    on top of the them.
    */
    private void setBonus(Node n[][]){

        n[0][0].setBonus("tw");
        n[0][3].setBonus("dl");
        n[0][7].setBonus("tw");
        n[0][11].setBonus("dl");
        n[0][14].setBonus("tw");

        n[1][1].setBonus("dw");
        n[1][5].setBonus("tl");
        n[1][9].setBonus("tl");
        n[1][13].setBonus("dw");

        n[2][2].setBonus("dw");
        n[2][6].setBonus("dl");
        n[2][8].setBonus("dl");
        n[2][12].setBonus("dw");

        n[3][0].setBonus("dl");
        n[3][3].setBonus("dw");
        n[3][7].setBonus("dl");
        n[3][11].setBonus("dw");
        n[3][14].setBonus("dl");

        n[4][4].setBonus("dw");
        n[4][10].setBonus("dw");

        n[5][1].setBonus("tl");
        n[5][5].setBonus("tl");
        n[5][9].setBonus("tl");
        n[5][13].setBonus("tl");

        n[6][2].setBonus("dl");
        n[6][6].setBonus("dl");
        n[6][8].setBonus("dl");
        n[6][12].setBonus("dl");

        n[7][0].setBonus("tw");
        n[7][3].setBonus("dl");
        n[7][7].setBonus("star");
        n[7][11].setBonus("dl");
        n[7][14].setBonus("tw");

        n[8][2].setBonus("dl");
        n[8][6].setBonus("dl");
        n[8][8].setBonus("dl");
        n[8][12].setBonus("dl");

        n[9][1].setBonus("tl");
        n[9][5].setBonus("tl");
        n[9][9].setBonus("tl");
        n[9][13].setBonus("tl");

        n[10][4].setBonus("dw");
        n[10][10].setBonus("dw");

        n[11][0].setBonus("dl");
        n[11][3].setBonus("dw");
        n[11][7].setBonus("dl");
        n[11][11].setBonus("dw");
        n[11][14].setBonus("dl");

        n[12][2].setBonus("dw");
        n[12][6].setBonus("dl");
        n[12][8].setBonus("dl");
        n[12][12].setBonus("dw");

        n[13][1].setBonus("dw");
        n[13][5].setBonus("tl");
        n[13][9].setBonus("tl");
        n[13][13].setBonus("dw");

        n[14][0].setBonus("tw");
        n[14][3].setBonus("dl");
        n[14][7].setBonus("tw");
        n[14][11].setBonus("dl");
        n[14][14].setBonus("tw");

    }

    /**
    printBoard method prints the game board in the console/terminal.
    */
    public void printBoard(){
        for(int i=0; i<15; i++){

            for(int j=0; j<15; j++){
                if(board[i][j].getTile() == null){
                    System.out.print('_' + " ");
                }
                else {
                    System.out.print(board[i][j].getTile().getLetter() + " ");
                }

            }
            System.out.println();
        }
    }

  /**
  setTile method place a tile onto the board.
  */
	public void setTile(int i, int j, Tile t)
	{
		board[i][j].setTile(t);
		board[i][j].setCurrent(true);
	}

	/**
  getTile method returns a tile that is in a certain position of the
  board.
  */
	public Tile getTile(int i, int j)
	{
		return board[i][j].getTile();
	}

    /**
    The setLetter places a letter onto the game board.
    */
    public void setLetter(int i, int j, char l){
        board[i][j].setLetter(l);
    }

    /**
    The getNode method returns a node object that represents a square.
    */
    public Node getNode(int i,int j){
        return board[i][j];
    }

    /**
    rollbackTurn method returns all the letters of the invalid word to the
    player and removes the letters off the board.
    */
    public void rollbackTurn(){
        for(int i = 0; i<15;i++){
            for (int j=0; j<15;j++){
                if(board[i][j].getCurrent() == true){
                    board[i][j].setTile(null);
					board[i][j].setLetter('_');
                    board[i][j].setCurrent(false);
                }
            }
        }
    }

    /**
    The finalizeTurn validize the word the player put on the board.
    */
    public void finalizeTurn(){
        for(int i = 0; i<15;i++){
            for (int j=0; j<15;j++){
                if(board[i][j].getCurrent() == true){
                    board[i][j].setCurrent(false);
                }
            }
        }
    }

    /**
    The getCurrentTiles method returns an array of Tiles that represents
    a word.
    */
    public List<Tile> getCurrentTiles(){
        List<Tile> current_Tiles = new ArrayList<Tile>();
        for(int i = 0; i<15;i++){
            for (int j=0; j<15;j++){
                if(board[i][j].getCurrent() == true){
                    current_Tiles.add(board[i][j].getTile());
                }
            }
        }
        return current_Tiles;
    }


	//I had to add this because the WordChecker requires an ArrayList of TilePlacements.
  /**
    The getCurrentTilePlacements returns an array of positions of each letter in
    a word placed by the player in the board.
	*/
	public List<TilePlacement> getCurrentTilePlacements(){
        List<TilePlacement> current_Tiles = new ArrayList<TilePlacement>();
        for(int i = 0; i<15;i++){
            for (int j=0; j<15;j++){
                if(board[i][j].getCurrent() == true){
                    current_Tiles.add(new TilePlacement(i, j, board[i][j].getTile()));
                }
            }
        }
        return current_Tiles;
    }


}
