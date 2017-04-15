package proj4;

/**
* @version 4/29/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 4
* Section 02
*/

import java.util.Random;

public class RandomAI {
	
	/**
	 * Makes a random move on the given board
	 * @param t - the board to play on
	 */
	public void move(TicTacToe t){
		Random row = new Random();
		Random col = new Random();
		boolean moveSuccessful = false;
		
		while(!moveSuccessful && !t.isFull()){
			moveSuccessful = t.move(row.nextInt(3), col.nextInt(3));
		}
	}


	
}