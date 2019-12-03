package model.ismail;
import model.claire.*;


/**
 This is the Node class and it will store all the information need in one block on the board.
 @author Ismail Ayoub
 */
public class Node {
    private boolean empty;
    private String bonus;
    private char letter;
	private boolean current;
	private Tile tile;

	/**
	 This is the Node constructor and will initialize all the valuables in the class.
	 */
	Node(){
        letter = '_';
        bonus = "none";
		tile = null;
		current = false;
		empty = true;

    }
    Node(char i){
        letter = i;
		empty = false;
		current = true;
    }
	Node(Tile t)
	{
		this.tile = t;
		empty = false;
		current = true;
	}

	/**
	 setEmpty changes the value of Empty.
	 @param a can be true or false.
	 */
	public void setEmpty(boolean a){
        this.empty = a;
    }
	/**
	 setBonus palces the right bonus at the right place.
	 @param f is a string that tells the program what the String is.
	 */
    public void setBonus(String f){
        this.bonus= f;
    }
    public void setLetter(char g){
        this.letter = g;
		empty = false;
    }
	/**
	 setTile places a new tile on the board.
	 @param t is the new tile that is about to be placed.
	 */
    public void setTile(Tile t)
	{
		this.tile = t;
		empty = false;
		current = true;
	}

	/**
	 isEmpty is used to know if a string is empty or not.
	 @return  a can be true or false.
	 */
	public boolean isEmpty(){
        return empty;
    }
	/**
	 getBonus is used to know what type of bonus the node has.
	 @return  a String that describes the bonus.
	 */
    public String getBounce(){
        return bonus;
    }
	/**
	 getLetter is used to get the letter in the node.
	 @return  a char that is the letter.
	 */
    public char getLetter(){
        return letter;
    }
	/**
	 getTile is used to get the tile in the node.
	 @return  a tile object.
	 */
	public Tile getTile()
	{
		return tile;
	}
	/**
	 setCurrent is used to know what tiles were placed this turn.
	 @param c is true or false.
	 */
	public void setCurrent(boolean c)
	{
		current = c;
	}
	/**
	 getCurrent is used to know what tile were placed this turn.
	 @return  true or false.
	 */
	public boolean getCurrent()
	{
		return current;
	}
}

