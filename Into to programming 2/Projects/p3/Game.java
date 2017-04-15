package proj3;

/**
 * This class models the game used in Project3.java
 * Class Invariants:
 *   - None
 * @version 11/02/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 3
 * @section 06
 */
public class Game {
	
	private Tableau tableau;
	private long gameNumber;
	private int tableauRows;
	private int tableauColumns;

	/**
	 * Constructor - creates a new Game instance
	 * Preconditions: None
	 * Postconditions: A game object is created with a given number of columns, rows, and rngSeed
	 */
	public Game(int tableauRows, int tableauColumns){
		this.tableauRows = tableauRows;
		this.tableauColumns = tableauColumns;
		this.tableau = new Tableau(tableauRows, tableauColumns, gameNumber);
		
	}
	
	/**
	 * Consolidates the tableau by moving cards next to each other
	 * Preconditions: A tableau must be created
	 * Postconditions: The tableau will be consolidated
	 * @param None
	 * @return None
	 */
	public void consolidate(){
		tableau.tableauConsolidate();
	}
	
	/**
	 * Gives a helpful how to play message.
	 * Preconditions: None
	 * Postconditions: None
	 * @param None
	 * @return String
	 */
	public String getHelpText(){
		String helpText = "How to play:\n";
		helpText += "find cards of equal rank that are touching either \n";
		helpText += "horizontal, vertical, or diagonal\n";
		helpText += "click the matching pair and it will go away\n";
		helpText += "press consolidate when no other pairs can be made\n";
		helpText += "repeat until you win.";
		return helpText;
	}
	
	/**
	 * Finds 2 cards that are matching next to each other and highlights them
	 * Preconditions: A game must be created
	 * Postconditions: None
	 * @param None
	 * @return Coordinate array
	 */
	public Coordinate[] getHint(){
		for(int i = 0; i < tableauRows; i++){
			for(int j = 0; j < tableauColumns; j++){
				Coordinate card1 = new Coordinate(i,j);
				if(tableau.getRank(card1) != null){
					for(int x = 0; x < tableauRows; x++){
						for(int z = 0; z < tableauColumns; z++){
							Coordinate card2 = new Coordinate(x,z);
							if(tableau.getRank(card2) != null){
								if(tableau.removeCardCheck(card1, card2)){
									Coordinate[] coordinateArray= {card1,card2};
									return coordinateArray;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the rank of the card at the given coordinate in the tableau
	 * Preconditions: A game must be created
	 * Postconditions: None
	 * @param Coordinate where the card is
	 * @return Rank of the card
	 */
	public Rank getRank(Coordinate coordinates){
		return tableau.getRank(coordinates);
	}
	
	/**
	 * Gets the players current score
	 * Preconditions: A game must be created
	 * Postconditions: None
	 * @param None
	 * @return Integer of the persons score
	 */
	public int getScore(){
		return tableau.getScore();
	}
	
	/**
	 * Gets the suit of the card at the given coordinate
	 * Preconditions: A game must be made
	 * Postconditions: None
	 * @param Coordinate where the card is
	 * @return Suit of the card
	 */
	public Suit getSuit(Coordinate coordinates){
		return tableau.getSuit(coordinates);
	}
	
	/**
	 * Checks if the hint option is implemented
	 * Preconditions: Game must be created
	 * Postconditions: None
	 * @param None
	 * @return Boolean of true if the hint option is implemented or false if it is not
	 */
	public boolean isHintImplemented(){
		return true;
	}
	
	/**
	 * Checks to see if the player has won
	 * Preconditions: Game must be created
	 * Postconditions: None
	 * @param None
	 * @return Boolean of true if the person won or false if they havent yet
	 */
	public boolean isWin(){
		if(tableau.getScore() == 52){
			return true;
		}
		return false;
	}
	
	/**
	 * Starts a new game with the user inputed gameNumber for the rng seed
	 * Preconditions: A game must be created
	 * Postconditions: None
	 * @param gameNumber used to seed the rng for the shuffling in deck
	 * @return None
	 */
	public void newGame(long gameNumber){
		this.gameNumber = gameNumber;
		this.tableau = new Tableau(tableauRows, tableauColumns, gameNumber);
	}
	
	/**
	 * Gets the number of cards left in the deck
	 * Preconditions: A tableau must be created
	 * Postconditions: None
	 * @param None
	 * @return Integer of the number of cards left in the deck
	 */
	public int numberOfCardsLeft(){
		return tableau.getCardsLeft();
	}
	
	/**
	 * Removes the selected cards from the tableau
	 * Preconditions: A tableau must be created.
	 * Postconditions: The cards will be removed from the tableau
	 * @param coordinate1 the coordinates of the first card the player clicked on
	 * @param coordinate2 the coordinates of the second card the player clicked on
	 * @return Boolean of true if the cards can be removed or false if not
	 */
	public boolean removeCards(Coordinate coordinate1, Coordinate coordinate2){
		if(tableau.removeCardCheck(coordinate1, coordinate2) == true){
			return tableau.removeCard(coordinate1, coordinate2);
		}
		return false;
	}
	
	/**
	 * Replays the current game.
	 * Preconditions: A game of Monet Carlo Solitare must be created already
	 * Postconditions: None
	 * @param None
	 * @return None
	 */
	public void replay(){
		this.tableau = new Tableau(tableauRows, tableauColumns, gameNumber);
	}
	public static void main(String[] args){
		Game game = new Game(5,5);
		System.out.println("testing consolidate()");
		game.consolidate();
		System.out.println("SUCCESS");
		System.out.println("testing getHelpText()");
		System.out.println(game.getHelpText());
		System.out.println("SUCCESS");
		System.out.println("testing getHint()");
		System.out.println(game.getHint());
		System.out.println("SUCCESS");
		System.out.println("testing getRank()");
		Coordinate coordinate = new Coordinate(1,1);
		game.getRank(coordinate);
		System.out.println("SUCCESS");
		System.out.println("testing getScore()");
		System.out.println(game.getScore());
		System.out.println("testing getSuit()");
		game.getSuit(coordinate);
		System.out.println("SUCCESS");
		System.out.println("testing isHintImplemented()");
		game.isHintImplemented();
		System.out.println("SUCCESS");
		System.out.println("testing isWin()");
		game.isWin();
		System.out.println("SUCCESS");
		System.out.println("testing numberOfCardsLeft()");
		game.numberOfCardsLeft();
		System.out.println("SUCCESS");
	}
}