package proj4;

/**
* @version 4/29/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 4
* Section 02
*/

public class TicTacToe implements Cloneable{
	
	private String[][] board = new String[3][3];
	private boolean XsTurn = true;
	private boolean draw = false;
	private int wins = 0;
	private int loses = 0;
	private int draws = 0;
	private int timesSeen = 0;
	private static String X = "X";
	private static String O = "O";


	
	public int getWinner(){
		if(draw == true){
			return 0;
		}
		//X wins
		else if(XsTurn == false && isOver() == true){
			return 1;
		}
		//O wins
		else if(XsTurn == true && isOver() == true){
			return 2;
		}
		
		return -1;
	}
	
	public int hashCode(){
		String code = "";
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[i][j] == null){
					code += "0";
				}
				else if(board[i][j] == X){
					code += "1";
				}
				else if(board[i][j] == O){
					code += "2";
				}
			}
		}
		return Integer.parseInt(code);
	}
	
	public boolean isOver(){
		//check diagonals
		if(board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
			return true;
		}
		else if(board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
			return true;
		}
		//check horizontals
		else if(board[0][0] != null && board[0][0] == board[0][1] && board[0][1] == board[0][2]){
			return true;
		}
		else if(board[1][0] != null && board[1][0] == board[1][1] && board[1][1] == board[1][2]){
			return true;
		}
		else if(board[2][0] != null && board[2][0] == board[2][1] && board[2][1] == board[2][2]){
			return true;
		}
		//check verticals
		else if(board[0][0] != null && board[0][0] == board[1][0] && board[1][0] == board[2][0]){
			return true;
		}
		else if(board[0][1] != null && board[0][1] == board[1][1] && board[1][1] == board[2][1]){
			return true;
		}
		else if(board[0][2] != null && board[0][2] == board[1][2] && board[1][2] == board[2][2]){
			return true;
		}
		//check for a draw
		else if(board[0][0] != null && board[0][1] != null && board[0][2] != null && board[1][0] != null && board[1][1] != null && board[1][2] != null && board[2][0] != null && board[2][1] != null && board[2][2] != null){
			draw = true;
			return true;
		}
		return false;
	}
	
	public boolean move(int row, int col){
		if(board[row][col] == null && XsTurn == true){
			board[row][col] = X;
			XsTurn = false;
			return true;
		}
		else if(board[row][col] == null && XsTurn == false){
			board[row][col] = O;
			XsTurn = true;
			return true;
		}
		return false;
	}
	
	public int playerAt(int row, int col){
		if(board[row][col] == X){
			return 1;
		}
		else if(board[row][col] == O){
			return 2;
		}
		return 0;
	}
	
	public String toString(){
		String string = "";
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[i][j] == null){
					string += "-";
				}
				else{
					string += board[i][j];
				}
				if(j == 2){
					string += "\n";
				}
			}
		}
		return string;
	}
	
	public boolean isFull(){
		if(board[0][0] != null && board[0][1] != null && board[0][2] != null && board[1][0] != null && board[1][1] != null && board[1][2] != null && board[2][0] != null && board[2][1] != null && board[2][2] != null){
			return true;
		}
		return false;
	}
	
	public double getWinRate(){
		if(wins + loses + draws == 0){
			return 1;
		}
		return wins / (wins + loses + draws);
	}
	
	public int getTimesSeen() {
		return timesSeen;
	}

	public void setTimesSeen(int timesSeen) {
		this.timesSeen = timesSeen;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public int getDraws() {
		return draws;
	}
	
	public String[][] getBoard(){
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	private void setXsTurn(boolean xsTurn) {
		XsTurn = xsTurn;
	}

	private void setDraw(boolean draw) {
		this.draw = draw;
	}

	private void setWins(int wins) {
		this.wins = wins;
	}

	private void setLoses(int loses) {
		this.loses = loses;
	}

	private void setDraws(int draws) {
		this.draws = draws;
	}
	
	public void incrementTimesSeen(){
		timesSeen++;
	}
	
	public void incrementWins(){
		wins++;
	}
	
	public void incrementLosses(){
		loses++;
	}
	
	public void incrementDraws(){
		draws++;
	}

	public TicTacToe clone(){
		TicTacToe newTicTacToe = new TicTacToe();
		String[][] newboard = new String[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				newboard[i][j] = board[i][j];
			}
		}
		newTicTacToe.setBoard(newboard);
		newTicTacToe.setWins(this.wins);
		newTicTacToe.setLoses(this.loses);
		newTicTacToe.setDraws(this.draws);
		newTicTacToe.setXsTurn(this.XsTurn);
		newTicTacToe.setDraw(this.draw);
		return newTicTacToe;
	}
}
