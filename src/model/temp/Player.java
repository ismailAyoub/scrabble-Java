package model.temp;

import model.ismail.*;
import model.claire.*;
import java.util.ArrayList;
public class Player
{
	private String name;
	private int score;
	private  Tile[] tileRack;
	
	public Player(String name, int points)
	{
		this.name = name;
		score = points;
		tileRack = new Tile[7];
	}
	
	public void addTile(Tile t)
	{
		for (int i = 0; i < tileRack.length; i++)
		{
			if (tileRack[i] == null)
			{
				tileRack[i] = t;
			}
		}
	}
	
	public void removeTile(Tile t)
	{
		for (int i = 0; i < tileRack.length; i++)
		{
			if (tileRack[i] != null)
			{
				
				if (tileRack[i].getLetter() == t.getLetter())
				{
					tileRack[i] = null;
					return;
				}
			}
		}
	}
	public void removeTile(int i)
	{
		tileRack[i] = null;
	}
	public void removeTile(char t)
	{
		for (int i = 0; i < tileRack.length; i++)
		{
			if (tileRack[i] != null)
			{
				if (tileRack[i].getLetter() == t)
				{
					tileRack[i] = null;
					return;
				}
			}
			
		}
		
	}
	public void setPoints(int i)
	{
		score = i;
	}
	public void addPoints(int i)
	{
		score += i;
	}
	public int getPoints()
	{
		return score;
	}
	public String getName()
	{
		return name;
	}
	public Tile[] getTileRack()
	{
		return tileRack;
	}
}