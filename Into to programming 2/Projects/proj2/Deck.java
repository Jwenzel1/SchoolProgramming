package proj2;

import java.util.Random;

/**
 * This class models the deck used in the game.java class
 * Class Invariants:
 *   - None
 * @version 10/11/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 2
 * @section 06
 */
public class Deck {
	
	private int totalCardsInDeck = 52;
	private Card[] deck = new Card[52];
	
	/**
     * Constructor - creates a new Deck instance
     * Preconditions: None
     * Postconditions: A deck object is created with an array of 52 cards each corresponding to a standard card
     */
	public Deck(){
		int i = 0;
		//iterates through the values of suit and rank to make the 52 card deck
		for(Suit suit : Suit.values()){
			for(Rank rank: Rank.values()){
				this.deck[i] = new Card(rank, suit);
				i++;
			}
		}
	}
		
	/**
     * Shuffles the deck using a given random number seed.
     * Preconditions: A deck must be created and a valid seed must be passed in
     * Postconditions: The deck will be shuffled from the point this method is called onwards
     * @param rngSeed to use in the random number generator
     * @return None
     */
	public void shuffleDeck(long rngSeed){
		//creates a new random number generator that gets seeded with the user inputed rngseed
		Random random = new Random(rngSeed);
		int numberOfCardsToShuffle = deck.length - 1;
		//while there is still a card to shuffle
		while(numberOfCardsToShuffle > 0){
			//get a random number from the generator
			int randomNumber = random.nextInt(numberOfCardsToShuffle+1); 
			//create deep copies of the 2 cards to be swapped
			Card temp = new Card(deck[numberOfCardsToShuffle].getRank(),deck[numberOfCardsToShuffle].getSuit());
			Card randomTemp = new Card(deck[randomNumber].getRank(),deck[randomNumber].getSuit());
			//swap the cards in the array(or references i guess)
			deck[randomNumber] = temp;
			deck[numberOfCardsToShuffle] = randomTemp;
			//lower the number of cards that need to be shuffled by 1
			numberOfCardsToShuffle--;
		}
	}
	
	/**
     * Returns the top card of the deck (begining with array position 51) 
     * Preconditions: A deck must be created
     * Postconditions: None
     * @return The top card of the deck
     */
	public Card dealTopCard(){
		totalCardsInDeck--;
		Card topCard = new Card(deck[totalCardsInDeck].getRank(), deck[totalCardsInDeck].getSuit());
		return topCard;
	}
	
	public static void main( String[ ] args){
		Deck testDeck = new Deck();
		testDeck.shuffleDeck(123456);
		for(int i = 0; i < 52; i++){
			System.out.println(testDeck.dealTopCard());
		}
	}
}
