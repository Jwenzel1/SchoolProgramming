package proj1;

import java.util.Scanner;
/**
 * This class implements a connect four game
 * @version 9/27/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu
 * @project CMSC 202 - Fall 2013 - Project 1
 * @section 89898
 */
public class Project1 {
	
	public static void main(String[] args) {
		//initializes a scanner and boolean for the replay while loop
		Scanner input = new Scanner(System.in);
		boolean replay = true;
		
		//while loop checking if the players want to play again
		while(replay == true){
			
			//initialized variables for the board
			boolean win = false;
			boolean player1Turn = true;
			boolean allowed = false;
			String playerAnswer = "";
			int responseX = 0;
			int responseY = 0;
			
			//Checking to see if the number of rows entered is allowed
			while(allowed == false){
				System.out.print("Hi, please enter a number of rows: ");
				responseX = input.nextInt();
				input.nextLine();
				if (responseX >=5){
					allowed = true;
				}
			}
			
			//checking to see if the number of columns is allowed
			allowed = false;
			while(allowed == false){
				System.out.print("Please enter a number of columns: ");
				responseY = input.nextInt();
				input.nextLine();
				if (responseY >= 5){
					allowed = true;
				}
			}
			
			//create the connect four instance
			ConnectFour board = new ConnectFour(responseX, responseY);
			//print out the baord so the players can see the board
			board.printBoard();
			
			//while loop waiting for someone to win
			while(win==false){

				int playerMove = -1;
				boolean legal = false;

				while (legal == false){
					if (player1Turn == true){
						System.out.print("Player one, please enter a move: ");
					}else{
						System.out.print("Player two, please enter a move: ");
					}
					
					playerAnswer = input.next();
					input.nextLine();
					//check if a player has enterd q
					board.checkForQuit(playerAnswer);
					//makes the players move into an integer for use
					playerMove = Integer.parseInt(playerAnswer);
					//checks id the play is allowed
					legal =  board.legalMove(playerMove);
				}
				//makes the move for the player
				board.makeMove(playerMove, player1Turn);
				board.printBoard();
				//switches between whose turn it is
				//true is player 1 and false is player 2
				if (player1Turn == true){
					player1Turn = false;
				}else{
					player1Turn = true;
				}
				//variable for checking is a win has been achieved
				win = board.checkForWin();
				//check to see if a draw has been achieved if no win is present
				if (board.checkForDraw() == true && win == false){
					System.out.print("The game eneded in a draw!");
					win = true;
				}
			}
			System.out.print("\nWould you like to play again?: ");
			boolean ask = true;
			//do while loop waiting for the player to put in a valid
			//response
			do{
				String response = input.next();
				input.nextLine();
				if(response.equals("n")){
					replay = false;
					ask = false;
				}else if (response.equals("y")){
					ask = false;
				}else{
					System.out.print("Please enter again");
				}
				
			}while(ask == true);
		}
		input.close();
	}
}
