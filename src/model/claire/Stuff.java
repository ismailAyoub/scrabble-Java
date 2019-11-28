package model.claire;
import java.util.Scanner;
import java.io.*;




public class Stuff
{
	public static void main(String [] args) throws IOException
	{
		ScrabbleTrie st = new ScrabbleTrie();
		st.addWordList(new File("model/claire/CollinsScrabbleWords2019.txt"), Difficulty.WORD);
		String ln = "";
		File f = new File("model/claire/Scrabble_EASY.old.txt");
		Scanner scan = new Scanner(f);
		FileWriter fw = new FileWriter("Scrabble_EASY.txt");
		PrintWriter pw = new PrintWriter(fw);
		int numEasy = 0;
		while (scan.hasNext())
		{
			
			ln = scan.nextLine();
			if (st.isWord(ln, Difficulty.WORD))
			{
				numEasy++;
				pw.println(ln);
			}
			
		}
		pw.close();
		System.out.println(numEasy + " words added to file.");
		
		
		int numHard = 0;
		fw = new FileWriter("Scrabble_HARD.txt");
		pw = new PrintWriter(fw);
		File f2 = new File("model/claire/Scrabble_HARD.old.txt");
		scan = new Scanner(f2);
		while (scan.hasNext())
		{
			
			ln = scan.nextLine();
			if (st.isWord(ln, Difficulty.WORD))
			{
				numHard++;
				pw.println(ln);
			}
			
		}
		pw.close();
		System.out.println(numHard + " words added to file.");
		
	}
	
	
}