package proj5;

/**
 * This class models the moving exceptions used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class TrainMovingException extends Exception{
	
	boolean isTrainMoving;
	String command;
	String boxCarType;
	int maxContents;
	
	public TrainMovingException(boolean isTrainMoving, String command, String boxCarType, int maxContents){
		this.isTrainMoving = isTrainMoving;
		this.command = command;
		this.boxCarType = boxCarType;
		this.maxContents = maxContents;
	}

	/**
     * Creates a string for what went wrong
     * Preconditions: None
     * Postconditions: none
     * @return String of what went wrong
     */
	public String getMessage(){
		if(isTrainMoving == true && command.equals("ADDCAR")){
			return "ADDCAR " + boxCarType + " " + maxContents + "\n	ERROR: Car cannot be added when the train is moving.";
		}
		else if(isTrainMoving == true && command.equals("REMOVECAR")){
			return "REMOVECAR " + boxCarType + " " + maxContents + "\n	ERROR: Car cannot be removed when the train is moving.";
		}
		else if(isTrainMoving == false){
			return "ARRIVE\n	ERROR: Train cannot ARRIVE when it has not departed for anywhere.";
		}
		else if(isTrainMoving == true){
			return "DEPART\n	ERROR: Train cannot DEPART when it has not arrived at its current destination.";
		}
		return "This was thrown by TrainMovingException. This should not have been thrown.";
	}
}
