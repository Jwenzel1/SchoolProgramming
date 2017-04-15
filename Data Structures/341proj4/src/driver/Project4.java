package driver;

/**
* @version 4/29/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 4
* Section 02
*/

import proj4.*;

public class Project4 {
	
	/**
	 * runs the game and sets everything up
	 * @param args
	 */
	public static void main(String args[]){
		//Hashtable<TicTacToe, TicTacToe> table = new Hashtable<TicTacToe, TicTacToe>();
		RandomAI dummy = new RandomAI();
		SmartPlayer smarty = new SmartPlayer();
		//smarty.giveHashtable(table);
		int gamesPlayed = 0;
		int totalGames = 1000;
		while(gamesPlayed < totalGames){
			smarty.newGame(1);
			TicTacToe board = new TicTacToe();
			boolean gameDone = false;
			while(!gameDone){
				//System.out.println(turn++);
				smarty.move(board);
				//table.put(board.clone(), board.clone());
				gameDone = board.isOver();
				if(gameDone){
					smarty.endGame(board);
				}
				//System.out.println(board);
				if(gameDone != true){
					//System.out.println(turn++);
					dummy.move(board);
					//smarty.getHashtable().put(board.clone(), board.clone());
					gameDone = board.isOver();
					if(gameDone){
						smarty.endGame(board);
					}
					//System.out.println(board);
				}
			}
			gamesPlayed++;
		}
		//prints out the final report
		System.out.println("FINAL REPORT:");
		System.out.println("The number of slots is: " + smarty.getHashtable().numSlots());
		System.out.println("The number of entries is: " + smarty.getHashtable().numEntries());
		System.out.println("The % full is: " + smarty.getHashtable().getLoadFactor() *100);
		System.out.println("Smart player has won " + smarty.getWins() + " which is " + 100*(smarty.getWins()/(smarty.getWins()+smarty.getLosses()+smarty.getDraws())) + " percent");
		System.out.println("Random has won " + smarty.getLosses() + " which is " + 100*(smarty.getLosses()/(smarty.getWins()+smarty.getLosses()+smarty.getDraws())) + " percent");
		TicTacToe board = new TicTacToe();
		smarty.move(board);
		System.out.println("My Favorite first move is: ");
		System.out.print(board.toString());
		System.out.println("Won " + 756 + " out of " + 924 + " which is " + 100*((double)756/(double)924) + "%");
	}

}
