package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {
	private LLIntegerNode guessListHead;
	private LLIntegerNode guessListTail;
	private LLIntegerNode candidateListHead;
	private LLIntegerNode candidateListTail;
	private LLIntegerNode newCandidateListHead;
	private LLIntegerNode newCandidateListTail;
	private int guess = 1000;
	private boolean guessedcorrectly = false;
	
	// TODO: declare data members as necessary

	
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		guessListHead = null;
		guessListTail = guessListHead;
		candidateListHead = candidateListTail = null;
		for(int i = 1000; i < 10000; i++)
			insertCandidateList(i);
		
		// TODO
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		guessListHead = null;
		guessListTail = guessListHead;
		candidateListHead = candidateListTail = null;
		guessedcorrectly = false;
		for(int i = 1000; i < 10000; i++)
			insertCandidateList(i);
		// TODO
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		LLIntegerNode temp = guessListHead;
		while(temp!= null) {
			if(temp.getInfo() == n)
				return true;
			temp = temp.getLink();
		}
		
		// TODO
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		int count = 0;
		LLIntegerNode temp = guessListHead;
		while(temp!= null) {
			count++;
			temp = temp.getLink();
			
			
		}
		// TODO
		return count;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		int matches = 0;
		while(a!= 0 && b != 0) {
			if(a%10 == b%10)
				matches++;
			a = a/10;
			b = b/10;
			
			
		}
		return matches;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		if(guessedcorrectly) {
			return true;
		}
		if(candidateListHead == null) {
		// TODO
		return true;
		}
		return false;
	}
	
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		if(candidateListHead == null) {
			
		guess = 1000;
		}
		
		else guess = candidateListHead.getInfo();
		
		insertGuessList(guess);
		
		// TODO: add guess to the list of prior guesses.
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches == 4) {
			guessedcorrectly = true;
				return true;
		}
		LLIntegerNode temp = candidateListHead;
		
		int count = 0;
		while(temp != null) {
			if(numMatches(temp.getInfo(),guess) == nmatches) {
				insertNewCandidateList(temp.getInfo());
				count++;
				}
				temp = temp.getLink();
					
			}
		candidateListHead = newCandidateListHead;
		candidateListTail = newCandidateListTail;
		newCandidateListHead = null;
		newCandidateListTail = null;
		
		if(count == 0)
			return false;
		// TODO
		return true;
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		return guessListHead;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		String str = "";
		LLIntegerNode temp = guessListHead;
		
		while(temp!= null) {
			str += Integer.toString(temp.getInfo());
			if(temp.getLink() != null)
				str += ", ";
			temp = temp.getLink();
		}
		
			
		// TODO
		return str;
	}
	public void insertCandidateList(int guess) {
		LLIntegerNode newNode = new LLIntegerNode(guess);
		if(candidateListTail == null) {
			candidateListHead = newNode;
			
		}
					
		else {
			candidateListTail.setLink(newNode);
			
		
		}
		candidateListTail = newNode;
		
	}
	public void insertGuessList(int guess) {
		LLIntegerNode newNode = new LLIntegerNode(guess);
		if(guessListTail == null) {
			guessListHead = newNode;
			
		}
					
		else {
			guessListTail.setLink(newNode);
			
		
		}
		guessListTail = newNode;
		
	}
	public void insertNewCandidateList(int guess) {
		LLIntegerNode newNode = new LLIntegerNode(guess);
		if(newCandidateListTail == null) {
			newCandidateListHead = newNode;
			
		}
					
		else {
			newCandidateListTail.setLink(newNode);
			
		
		}
		newCandidateListTail = newNode;
		
	}
	
}
