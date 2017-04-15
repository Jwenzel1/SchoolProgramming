package proj5;

/**
 * This class models the BoxcarException used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class BoxcarException extends Exception{
	
	int size;
	int maxCapacity;
	int boxcarIndex;
	
	public BoxcarException(){
		this.size = 1;
		this.maxCapacity = 62;
		this.boxcarIndex = 0;
	}
	
	
	public BoxcarException(int size, int maxCapacity, int boxcarIndex){
		this.size = size;
		this.maxCapacity = maxCapacity;
		this.boxcarIndex = boxcarIndex;
	}

	/**
     * String for whatever was wrong
     * Preconditions: None
     * Postconditions: None
     * @return String saying what was wrong
     */
	public String getMessage(){
		if(size == maxCapacity){
			return "LOAD  \n	ERROR: Boxcar cannot exceed its max capacity.";
		}
		else{
			return null;
		}
	}
}
