package model.ismail;
import model.claire.*;

public class Node {
    private boolean empty = true;
    private boolean left = false,right = false,top = false,bottom = false;
    private String bonus;
    private char letter;
	private boolean current;
	private Tile tile;
    Node(){
        letter = '_';
        bonus = "none";
		tile = null;
		current = false;
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
    public void setLeft(boolean b){
        this.left = b;
    }
    public void setRight(boolean c){
        this.right = c;
    }
    public void setTop(boolean d){
        this.top = d;
    }
    public void setBottom(boolean e){
        this.bottom = e;
    }
    public void setBounce(String f){
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
	}

    public boolean isEmpty(){
        return empty;
    }
    public boolean isLeft(){
        return left;
    }
    public boolean isRight(){
        return right;
    }
    public boolean isTop(){
        return top;
    }
    public boolean isBottom(){
        return bottom;
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
		current = false;
	}
	public boolean getCurrent()
	{
		return current;
	}
}
