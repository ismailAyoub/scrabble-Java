package model.claire;
import model.ismail.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCheckerDriver
{
	


	public static void main(String[] args)
	{
			GameBoard board1 = new GameBoard();
			GameBoard dummyBoard = board1;
			WordChecker w = new WordChecker(new ScrabbleTrie(), board1);
////Note-----Code copy+pasted from model.ismail/////////////////////////////////////////////
			
			board1.printBoard();
			System.out.println();
			board1.getNode(0,0).getBounce();
			String words[] = {"cat","dog","house","car","bike"};

			Scanner input = new Scanner(System.in);
			String loop = "yes";
			while (loop.equals("yes")){
				System.out.print("Enter the letter you want to place: ");
				char l = input.next().charAt(0);
				System.out.print("Enter position 'i' (row) in array:");
				int i = input.nextInt();
				System.out.print("Enter position 'j' (col) in array:");
				int j = input.nextInt();
				dummyBoard.setLetter(i,j,l);
				dummyBoard.setTile(i, j, new Tile(1, l));
				dummyBoard.getNode(i, j).setCurrent(true);
				dummyBoard.printBoard();
				//System.out.print("Do you want to input another letter? 'yes' or 'no' ");
				//loop = input.next();
///Note------End of stolen code/////////////////////////////////////////////////////////////
				ArrayList<ArrayList<Tile> > arr = new ArrayList<ArrayList<Tile> >();
				w.addTilePlaced(new TilePlacement(i, j, new Tile(1, l)));
				
				System.out.println("Check word?");
				String inp = input.nextLine();
				inp = input.nextLine();
				if (inp.equals("Y") || inp.equals("y"))
				{
					System.out.println(w.validatePlacement() ? 
								("Valid placement: " + w.getAlignment()) :
								("Invalid placement.") );
					arr = w.validateWord();
					for (ArrayList<Tile> wordd : arr) {
						String result = w.convertToString(wordd);
						System.out.println("Word Placed: " + result);
						System.out.print("\n");
					}
					
					w.dumpTilesToBoard();
					System.out.print("\n\n\nTHE BOARD: ");
					board1.printBoard();
					
					System.out.print("\n\n--------------------------------------------------------------------------\n\n");
					w.reset();
				}
			}


	}
	
}