package proj2;

/**
 * This class models a player in game.java
 * Class Invariants:
 *   - None
 * @version 10/11/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 2
 * @section 06
 */
public class Player {

	private static int totalWars = 0;
	private int warsWon;
	private int cardsWon;
	private String name;
	private CardPile pile;
	
	/**
     * Constructor - creates a new Player instance
     * Preconditions:
     *   - the name of the player must be of type String
     * Postconditions: A Player object is created with initialized instance variables and a new CardPile is made for the player at the time of creation.
     * 				   This CardPile object is used to store the cards the player is dealt.
     * @param name the name of the player
     */
	public Player(String name){
		this.name = name;
		this.warsWon = 0;
		this.cardsWon = 0;
		this.pile = new CardPile(); //initialize a pile for each player created
	}
	
	 /**
     * Adds the given card to the players cardpile
     * Preconditions: None
     * Postconditions: The players cardpile now contains the added card
     * @param Card to add to the players pile
     * @return None
     */
	public void addCardToPile(Card card){
		pile.addCardToPile(card);
	}
	
	 /**
     * Plays the top card from the players pile
     * Preconditions: None
     * Postconditions: The players pile now contains 1 fewer cards
     * @return The card being played by the player
     */
	public Card playCard(){
		Card card = pile.getTopCard();
		return card;
	}
	
	 /**
     * Removes the top 3 cards of the players pile if it is a war
     * Preconditions: A war must break out
     * Postconditions: The pile is now 3 cards less
     * @return None
     */
	public void itsWar(){
		//I use i<=2 because i need gettopcard to occur 3 times to remove 3 cards from each players pile
		for(int i = 0; i <= 2; i++){
			pile.getTopCard();
		}
	}
	
	 /**
     * Gets the name of the player
     * Preconditions: None
     * Postconditions: Player's name is returned
     * @return the name of the player
     */
	public String getName(){
		return name;
	}
	
	 /**
     * Gets the number of wars won by the player
     * Preconditions: None
     * Postconditions: the number of wars won is returned
     * @return the number of wars won by the player
     */
	public int getWarsWon(){
		return warsWon;
	}
	
	 /**
     * Gets the number of cards the player has won
     * Preconditions: None
     * Postconditions: The number of cards won by the player is returned
     * @return the number of cards won by the player
     */
	public int getCardsWon(){
		return cardsWon;
	}
	
	 /**
     * Adds the number of won cards to the number of cards the player had previously
     * Preconditions: None
     * Postconditions: The number of cards the player has won is increased
     * @param winnings to add to the total number of cards won
     * @return None
     */
	public void addCardsWon(int winnings){
		cardsWon += winnings;
	}
	
	 /**
     * Adds the number of won wars to the number of won wars the player had previously
     * Preconditions: None
     * Postconditions: The number of wars the player won is increased
     * @param battleswon to add to the total number of wars the player has won
     * @return None
     */
	public void addWonWars(int battlesWon){
		warsWon += battlesWon;
	}
	
	 /**
     * Increments the number of wars the player won
     * Preconditions: None
     * Postconditions: The number of wars the player won increased by 1
     * @return None
     */
	public static void warTracker(){
		totalWars++;
	}
	
	 /**
     * Gets the total amount of wars the players have participated in
     * Preconditions: None
     * Postconditions: the total amount of wars that have happened is returned
     * @return the current number of wars that have happened throughout the whole game
     */
	public static int getTotalWars(){
		return totalWars;
	}
	
	 /**
     * Gets the number of cards the player has left in the card pile
     * Preconditions: None
     * Postconditions: number of cards in the pile is returned
     * @return number of cards in the player's pile
     */
	public int cardsRemaining(){
		return pile.getNumCardsInPile();
	}
}
