package proj1;

/**
 * This class models a Connect Four game
 * Class Invariants:
 * 	- The board must be at minimum 5x5
 * @version 9/27/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu
 * @project CMSC 202 - Fall 2013 - Project 1
 * @section 89898
 */
public class ConnectFour {
	public int rows; 
	public int columns;
	public String[][] board;
	
	/**
	 * Constructor - creates a new Connect Four board instance
	 * Preconditions: A board object is created and its instance variables
	 * 				  are initialized
	 * @param numRows the number of rows of the board
	 * @param numColumns the number of columns of the board
	 */
	public ConnectFour(int numRows, int numColumns){
		rows = numRows;
		columns = numColumns;
		board = new String[numRows][numColumns];
		// Generates the actual board to play with. 
		// It uses underscores ("_") to denote unplayed spaces.
		for(int x=0; x < rows; x++){
			for(int y=0; y < columns; y++){
				board[x][y] = "_";
			}
		}
	}
	/**
	 * Prints the board for the players
	 * Preconditions: None
	 * Postconditions: None
	 */
	public void printBoard(){
		for(int x=0; x < rows; x++){
			for(int y=0; y < columns; y++){
				System.out.print(board[x][y]);
				if (y == columns-1){
					System.out.print("\n");
				}
			}
		}
	}
	/**
	 * Checks if one of the players has quit by entering "q"
	 * as their play column
	 * Preconditions: Takes in the players choice column to see if
	 * 				  they enetered "q"
	 * Postconditions: None
	 * @param input
	 */
	public void checkForQuit(String input){
		if (input.equals("q")){
			System.exit(0);
		}
	}
	/**
	 * Makes the move the person chooses by changing the "_" in the board
	 * to their respective piece
	 * Precondition: Needs to know the column the player wants to 
	 *               play in and whose turn it is.
	 * Postconditions: returns the row the player played in  
	 * @param location
	 * @param player
	 * @return the row that the persons piece is in
	 */
	public int makeMove(int location, boolean player){
		location = location - 1;
		if (player == true){
			for (int x = rows-1; x >= -1; x--){
				if (board[x][location].equals("_")){
					board[x][location] = "x";
					return x;
				}
			}
		} 
		else {
			for (int x = rows - 1; x >= -1; x--){
				if (board[x][location].equals("_")){
					board[x][location] = "o";
					return x;
				}
			}
		}
		return location;
	}
	/**
	 * Checks to see if the input the player entered is an allowed move.
	 * Preconditions: needs the players column they want to play in
	 * Postconditions: None
	 * @param playerColumn the column the player chooses
	 * @return whether the move is allowed or not
	 */
	public boolean legalMove(int playerColumn){
		if(playerColumn < 1 || playerColumn > columns || board[0][playerColumn-1] != "_"){
			return false;
		}
		return true;
	}
	/**
	 * Check is a player has won yet
	 * Preconditions: None
	 * Postconditions: None
	 * @return true or false if someone has won or not
	 */
	public boolean checkForWin(){
		boolean win = false;
		if(checkHorizontal() || checkVertical() || checkLowerDiagonal() || checkUpperDiagonal()){
			win = true;
		}
		return win;
	}
	/**
	 * Check for a horizontal win. This is a helper method for 
	 * checkForWin()
	 * Preconditions: None
	 * PostConditions: None
	 * @return true or false depending on if there is a win or not in
	 * 		   the horizontal direction
	 */
	public boolean checkHorizontal(){
		boolean win = false;
		for(int x=0; x < rows; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "x"){
					if(board[x][y+1] == "x" && board[x][y+2] == "x" && board[x][y+3] == "x"){
						System.out.print("Congrats, player 1 wins!");
						win = true;
					}
				}
			}
		}
		for(int x=0; x < rows; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "o"){
					if(board[x][y+1] == "o" && board[x][y+2] == "o" && board[x][y+3] == "o"){
						System.out.print("Congrats, player 2 wins!");
						win = true;
					}
				}
			}
		}
		return win;
	}
	/**
	 * Check if there is a win in the horizontal direction.
	 * Preconditions: None
	 * Postconditions: None
	 * @return true or false depending on if someone has won in the 
	 * 		   horiontal direction
	 */
	public boolean checkVertical(){
		for(int x=0; x < rows-3; x++){
			for(int y=0; y < columns; y++){
				if(board[x][y] == "x"){
					if(board[x+1][y] == "x" && board[x+2][y] == "x" && board[x+3][y] == "x"){
						System.out.print("Congrats, player 1 wins!");
						return true;
					}
				}
			}
		}
		for(int x=0; x < rows-3; x++){
			for(int y=0; y < columns; y++){
				if(board[x][y] == "o"){
					if(board[x+1][y] == "o" && board[x+2][y] == "o" && board[x+3][y] == "o"){
						System.out.print("Congrats, player 2 wins!");
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Check if someone has won in the top left and bottom right
	 * diagonal direction
	 * Preconditions: None
	 * PostConditions: None
	 * @return true or false if someone has won in either direction
	 * 		   previously stated
	 */
	public boolean checkLowerDiagonal(){
		for(int x=0; x < rows-3; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "x"){
					if(board[x+1][y+1] == "x" && board[x+2][y+2] == "x" && board[x+3][y+3] == "x"){
						System.out.print("Congrats, player 1 wins!");
						return true;
					}
				}
			}
		}
		for(int x=0; x < rows-3; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "o"){
					if(board[x+1][y+1] == "o" && board[x+2][y+2] == "o" && board[x+3][y+3] == "o"){
						System.out.print("Congrats, player 2 wins!");
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Check the top right and bottom left direction to see if someone
	 * has won in those directions
	 * Preconditions: None
	 * PostConditions: None
	 * @return true or false if someone has won in those previously
	 * stated directions
	 */
	public boolean checkUpperDiagonal(){
		for(int x=3; x < rows; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "x"){
					if(board[x-1][y+1] == "x" && board[x-2][y+2] == "x" && board[x-3][y+3] == "x"){
						System.out.print("Congrats, player 1 wins!");
						return true;
					}
				}
			}
		}
		for(int x=3; x < rows; x++){
			for(int y=0; y < columns-3; y++){
				if(board[x][y] == "o"){
					if(board[x-1][y+1] == "o" && board[x-2][y+2] == "o" && board[x-3][y+3] == "o"){
						System.out.print("Congrats, player 2 wins!");
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Check the board to see if the outcome is a draw
	 * Preconditions: None
	 * PostConditions: None
	 * @return true or false depending on if the baord is full or not
	 */
	public boolean checkForDraw(){
		boolean tie = true;
		for(int x=0; x < rows; x++){
			for(int y=0; y < columns; y++){
				if(board[x][y] == "_"){
					tie = false;
				}
			}
		}
		return tie;
	}
}