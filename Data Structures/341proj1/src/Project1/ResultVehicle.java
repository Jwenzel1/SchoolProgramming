package Project1;

/**
 * This class is models one node used in the linked list class
 * Class Invariants:
 *   - None
 * @version 02/24/14
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 341 - Spring 2014 - Project 1
 * @section 02
 */

public class ResultVehicle extends Vehicle{
	
	private int timeExited;
	
	/**
	 * Constructor - Creates an instance of a Result Vehicle.
	 */
	public ResultVehicle(Vehicle vehicle, int timeExited){
		super(vehicle.getType(), vehicle.getTimeEntered());
		this.timeExited = timeExited;
	}

	/**
	 * Getters and Setters
	 */
	public int getTimeExited(){
		return timeExited;
	}
	
	public char getType(){
		return super.getType();
	}
	
	/**
	 * returns the total amount of time the vehicle waited in the queue
	 */
	public int waitTime(){
		return timeExited - super.getTimeEntered();
	}
}
