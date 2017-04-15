package proj3;

/**
 * This class models the coordinates used in the Project3.java class
 * Class Invariants:
 *   - Any coordinate must be greater than (0,0)
 * @version 11/02/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 3
 * @section 06
 */
public class Coordinate {
	
	private int row;
	private int column;
	
	/**
     * Constructor - creates a new Coordinate instance
     * Preconditions: None
     * Postconditions: A coordinate is created with the given values
     * @param row of the coordinate
     * @param column of the coordinate
     */
	public Coordinate(int row, int column){
		this.row = row;
		this.column = column;
	}
	/**
	 * Gets the column of the coordinate
	 * Preconditions: None
	 * Postconditions: None
	 * @return integer of the column of the coordinate
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Gets the row of the coordinate
	 * Preconditions: None
	 * PostConditions: None
	 * @return integer of the row of the coordinate
	 */
	public int getRow() {
		return row;
	}
	public static void main(String[] args){
		Coordinate c  = new Coordinate(1,1);
		System.out.println("Testing getColumn()");
		System.out.println(c.getColumn());
		System.out.println("Testing getRow()");
		System.out.println(c.getRow());
	}
}
