package model.claire;
/////IGNORE THIS FILE FOR THE FINAL PROTOTYPE THIS JUST DEMONSTRATES THE SCRABBLETRIE
import java.util.Scanner;
import java.io.*;
public class ScrabbleTrieDriver
{
	public static void main(String [] args) throws IOException{
		char a = 'A';
		int i = a;
		System.out.println(i);
		File f = new File("CollinsScrabbleWords2019.txt");
		ScrabbleTrie st = new ScrabbleTrie();
		st.addWordList(f, Difficulty.WORD);
		Scanner s = new Scanner(System.in);
		String input = "";
		Scanner f2;
		while (input != "-1")
		{
			
			System.out.println("Enter a word: ");
			input = s.nextLine();
			boolean b;
			long before = System.currentTimeMillis();
			b = st.isWord(input);
			long after = System.currentTimeMillis();
			System.out.println(input +  (b ? " is a word!" : " is not a word!")
				+ "\n\tOperation took: " + (after - before) + " milliseconds");
				
				
			before = System.currentTimeMillis();
			f2 =  new Scanner("CollinsScrabbleWords2019.txt");
			b = false;
			while(f2.hasNext())
			{
				String fStr = f2.nextLine();
				if (fStr.equals(input))
				{
					b = true;
					System.out.println("\tFound in file!");
					break;
				}
				else
				{
					
				}
			}
			f2.close();
			
			
			after = System.currentTimeMillis();
			System.out.println("\tReading from file took: " + (after - before) + " milliseconds");
			
		}
	}
}