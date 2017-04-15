package proj2;

/**
 * This class models the card piles a player would have after dealing out cards to them
 * Class Invariants:
 *   - None
 * @version 10/11/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 2
 * @section 06
 */
public class CardPile {
	
	private int numOfCardsInPile = 0;
	private Card[] cardPile = new Card[26];
	
	 /**
     * Adds the given card to the cardpile
     * Preconditions: None
     * Postconditions: The card is added to the cardpile
     * @param Card to add to the pile
     * @return An array of cards representing the players current cards they have
     */
	public Card[] addCardToPile(Card card){
		cardPile[numOfCardsInPile] = card;
		numOfCardsInPile++;
		return cardPile;
	}
	
	 /**
     * Gets the top card of the cardpile
     * Preconditions: None
     * Postconditions: The top card is returned
     * @return The top card of the cardpile
     */
	public Card getTopCard(){
		Card topCard = new Card(cardPile[numOfCardsInPile-1].getRank(), cardPile[numOfCardsInPile-1].getSuit());
		numOfCardsInPile--;
		return topCard;
	}
	
	 /**
     * Gets the number of cards in the cardpile
     * Preconditions: None
     * Postconditions: Length of the cardpile array is returned
     * @return the number of cards in the pile
     */
	public int getNumCardsInPile(){
		return numOfCardsInPile;
	}
	
	public static void main( String[ ] args){
		CardPile pile = new CardPile();

	}
}
