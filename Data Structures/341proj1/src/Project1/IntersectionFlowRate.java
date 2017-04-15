package Project1;

/**
 * This class holds all the intersection flowrates for the TrafficSim class
 * Class Invariants:
 *   - None
 * @version 02/24/14
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 341 - Spring 2014 - Project 1
 * @section 02
 */

public class IntersectionFlowRate {
	
	private int eastFlowRateCars;
	private int westFlowRateCars;
	private int northFlowRateCars;
	private int southFlowRateCars;
	private int eastFlowRateTrucks;
	private int westFlowRateTrucks;
	private int northFlowRateTrucks;
	private int southFlowRateTrucks;
	
	/**
	 * Constructor - Creates an instance to hold all the flow rates.
	 * Preconditions: None
	 * Postconditions: None
	 * @param eastFlowRateCars
	 * @param westFlowRateCars
	 * @param northFlowRateCars
	 * @param southFlowRateCars
	 * @param eastFlowRateTrucks
	 * @param westFlowRateTrucks
	 * @param northFlowRateTrucks
	 * @param southFlowRateTrucks
	 */
	public IntersectionFlowRate(int eastFlowRateCars, int westFlowRateCars, int northFlowRateCars, int southFlowRateCars, int eastFlowRateTrucks, int westFlowRateTrucks, int northFlowRateTrucks, int southFlowRateTrucks){
		this.eastFlowRateCars = eastFlowRateCars;
		this.westFlowRateCars = westFlowRateCars;
		this.northFlowRateCars = northFlowRateCars;
		this.southFlowRateCars = southFlowRateCars;
		this.eastFlowRateTrucks = eastFlowRateTrucks;
		this.westFlowRateTrucks = westFlowRateTrucks;
		this.northFlowRateTrucks = northFlowRateTrucks;
		this.southFlowRateTrucks = southFlowRateTrucks;
	}
	
	/**
	 * Setters and Getters
	 */
	public int getEastFlowRateCars() {
		return eastFlowRateCars;
	}
	public void setEastFlowRateCars(int eastFlowRateCars) {
		this.eastFlowRateCars = eastFlowRateCars;
	}
	public int getWestFlowRateCars() {
		return westFlowRateCars;
	}
	public void setWestFlowRateCars(int westFlowRateCars) {
		this.westFlowRateCars = westFlowRateCars;
	}
	public int getNorthFlowRateCars() {
		return northFlowRateCars;
	}
	public void setNorthFlowRateCars(int northFlowRateCars) {
		this.northFlowRateCars = northFlowRateCars;
	}
	public int getSouthFlowRateCars() {
		return southFlowRateCars;
	}
	public void setSouthFlowRateCars(int southFlowRateCars) {
		this.southFlowRateCars = southFlowRateCars;
	}
	public int getEastFlowRateTrucks() {
		return eastFlowRateTrucks;
	}
	public void setEastFlowRateTrucks(int eastFlowRateTrucks) {
		this.eastFlowRateTrucks = eastFlowRateTrucks;
	}
	public int getWestFlowRateTrucks() {
		return westFlowRateTrucks;
	}
	public void setWestFlowRateTrucks(int westFlowRateTrucks) {
		this.westFlowRateTrucks = westFlowRateTrucks;
	}
	public int getNorthFlowRateTrucks() {
		return northFlowRateTrucks;
	}
	public void setNorthFlowRateTrucks(int northFlowRateTrucks) {
		this.northFlowRateTrucks = northFlowRateTrucks;
	}
	public int getSouthFlowRateTrucks() {
		return southFlowRateTrucks;
	}
	public void setSouthFlowRateTrucks(int southFlowRateTrucks) {
		this.southFlowRateTrucks = southFlowRateTrucks;
	}
}
