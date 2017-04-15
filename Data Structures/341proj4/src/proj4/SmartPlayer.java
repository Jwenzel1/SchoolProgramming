package proj4;

/**
* @version 4/29/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 4
* Section 02
*/

import java.util.ArrayList;
import java.util.Random;

public class SmartPlayer {
	
	private ArrayList<TicTacToe> playedBoards;
	private int player;
	private Hashtable<TicTacToe, TicTacToe> hashtable = new Hashtable<TicTacToe, TicTacToe>();
	private boolean begining = true;
	private double wins = 0;
	private double losses = 0;
	private double draws = 0;

	/**
	 * ends the game for smartplayer and also increments wins and losses for the boards
	 * @param finalBoard
	 */
	public void endGame(TicTacToe finalBoard){
		if(finalBoard.getWinner() != -1 && finalBoard.getWinner() == player){
			wins++;
			for(int i = 0; i < playedBoards.size(); i++){
				playedBoards.get(i).incrementWins();
			}
		}
		else if(finalBoard.getWinner() != -1 && finalBoard.getWinner() == 0){
			draws++;
			for(int i = 0; i < playedBoards.size(); i++){
				playedBoards.get(i).incrementDraws();
			}
		}
		else if(finalBoard.getWinner() != -1 && finalBoard.getWinner() != player){
			losses++;
			for(int i = 0; i < playedBoards.size(); i++){
				playedBoards.get(i).incrementLosses();	
			}
		}
		for(int i = 0; i < playedBoards.size(); i++){
			hashtable.put(playedBoards.get(i), playedBoards.get(i));
		}
	}
	
	/**
	 * finds all the successors to the given board that 
	 * smartplayer has already played before
	 * @param t
	 * @return
	 */
	public TicTacToe[] getSuccessors(TicTacToe t){
		ArrayList<TicTacToe> array = new ArrayList<TicTacToe>();
		String[][] currentBoard = t.clone().getBoard();
		if(player == 1){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(currentBoard[i][j] == null){
						currentBoard[i][j] = "X";
						TicTacToe tictactoe = new TicTacToe();
						tictactoe.setBoard(currentBoard);
						if(hashtable.containsKey(tictactoe)){
							array.add(hashtable.get(tictactoe));
						}
						currentBoard = t.clone().getBoard();
					}
				}
			}
		}
		else if(player == 2){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(currentBoard[i][j] == null){
						currentBoard[i][j] = "O";
						TicTacToe tictactoe = new TicTacToe();
						tictactoe.setBoard(currentBoard);
						if(hashtable.containsKey(tictactoe)){
							array.add(hashtable.get(tictactoe));
						}
						currentBoard = t.clone().getBoard();
					}
				}
			}
		}
		TicTacToe[] tictactoeArray = new TicTacToe[array.size()];
		for(int i = 0; i < tictactoeArray.length; i++){
			tictactoeArray[i] = array.get(i);
		}
		return tictactoeArray;
	}
	
	private TicTacToe[] getAntiSuccessors(TicTacToe t){
		ArrayList<TicTacToe> array = new ArrayList<TicTacToe>();
		String[][] currentBoard = t.clone().getBoard();
		if(player == 1){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(currentBoard[i][j] == null){
						currentBoard[i][j] = "X";
						TicTacToe tictactoe = new TicTacToe();
						tictactoe.setBoard(currentBoard);
						if(!hashtable.containsKey(tictactoe)){
							array.add(tictactoe);
						}
						currentBoard = t.clone().getBoard();
					}
				}
			}
		}
		else if(player == 2){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(currentBoard[i][j] == null){
						currentBoard[i][j] = "O";
						TicTacToe tictactoe = new TicTacToe();
						tictactoe.setBoard(currentBoard);
						if(!hashtable.containsKey(tictactoe)){
							array.add(tictactoe);
						}
						currentBoard = t.clone().getBoard();
					}
				}
			}
		}
		TicTacToe[] tictactoeArray = new TicTacToe[array.size()];
		for(int i = 0; i < tictactoeArray.length; i++){
			tictactoeArray[i] = array.get(i);
		}
		return tictactoeArray;
	}
	
	/**
	 * performs smartplayers move after consulting the hashtable first
	 * @param t
	 */
	public void move(TicTacToe t){
		playedBoards.add(t);
		if(player == 1 && begining){
			playedBoards.remove(t);
			begining = false;
		}
		Random random = new Random();
		TicTacToe[] playedSuccessors = getSuccessors(t);
		TicTacToe[] unplayedSuccessors = getAntiSuccessors(t);
		TicTacToe newMove;
		double highestWinRate = 0;
		ArrayList<TicTacToe> potentialBest = new ArrayList<TicTacToe>();
		for(int i = 0; i < playedSuccessors.length; i++){
			if(playedSuccessors[i].getWinRate() > highestWinRate){
				potentialBest.clear();
				potentialBest.add(playedSuccessors[i]);
				highestWinRate = playedSuccessors[i].getWinRate();
			}
			else if(playedSuccessors[i].getWinRate() == highestWinRate){
				potentialBest.add(playedSuccessors[i]);
			}
		}
		for(int i = 0; i < unplayedSuccessors.length; i++){
			potentialBest.add(unplayedSuccessors[i]);
		}
		int randomNumber = random.nextInt(potentialBest.size());
		newMove = potentialBest.get(randomNumber);
		playedBoards.add(newMove);
		for(int i = 0; i < 3; i ++){
			for(int j = 0; j < 3; j++){
				if(newMove.getBoard()[i][j] != t.getBoard()[i][j]){
					t.move(i, j);
				}
			}
		}
		playedBoards.add(t);
	}
	
	/**
	 * prepares the smartplayer for a new game
	 * @param player
	 */
	public void newGame(int player){
		playedBoards = new ArrayList<TicTacToe>();
		this.player = player;
		
	}
	
	//getters and setters****************************
	public int numberOfTimesSeen(TicTacToe t){
		return hashtable.get(t).getTimesSeen();
	}
	
	public void giveHashtable(Hashtable<TicTacToe, TicTacToe> hashtable){
		this.hashtable = hashtable;
	}

	public double getWins() {
		return wins;
	}

	public double getLosses() {
		return losses;
	}

	public double getDraws() {
		return draws;
	}

	public Hashtable<TicTacToe, TicTacToe> getHashtable() {
		return hashtable;
	}
	//*******************************
	
	
}
