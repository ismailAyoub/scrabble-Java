package model.claire;

import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

/**
	The ScrabbleTrie is a tree-like structure that emulates a Scrabble dictionary.
	It stores all words that the user player is allowed to play during their turn, as well
	as all words associated with each Difficulty setting, represented as enum constants.
	@author Claire Campbell
*/
public class ScrabbleTrie
{
	/**
		The TrieNode represents a node within the ScrabbleTrie trie/tree structure.
		A path from the root TrieNode to any TrieNode with a "marker" (represented by a Difficulty constant)
		represents a valid word.
		@author Claire Campbell
	*/
	protected static class TrieNode 
	{
		
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
	
	public void initialize(String scrabbleDictionary, String easyWordList, String hardWordList) throws IOException
	{
		File scrabbleDictionaryFile = new File(scrabbleDictionary);
		File easyWordFile = new File(easyWordList);
		File hardWordFile = new File(hardWordList);
		
		//Add the words in the Scrabble Dictionary to the scrabble trie.
		addWordList(scrabbleDictionaryFile, Difficulty.WORD);
		
		int easywords = 0;
		//Scan through Scrabble_EASY.txt and add only those words in the Scrabble Dictionary
		String current = "";
		Scanner scanner = new Scanner(easyWordFile);
		while (scanner.hasNext())
		{
			current = scanner.nextLine().toUpperCase();
			if (isWord(current, Difficulty.WORD))
			{
				addWord(current, Difficulty.EASY);
				easywords++;
			}
		}
		
		int hardwords = 0;
		//Scan through Scrabble_HARD.txt and add only those words in the Scrabble Dictionary
		current = "";
		scanner = new Scanner(hardWordFile);
		while (scanner.hasNext())
		{
			current = scanner.nextLine().toUpperCase();
			if (isWord(current, Difficulty.WORD))
			{
				addWord(current, Difficulty.HARD);
				hardwords++;
			}
		}
		
		System.out.println("Easy words added: " + easywords);
		System.out.println("Hard words added: " + hardwords);
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
	
	public void addWord(String word, Difficulty difficulty) throws IOException
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
}