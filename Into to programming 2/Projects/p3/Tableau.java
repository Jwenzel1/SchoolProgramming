package proj3;

/**
 * This class models the Tableau used in the Game.java class
 * Class Invariants:
 *   - None
 * @version 11/02/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 3
 * @section 06
 */
public class Tableau {
	
	private Deck deck;
	private Card[] tableauArray;
	private int score = 0;
	private static final int HORIZONTAL_DISTANCE = 1;
	private static final int LEFT_DIAGONAL_DISTANCE = 6;
	private static final int VERTICAL_DISTANCE = 5;
	private static final int RIGHT_DIAGONAL_DISTANCE = 4;
	
	/**
	 * Constructor - creates a new Tableau instance
	 * Preconditions: None
	 * Postconditions: A tableau object is created that has 25 cards delt into it
	 */
	public Tableau(int tableauRows, int tableauColumns, long rngSeed){
		tableauArray = new Card[tableauRows*tableauColumns];
		this.deck = new Deck();
		deck.shuffleDeck(rngSeed);
		for(int i = 0; i < this.tableauArray.length; i++){
			tableauArray[i] = deck.dealTopCard();
		}
	}
	
	/**
	 * Converts the given coordinate into a position in the one dimensional tableau array
	 * Used for finding elements in the one dimensional array that correspond to 2 dimensional coordinates
	 * Preconditions: None
	 * Postconditions: None
	 * @param coordinate of the card in the tableau
	 * @return integer index location of the element in the array
	 */
	private int coordinateToIndex(Coordinate coordinate){
		return coordinate.getRow()*5+coordinate.getColumn();
	}
	
	/**
	 * Check to see if the two selected cards can be
	 * Preconditions: None
	 * Postconditions: None
	 * @param coordinate1 first selected cards coordinates in the tableau
	 * @param coordinate2 second selected cards coordinates in the tableau
	 * @return True if the cards are legal to be removed
	 */
	public boolean removeCardCheck(Coordinate coordinate1, Coordinate coordinate2){
		if(Math.abs(coordinate1.getRow()-coordinate2.getRow()) > 1){
			return false;
		}
		if(Math.abs(coordinate1.getColumn()-coordinate2.getColumn()) > 1){
			return false;
		}
		int distanceBetweenIndecies = coordinateToIndex(coordinate2)-coordinateToIndex(coordinate1);
		
		//takes the absolute value of the distance between the indecies of the cards
		if(distanceBetweenIndecies < 0){
			distanceBetweenIndecies = distanceBetweenIndecies*-1;
		}
		//Checks if the card is exactly one position away in each cardinal direction and each diagonal direction
		if(distanceBetweenIndecies == HORIZONTAL_DISTANCE || distanceBetweenIndecies == RIGHT_DIAGONAL_DISTANCE || distanceBetweenIndecies == VERTICAL_DISTANCE || distanceBetweenIndecies == LEFT_DIAGONAL_DISTANCE){
			//Checks to see if the selected cards are of equal value
			if(tableauArray[coordinateToIndex(coordinate1)].getRank() == tableauArray[coordinateToIndex(coordinate2)].getRank()){
				return true;
			}
		}
		return false;
	}
	/**
	 * removes the cards from the array
	 * Preconditions: None
	 * Postconditions: None
	 * @param coordinate1 
	 * @param coordinate2
	 * @return true when the cards are removed
	 */
	public boolean removeCard(Coordinate coordinate1, Coordinate coordinate2){
		tableauArray[coordinateToIndex(coordinate1)] = null;
		tableauArray[coordinateToIndex(coordinate2)] = null;
		score += 2;
		return true;
	}
	 /**
	  * Consolidates the tableau 
	  * Preconditions: None
	  * PostConditions: None
	  * @param None
	  * @return None 
	  */
	public void tableauConsolidate(){
		//this entire method is basically a sort. It brings all the nulls to the back and then replaces them with more cards from the deck
		int nullCounter = 0;
		
		//Looks through the array of cards for the tableau
		for(int i = 0; i < tableauArray.length; i++){
			//If the element in the array is a null
			if(tableauArray[i] == null){
				//Begin a new search through the tableau at the null's index for the next card
				for(int j = i ; j < tableauArray.length; j++){
					//when it finds a card swap the card and the null
					if(tableauArray[j] != null){
						tableauArray[i] = new Card(tableauArray[j].getRank(), tableauArray[j].getSuit());
						tableauArray[j] = null;
						//break the loop
						j = tableauArray.length;
					}
				}
			}
		}
		//count the number of nulls int the array
		for(int i = 0; i < tableauArray.length; i++){
			if(tableauArray[i] == null){
				nullCounter++;
			}
		}
		//begin at index at the first null
		for(int i = tableauArray.length-nullCounter; i < tableauArray.length; i++){
			try{
				//deal the next card into the null spot
				tableauArray[i] = deck.dealTopCard();
			}catch(Exception e){}
		}
	}
	
	/**
	 * Gets the rank of the card at the given coordinate
	 * Precondition: None
	 * PostCondition: None
	 * @param coordinate of the given card
	 * @return Rank of the given card
	 */
	public Rank getRank(Coordinate coordinate){
		try{
			return tableauArray[coordinateToIndex(coordinate)].getRank();
		}catch(Exception e){}
		return null;
	}
	/**
	 * Gets the suit of the card at the given coordinate
	 * Precondition: None
	 * Postcondition: None
	 * @param coordinate of the given card
	 * @return Rank of the given card
	 */
	public Suit getSuit(Coordinate coordinate){
		try{
			return tableauArray[coordinateToIndex(coordinate)].getSuit();
		}catch(Exception e){}
		return null;
	}
	
	/**
	 * Gets how many cards are left in the deck
	 * Precondition: None
	 * Postconditon: None
	 * @param None
	 * @return integer of the cards left in the deck
	 */
	public int getCardsLeft(){
		if(deck.getTotalCardsInDeck() <= 0){
			return 0;
		}
		return deck.getTotalCardsInDeck();
	}
	
	/**
	 * Gets the current score of the game
	 * Preconditions: None
	 * Postconditions: None
	 * @param None
	 * @return score of the game
	 */
	public int getScore(){
		return score;
	}
	public static void main(String[] args){
		Tableau tableau = new Tableau(5,5,123456);
		Coordinate coordinate = new Coordinate(2,2);
		System.out.println("Testing coordinateToIndex()");
		tableau.coordinateToIndex(coordinate);
		System.out.println("SUCCESS");
		System.out.println("testing getRank()");
		tableau.getRank(coordinate);
		System.out.println("SUCCESS");
		
		
	}
}
