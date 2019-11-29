package model.temp;

import model.claire.*;
import model.ismail.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class TileBag
{
	private ArrayList<Tile> tiles;
	public TileBag()
	{
		tiles = new ArrayList<Tile>();
	}
	
	public void init(File f) throws IOException
	{
		if (f.exists())
		{
			String ln = "";
			String points = "";
			String letter = "";
			String num = "";
			Scanner s = new Scanner(f);
			while (s.hasNext())
			{
				points = "";
				letter = "";
				num = "";
				ln = s.nextLine();
				letter += ln.charAt(0);
				
				num += ln.charAt(2);
				if (ln.charAt(3) != '\t')
				{
					num += ln.charAt(3);
					points += ln.charAt(5);
					try 
					{
						points += ln.charAt(6);
					}
					catch (Exception e)
					{
						
					}
				}
				else
				{
					points += ln.charAt(4);
					try 
					{
						points += ln.charAt(5);
					}
					catch (Exception e)
					{
						
					}
				}
				
				for (int i = 0 ; i < Integer.parseInt(num); i++)
				{
					tiles.add(new Tile(Integer.parseInt(points), letter.charAt(0)));
				}
			}
		}
	}
	
	public Tile drawRandomTile()
	{
		Random rand = new Random();
		int tile = rand.nextInt(tiles.size());
		return tiles.remove(tile);
	}
	
	public int tilesLeft()
	{
		return tiles.size();
	}
	
	public void addTiles(ArrayList<Tile> tilesArg)
	{
		for (int i = 0; i < tilesArg.size(); i++)
		{
			tiles.add(tilesArg.get(i));
		}
	}
	
	public ArrayList<Tile> getAllTiles()
	{
		return (ArrayList<Tile>)tiles.clone();
	}
	
	public void printTileBag()
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			Tile t = tiles.get(i);
			System.out.println("Tile: " + t.getLetter() + " Points: " + t.getPoints());
		}
	}
}