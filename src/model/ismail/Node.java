package model.ismail;
import model.claire.*;


public class Node {
    private boolean empty;
    private String bonus;
    private char letter;
	private boolean current;
	private Tile tile;
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
    public void setEmpty(boolean a){
        this.empty = a;
    }
    
    public void setBonus(String f){
        this.bonus= f;
    }
    public void setLetter(char g){
        this.letter = g;
		empty = false;
    }
	public void setTile(Tile t)
	{
		this.tile = t;
		empty = false;
		current = true;
	}

    public boolean isEmpty(){
        return empty;
    }
    
    public String getBounce(){
        return bonus;
    }
    public char getLetter(){
        return letter;
    }
	public Tile getTile()
	{
		return tile;
	}
	public void setCurrent(boolean c)
	{
		current = c;
	}
	public boolean getCurrent()
	{
		return current;
	}
}
