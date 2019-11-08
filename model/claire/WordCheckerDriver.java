package model.claire;
import model.ismail.*;
import java.util.Scanner;

public class WordCheckerDriver
{
	


	public static void main(String[] args)
	{
			WordChecker w = new WordChecker(new ScrabbleTrie(), new GameBoard());
////Note-----Code copy+pasted from model.ismail/////////////////////////////////////////////
			GameBoard board1 = new GameBoard();
			board1.printBoard();
			System.out.println();
			board1.getNode(0,0).getBounce();
			String words[] = {"cat","dog","house","car","bike"};

			Scanner input = new Scanner(System.in);
			String loop = "yes";
			while (loop.equals("yes")){
				System.out.print("Enter the letter you want to place: ");
				char l = input.next().charAt(0);
				System.out.print("Enter position 'i' in array:");
				int i = input.nextInt();
				System.out.print("Enter position 'j' in array:");
				int j = input.nextInt();
				board1.setLetter(i,j,l);
				board1.setTile(i, j, new Tile(1, l));
				board1.printBoard();
				//System.out.print("Do you want to input another letter? 'yes' or 'no' ");
				//loop = input.next();
///Note------End of stolen code/////////////////////////////////////////////////////////////
				
				w.addTilePlaced(new TilePlacement(i, j, new Tile(1, l)));
				
				System.out.println("Check word?");
				String inp = input.nextLine();
				if (inp.equals("Y") || inp.equals("y"))
				{
					
					w.reset();
				}
			}


	}
	
}