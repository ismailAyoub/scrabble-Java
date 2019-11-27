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
		
		private TrieNode [] nexts;				//The array of child TrieNodes, will be one for each letter in the alphabet.
		
		private HashSet<Difficulty> validity;	//There are multiple difficulty settings. 
												//The TrieNode must store whether the current Node marks the end of a valid word (i.e.
												//	in the Scrabble dictionary), as well as a word that the AI is allowed to play
												//	in EASY, HARD, or NIGHTMARE modes
		
		/**
			The TrieNode constructor. Creates an "empty" TrieNode which does not reference any other TrieNodes.
		*/
		public TrieNode()
		{
			validity = new HashSet<Difficulty>();
			nexts = new TrieNode[26];	//Array elements initialized to NULL automatically, thanks Java
		}
		
		/**
			The putValidMarker() method places a validity marker for the specified Difficulty constant in the current TrieNode.
			This marker indicates that the TrieNode marks the end of a valid word for the specified Difficulty.
			@param setting A Difficulty enum constant; the difficulty setting that the validity marker should be related to.
			@param valid A boolean. If true, a validity marker is placed; if false, a validity marker for the specified Difficulty is removed if one exists.
		*/
		public void putValidMarker(Difficulty setting, Boolean valid)
		{
			if (valid == true)
				validity.add(setting); 
			else
				validity.remove(setting);
		}
		
		/**
			The getValidMarker() method is called to check if the current TrieNode has a validity marker for the given difficulty.
			A validity marker indicates that the current TrieNode marks the end of a valid word for the specified difficulty.
			@param setting A Difficulty enum constant representing the difficulty setting.
			@return The boolean value true if a validity marker is present in the current TrieNode for the specified Difficulty (i.e. if
					the current TrieNode marks the end of a valid word for the specified Difficulty); the boolean value false if no validity marker is 
					present in the current TrieNode for the specified Difficulty.
		*/
		public boolean getValidMarker(Difficulty setting)
		{
			return validity.contains(setting);	//Will unbox the Boolean wrapper in the HashMap and return it
		}
		
		/**
			The getNext() method retrieves and returns the TrieNode representing the specified character, if one exists.
			@param next A character representing the next character in the string stored in the ScrabbleTrie's TrieNodes.
			@return Returns a TrieNode representing the "next" character in the string relative to the current TrieNode, if the "next" character's TrieNode exists.
					Returns a null reference if the "next" character has no corresponding TrieNode reference in the current TrieNode.
		*/	
		public TrieNode getNext(char next)
		{
			//Example: Capital A is feff0041.
			return nexts[next - 0x41];	//Capital A == nexts[0].
		}
		
		/**
			The setNext() method associates a TrieNode with a character, indicating that the character might be added to the prefix
				represented by the current TrieNode to create another valid prefix.
			@param nextChar The character to add to the current prefix.
			@param nextNode The TrieNode to associate with the nextChar character.
		*/
		public void setNext(char nextChar, TrieNode nextNode)
		{
			nexts[nextChar - 0x41] = nextNode;
		}
	}
	
	
	private TrieNode root;		//ScrabbleTrie is meant to be built once and then left alone (queried only). Therefore,
								//	no mutators to delete words.
	
	/**
		The constructor for the ScrabbleTrie; creates an empty ScrabbleTrie.
	*/
	public ScrabbleTrie()
	{
		root = new TrieNode();
	}
	
	/**
		The initialize() method initializes the ScrabbleTrie by filling it with the words in the specified plaintext files.
		@param scrabbleDictionary A String representing the name of a plaintext, newline-delimited file of English words. This file represents the Scrabble Dictionary used by the program.
		@param easyWordList A String representing the name of a plaintext, newline-delimited file of English words. This file represents the list of words that the AI Player is allowed to use when the game is in easy mode.
		@param hardWordList A String representing the name of a plaintext, newline-delimited file of English words. This file represents the list of words that the AI Player is allowed to use when the game is in hard mode.
		@throws IOException If any one of the filenames provided do not represent a valid file on disk, or if the files specified cannot be opened or read from.
	*/
	public void initialize(String scrabbleDictionary, String easyWordList, String hardWordList) throws IOException
	{
		File scrabbleDictionaryFile = new File(scrabbleDictionary);
		File easyWordFile = new File(easyWordList);
		File hardWordFile = new File(hardWordList);
		
		//Add the words in the Scrabble Dictionary to the scrabble trie.
		addWordList(scrabbleDictionaryFile, Difficulty.WORD);
		
		int easywords = 0;	//for debug
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
		
		int hardwords = 0;	//for debug
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
		
		//Debug stuff
		//System.out.println("Easy words added: " + easywords);
		//System.out.println("Hard words added: " + hardwords);
	}
	
	/**
		The addWordList() method adds a list of words to the ScrabbleTrie and associates each word with the specified Difficulty constant.
		@param f A plaintext, newline-delimited file containing English words. This represents the file of words to add to the ScrabbleTrie. 
				The file must contain no punctuation or numbers; only English letters and newlines.
		@param difficulty The Difficulty setting/enum constant to associate the words in the file f with.
		@throws IOException If the File f does not represent a valid file, or if the file cannot be opened or read from.
	*/
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
	
	/**
		The addWord() method adds a single word to the ScrabbleTrie and associates it with the specified Difficulty constant.
		@param word A String containing the word to add. Must be a string of English letters, containing no numbers or punctuation.
		@param difficulty The Difficulty setting/enum constant to associate the word with.
	*/
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
	
	/**
		The isWord() method checks if the given word is stored within the ScrabbleTrie and associated with the given Difficulty.
		@param word The word to search for within the ScrabbleTrie.
		@param difficulty The Difficulty constant to check if the word is associated with.
		@return The boolean value true if the specified word is stored in the ScrabbleTrie and associated with the specified difficulty;
				the boolean value false if the specified word is not stored in the ScrabbleTrie or not associated with the specified difficulty.
	*/
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
	
	/**
		The isWord() method checks if the given word is stored within the ScrabbleTrie and associated with the WORD Difficulty constant.
		@param word The word to search for within the ScrabbleTrie.
		@return The boolean value true if the specified word is stored in the ScrabbleTrie and associated with the Difficulty.EASY constant;
				the boolean value false if the specified word is not stored in the ScrabbleTrie or not associated with the Difficulty.EASY constant.
	*/
	public boolean isWord(String word)
	{
		return isWord(word, Difficulty.WORD);
	}
	
}