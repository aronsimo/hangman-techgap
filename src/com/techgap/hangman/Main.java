package com.techgap.hangman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	private static final String[] STATEMENT_ARRAY = new String[] {
			"abruptly", "bagpipes", "cobweb", "daiquiri", "embezzle", 
			"faking", "gabby", "haiku", "iatrogenic", "jackpot"
	};
	
	private String selectedWord;
	private String wordToBeGuessed;
	private int selectedWordLength;
	private int maximumAttempts;
	private int score;
	private Set<Character> wrongLetters;
	private Set<Character> guessedLetters;
	
	public String selectWord() {
		
		this.selectedWord = STATEMENT_ARRAY[(int) (Math.random() * STATEMENT_ARRAY.length)];
		this.selectedWordLength = selectedWord.length();
		
		System.out.println(this.selectedWord);
		
		StringBuilder sb = new StringBuilder("-");
		
		for(int i = 1; i < selectedWordLength; ++i) {
			sb.append("-");
		}
		
		this.wordToBeGuessed = sb.toString();
		
		wrongLetters = new HashSet<>();
		guessedLetters = new HashSet<>();
		maximumAttempts = selectedWordLength * 2;
		
		return this.selectedWord;
	}
	
	public boolean waitForInput() {
		
		System.out.println("Word: " + this.wordToBeGuessed);
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Guess: ");
		char character = keyboard.next(".").charAt(0);
		
		StringBuilder wordToBeGuessedSB = new StringBuilder(this.wordToBeGuessed);
		boolean guessed = false;
		
		if(!this.guessedLetters.contains(character)) {
			for (int i = 0; i < selectedWord.length(); i++){
			    char c = selectedWord.charAt(i);        
			    
			    if(c == character) {
			    	wordToBeGuessedSB.setCharAt(i, character);
			    	guessed = true;
			    }
			}
		}
		
		if(!guessed) {
			this.wrongLetters.add(character);
		} else {
			this.guessedLetters.add(character);
			this.wordToBeGuessed = wordToBeGuessedSB.toString();
			this.score += 10;
			
			if(this.wordToBeGuessed.equals(this.selectedWord)) {
				System.out.println();
				System.out.println("You have finished! The word was: " + this.wordToBeGuessed + " Your score is: " + this.score);
				return true;
			}
		}
		
		System.out.println("Misses: " + this.wrongLetters + " Your score is: " + this.score);
		return false;
	}
	
	public int getMaximumAttempts() {
		return maximumAttempts;
	}

	public static void main(String[] args) {
		
		Main main = new Main();
		main.selectWord();
		
		boolean solved = false;
		int attempt = 0;
		
		while(!solved && attempt++ < main.getMaximumAttempts()) {
			System.out.println("Attempt " + attempt + ":");
			solved = main.waitForInput();
			System.out.println();
		}
	}
}
