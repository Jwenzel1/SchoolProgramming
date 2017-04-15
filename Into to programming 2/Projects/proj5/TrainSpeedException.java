package proj5;

/**
 * This class models the train speed exceptions used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class TrainSpeedException extends Exception{

	int maxSpeed;
	int minSpeed;
	int currentSpeed;
	int speedChange;
	boolean isTrainMoving;
	boolean speedUp;
	String command;
	
	public TrainSpeedException(int maxSpeed, int minSpeed, int currentSpeed, int speedChange, boolean isTrainMoving, boolean speedUp, String command){
		this.maxSpeed = maxSpeed;
		this.minSpeed = minSpeed;
		this.currentSpeed = currentSpeed;
		this.speedChange = speedChange;
		this.isTrainMoving = isTrainMoving;
		this.speedUp = speedUp;
		this.command = command;
	}
	
	/**
     * Creates a string telling what went wrong
     * Preconditions: None
     * Postconditions: None
     * @return String of what went wrong
     */
	public String getMessage(){
		if(isTrainMoving == false && command.equals("SPEEDUP")){
			return "SPEEDUP " + speedChange +"\n	ERROR: Train is not moving.";
		}
		else if(isTrainMoving == false && command.equals("SLOWDOWN")){
			return "SLOWDOWN " + speedChange +"\n	ERROR: Train is not moving.";
		}
		else if(currentSpeed + speedChange > maxSpeed && speedUp == true){
			return "SPEEDUP " + speedChange +"\n	ERROR: Speed can not be increased, it would exceed its maximum speed.";
		}
		else if(currentSpeed + speedChange < minSpeed){
			return "SLOWDOWN " + speedChange +"\n	ERROR: Speed can not be decreased, it would become lower than its minimum speed.";
		}
		else{ 
			return "This message was thrown from TrainSpeedException. This message should not have been given. A problem occurred.";
		}
	}
}
