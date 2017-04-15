package proj2;

/**
 * This class models the game of war!
 * Class Invariants: 
 * 	 - None
 * @version 10/11/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 2
 * @section 06
 */
public class Game {
	
	private String p1Name;
	private String p2Name;
	private long rngSeed;
	private Player player1;
	private Player player2;
	private int numberOfCardsPlayed = 0;
	private int warCounter = 0;
	
	/**
     * Constructor - creates a new Game instance
     * Preconditions:
     *   - p1Name and p2Name must be Strings
     *   - rngSeed must be of type long
     * Postconditions: A game object is created which then makes a new standard 52 card deck, creates instances for player1 and player2, 
     * 				   shuffles the deck, and then deals out the shuffled deck to the players
     * @param p1Name the name of player 1
     * @param p2Name the name of player 2
     * @param rngSeed the number used to seed the random number generator used to shuffle the deck
     */
	public Game(String p1Name, String p2Name, long rngSeed){
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		this.rngSeed = rngSeed;
		this.player1 = new Player(p1Name); //Initialize player objects used for player 1 and 2
		this.player2 = new Player(p2Name);
		//creates a new deck to play the game with
		Deck playDeck = new Deck();
		//shuffles the deck just made
		playDeck.shuffleDeck(this.rngSeed);
		//deals out the card of the deck to the players starting with player1 then alternating between player2 then back to 1
		dealCardsToPlayers(playDeck);
	}

	/**
     * Deals the cards from the deck instance to the player instances which in turn adds them to the players cardpile instances
     * Preconditions: A deck object and two player objects must be made
     * Postconditions: The players cardpiles are now filled with cards
     * @param deck to be shuffled
     * @return None
     */
	private void dealCardsToPlayers(Deck deck){
		//This for loop deals out cards to the players which then adds the card to each players respective pile
		for(int i = 0; i < 52; i++){
			//adds the card to the respective players cardpile
			player1.addCardToPile(deck.dealTopCard());
			i++;
			player2.addCardToPile(deck.dealTopCard());
		}
	}
	
	/**
     * Plays the game and keeps track of the number of cards played a turn, and what cards are played
     * Preconditions: None
     * Postconditions: Returns a string based on what was played
     * @return String showing who played what, and how many cards were won or if a war occured.
     */
	public String nextTurn(){
		//gets the players top card
		Card p1Card = player1.playCard();
		//increment a counter to keep track of how many cards were played this round
		numberOfCardsPlayed++;
		Card p2Card = player2.playCard();
		numberOfCardsPlayed++;
		
		//if the value of player 1's card is higher than player 2's card
		if(p1Card.getRank().getValue() > p2Card.getRank().getValue()){
			//move the number of cards played this round to a temporary variable
			int temp = numberOfCardsPlayed;
			//add that many won cards to player1's won card amount
			player1.addCardsWon(numberOfCardsPlayed);
			//add the number of wars player1 won to the won wars amount
			player1.addWonWars(warCounter);
			
			//this variable keeps track of the number of cards played during each round.
			//reset this variable and the war variable since the round is over
			numberOfCardsPlayed = 0;
			warCounter = 0;
			return p1Name + " shows " + p1Card + "\n" + p2Name + " shows " + p2Card + "\n" + p1Name + " wins " + temp + " cards.\n";
		}
		
		//if player 1 and 2's cards are the same value a war breaks out
		else if(p1Card.getRank().getValue() == p2Card.getRank().getValue()){
			//the itsWar method removes 3 cards from each players cardpile
			player1.itsWar();
			player2.itsWar();
			
			//Adding 6 cards because that is the number of face down cards that gets played during a war.
			numberOfCardsPlayed += 6;
			//increment the war counter
			warCounter++;
			//keeps track of the total number of wars that have occured in the entire game
			Player.warTracker();
			return p1Name + " shows " + p1Card + "\n" + p2Name + " shows " + p2Card + "\n" + "WAR!!!\n";
		}
		//if player 1 didnt win and a war didnt break out then player 2 won. this does the same thing the player1 if statement does.
		int temp = numberOfCardsPlayed;
		player2.addCardsWon(numberOfCardsPlayed);
		player2.addWonWars(warCounter);
		numberOfCardsPlayed = 0;
		warCounter = 0;
		return p1Name + " shows " + p1Card + "\n" + p2Name + " shows " + p2Card + "\n" + p2Name + " wins " + temp + " cards.\n";
	}
	
	/**
     * Checks to see if the game is over
     * Preconditions: None
     * Postconditions: Returns true or false
     * @return true if the game is over, false if it is not.
     */
	public boolean gameComplete(){
		//if player1 has no cards left then neither does player 2 since this is only one round and the game is over
		if(player1.cardsRemaining() <= 0){
			return true;
		}
		return false;
	}
	
	/**
     * Creates the String telling the players information about their game they just played.
     * Preconditions: None
     * Postconditions: Returns a game ending string
     * @return String showing who won, how many wars there were, and how many cards and wars were won by each player
     */
	public String gameResult(){
		//Creates the wintext that gets returned to project2
		String winText ="There were " + Player.getTotalWars()+" wars\n" + p1Name + " won " + player1.getCardsWon() + " cards and " + player1.getWarsWon() + " war(s)\n" + p2Name + " won " + player2.getCardsWon() + " cards and " + player2.getWarsWon() + " war(s)\nWinner: ";
		if(player1.getCardsWon() > player2.getCardsWon()){
			winText += p1Name;
			return winText;
		}
		//checks if it was a tie
		else if(player1.getCardsWon() == player2.getCardsWon()){
			winText += "Nobody it was a tie!";
			return winText;
		}
		return winText + p2Name;
	}
	public static void main( String[ ] args){
		new Game("Billy", "Joe", 123456);
	}
}
