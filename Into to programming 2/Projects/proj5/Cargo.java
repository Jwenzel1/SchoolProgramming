package proj5;

/**
 * This class models the Cargo used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class Cargo implements Comparable{

	private String cargoID;
	private int weight;
	private int height;
	private int width;
	private int length;

	public Cargo(String cargoID, int weight, int height, int width, int length) {
		this.cargoID = cargoID;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.length = length;
	}

	/**
     * Returns the id of the boxcar
     * Preconditions: None
     * Postconditions: None
     * @return A string with the ID
     */
	public String getID(){
		return cargoID;
	}

	/**
     * Compares an ID to another ID
     * Preconditions: None
     * Postconditions: None
     * @return Int of the difference of the comparisons
     */
	public int compareTo(Object o) {
		if(o instanceof String){
			return cargoID.compareTo((String)o);
		}
		return cargoID.compareTo((((Cargo)o).getID()));
	}
	
	/**
     * String of the cargo
     * Preconditions: None
     * Postconditions: None
     * @return String of the contents of the Cargo
     */
	public String toString(){
		return "	Weight: " + weight + "	Dimensions: " + width + " X " + height + " X " + length + "\n";
	}
}
