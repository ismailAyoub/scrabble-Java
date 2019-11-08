package model.claire;

import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

public class ScrabbleTrie
{
	protected static class TrieNode {
		
		//The array of child TrieNodes, will be one for each letter in the alphabet.
		private TrieNode [] nexts;
		
		//There are multiple difficulty settings. 
		//The TrieNode must store whether the current Node marks the end of a valid word (i.e.
		//	in the Scrabble dictionary), as well as a word that the AI is allowed to play
		//	in EASY, HARD, or NIGHTMARE modes
		private HashSet<Difficulty> validity;
		
		public TrieNode()
		{
			validity = new HashSet<Difficulty>();
			nexts = new TrieNode[26];	//Array elements initialized to NULL automatically, thanks Java
		}
		public void putValidMarker(Difficulty setting, Boolean valid)
		{
			if (valid == true)
				validity.add(setting); 
			else
				validity.remove(setting);
		}
		public boolean getValidMarker(Difficulty setting)
		{
			return validity.contains(setting);	//Will unbox the Boolean wrapper in the HashMap and return it
		}
		public TrieNode getNext(char next)
		{
			//Example: Capital A is feff0041.
			return nexts[next - 0x41];	//Capital A == nexts[0].
		}
		public void setNext(char nextChar, TrieNode nextNode)
		{
			nexts[nextChar - 0x41] = nextNode;
		}
	}
	
	//ScrabbleTrie is meant to be built once and then left alone (queried only). Therefore,
	//	no mutators to delete words.
	private TrieNode root;
	
	public ScrabbleTrie()
	{
		root = new TrieNode();
	}
	
	public void addWordList(File f, Difficulty difficulty) throws IOException
	{
		Scanner scanner = new Scanner(f);
		while (scanner.hasNext())
		{
			String word = scanner.nextLine().toUpperCase();
			//System.out.println(word);
			this.addWord(word, difficulty);
		}
		scanner.close();
	}
	public void addWord(String word, Difficulty difficulty)
	{
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++)
		{
			if (current.getNext(word.charAt(i)) == null)
			{
				current.setNext(word.charAt(i), new TrieNode());
			}
			current = current.getNext(word.charAt(i));
		}
		//At end of word, set the marker in the TrieNode to indicate a valid word.
		current.putValidMarker(difficulty, true);
	}
	public boolean isWord(String word, Difficulty difficulty)
	{
		word = word.toUpperCase();
		TrieNode current = root;
		TrieNode next = null;
		for (int i = 0; i < word.length(); i++)
		{
			next = current.getNext(word.charAt(i));
			if (next == null)
			{
				return false;
			}
			else
			{
				current = next;
			}
		}
		return current.getValidMarker(difficulty);
	}
	public boolean isWord(String word)
	{
		return isWord(word, Difficulty.WORD);
	}
	
	public HashSet<String> generateWords(String tiles)
	{
		HashSet<String> words = new HashSet<String>();
		ArrayList chars = new ArrayList<Character>();
		for (int i = 0; i < tiles.length(); i++)
		{
			chars.add(tiles.charAt(i));
		}
		generateHelper(words, root, new char[7], 0, chars);
		return words;
	}
	//Generate words through traversal of the Trie??
	private void generateHelper(HashSet<String> words, TrieNode currentNode, char [] word, int currentIndex, ArrayList<Character> tiles)
	{
		if (currentIndex != -1 && currentNode.getValidMarker(Difficulty.WORD))
		{
			words.add(new String(word));
		}
		
		if (tiles.isEmpty())
		{
			return;
		}
		else
		{
			TrieNode n;
			char currentTile;
			int tileSize = tiles.size();
			for (int i = 0; i < tileSize; i++)			//For each tile left in the tile rack...
			{
				currentTile = tiles.get(i);				//Get current tile.
				
				n = currentNode.getNext(currentTile);	//Use the trie to see if the current tile, added to the 
														//	current word-in-progress, is a word or a prefix to a valid word.
														//Do this by retrieving the trie node corresponding to that character:
														//	If the trie node is null, the current tile does not create a valid 
														//	word or prefix.
														
				if (n != null)							//If n != null, then recursively call the method to keep building the word.
				{
					currentIndex++;						//Next index in the word being built
					word[currentIndex] = currentTile;	//Add the current tile to the word being built
					tiles.remove(i);					//Take current tile out of the tiles-to-be-used set
					generateHelper(words, n, word, currentIndex, tiles);	//Recursively call the function to continue building the word
					
					tiles.add(i, currentTile);		//After recursive call, reset everything to previous state.
					word[currentIndex] = 0;
					currentIndex--;
				}
			}
		}
	}
}