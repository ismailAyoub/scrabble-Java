package model.claire;
import model.ismail.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordCheckerDriver
{
	
	public static void main(String[] args) throws IOException
	{
			boolean firstTurn = true;
			ScrabbleTrie t2 = new ScrabbleTrie();
			t2.addWordList(new File("CollinsScrabbleWords2019.txt"), Difficulty.WORD);
			System.out.println("Is her a word?? " + t2.isWord("her"));
			GameBoard board1 = new GameBoard();
			GameBoard dummyBoard = board1;
			ScrabbleTrie sc = new ScrabbleTrie();
			sc.initialize("CollinsScrabbleWords2019.txt", "Scrabble_EASY.txt", "Scrabble_HARD.txt");
			System.out.println("How about now?? " + sc.isWord("her"));
			WordChecker w = new WordChecker(sc, board1);
			ArrayList<TilePlacement> m = new ArrayList<TilePlacement>();
			m.add(new TilePlacement(1,1,new Tile(1, 'h')));
			m.add(new TilePlacement(1,1,new Tile(1, 'e')));
			m.add(new TilePlacement(1,1,new Tile(1, 'r')));
			String lol = WordChecker.convertTilePlacementsToString(m);
			System.out.println("How about now?? " + sc.isWord(lol));
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
				ArrayList<ArrayList<TilePlacement> > arr = new ArrayList<ArrayList<TilePlacement> >();
				w.addTilePlaced(new TilePlacement(i, j, new Tile(1, l)));
				
				System.out.println("Check word?");
				String inp = input.nextLine();
				inp = input.nextLine();
				if (inp.equals("Y") || inp.equals("y"))
				{
					System.out.println(w.validatePlacement() ? 
								("Valid placement: " + w.getAlignment()) :
								("Invalid placement.") );
					
					if (firstTurn)
						arr = w.validateTilesFirstTurn();
					else
						arr = w.validateTiles();
					for (ArrayList<TilePlacement> wordd : arr) {
						String result = w.convertTilePlacementsToString(wordd);
						System.out.println("Word Placed: " + result);
						System.out.print("\n");
					}
					
					//w.dumpTilesToBoard();
					System.out.print("\n\n\nTHE BOARD: \n");
					board1.printBoard();
					
					System.out.print("\n\n--------------------------------------------------------------------------\n\n");
					w.reset();
				}
			}


	}
	
}