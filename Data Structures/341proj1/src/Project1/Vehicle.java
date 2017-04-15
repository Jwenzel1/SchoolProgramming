package Project1;

/**
 * This class is models the vehicles used in the Queues in the TrafficSim class
 * Class Invariants:
 *   - None
 * @version 02/24/14
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 341 - Spring 2014 - Project 1
 * @section 02
 */

public class Vehicle {
	
	private char type;
	private int timeEntered;
	
	/**
	 * Constructor - Creates an instance of a Vehicle.
	 */
	public Vehicle(char type, int timeEntered){
		this.type = type;
		this.timeEntered = timeEntered;
	}

	/**
	 * Getters
	 */
	public char getType() {
		return type;
	}

	public int getTimeEntered() {
		return timeEntered;
	}
}
