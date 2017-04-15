package proj5;

import java.util.ArrayList;

/**
 * This class models the Train used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */

public class Train {

	private String cityOfOrigin;
	private String destination;
	private int minSpeed;
	private int currentSpeed = 0;
	private int maxSpeed;
	private int maxNumOfBoxcars;
	private boolean moving = false;
	private ArrayList<Boxcar> boxcars = new ArrayList<Boxcar>();

	public Train(String cityOfOrigin, int minSpeed, int maxSpeed, int maxNumOfBoxcars) {
		this.cityOfOrigin = cityOfOrigin;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.maxNumOfBoxcars = maxNumOfBoxcars;
	}


	/**
     * Creates a string for the overall status of the train
     * Preconditions: None
     * Postconditions: None
     * @return String of the Information
     */
	public String print() {
		String logString = "";
		logString += "PRINT\n";
		logString += "Train Status\n";
		logString += "----------------\n";
		logString += "	Current Speed: " + currentSpeed + "\n";
		logString += "	Minimum Speed: " + minSpeed + "\n";
		logString += "	Maximum Speed: " + maxSpeed + "\n";
		logString += "	Current Position:	";
		if (moving == false) {
			logString += "Stopped in " + cityOfOrigin + "\n";
		} 
		else {
			logString += "Travelling from " + cityOfOrigin + " to " + destination + "\n";
		}
		logString += "	Current number of Boxcars: " + boxcars.size() + "\n";
		logString += "	Maximum number of Boxcars: " + maxNumOfBoxcars + "\n";
		for(int i = 0; i<boxcars.size(); i++){
			logString += "	BoxCar: " + i + "\n";
			logString += "	------------\n";
			logString += "	Contents:\n";
			if(boxcars.get(i).getSize() > 0 && boxcars.get(i).getContents().get(0) instanceof Cargo){
				for(int j = 0; j < boxcars.get(i).getContents().size(); j++){
					int indexOfBox = j+1;
					logString += "		"+ indexOfBox + ":" + boxcars.get(i).getContents().get(j).toString();
				}
				
			}
			else{
				for(int j = 0; j<boxcars.get(i).getContents().size(); j++){
					logString += "	" + boxcars.get(i).getContents().get(j).toString();
				}
			}
		}
		return logString;
	}

	/**
     * Creates a String if the train has arrived
     * Preconditions: None
     * Postconditions: None
     * @return String of the arrive
     */
	public String arrive() throws TrainMovingException{
		String command = "ARRIVE";
		if(moving == false){
			throw new TrainMovingException(moving, command, new String("Dummy String"), 0);
		}
		cityOfOrigin = destination;
		moving = false;
		currentSpeed = 0;
		return "ARRIVE";
		
	}

	/**
     * Creates a string if the train departed
     * Preconditions: None
     * Postconditions: None
     * @return String saying if the train departed
     */
	public String depart(String city) throws TrainMovingException{
		String command = "DEPART";
		if(moving == true){
			throw new TrainMovingException(moving, command, new String("Dummy String"), 0);
		}
		moving = true;
		destination = city;
		currentSpeed = minSpeed;
		return "DEPART " + city + "\n";
	}

	/**
     * Creates a string if the train speeded up
     * Preconditions: None
     * Postconditions: None
     * @return String of the status of the trains speedup
     */
	public String speedUp(int milesPerHour) throws TrainSpeedException{
		String command = "SPEEDUP";
		String logString = "SPEEDUP " + milesPerHour;
		if (moving != true || currentSpeed + milesPerHour > maxSpeed) {
			throw new TrainSpeedException(maxSpeed, minSpeed, currentSpeed, milesPerHour, moving, true, command);
		}
		currentSpeed += milesPerHour;
		return logString;
	}

	/**
     * Slows down the train
     * Preconditions: None
     * Postconditions: none
     * @return String saying if the train slowed down
     */
	public String slowDown(int milesPerHour) throws TrainSpeedException{
		String command = "SLOWDOWN";
		String logString = "SLOWDOWN " + milesPerHour;
		if (moving != true || currentSpeed - milesPerHour < minSpeed) {
			throw new TrainSpeedException(maxSpeed, minSpeed, currentSpeed, milesPerHour, moving, false, command);
		}
		currentSpeed -= milesPerHour;
		return logString;
	}

	/**
     * Adds a car to the train
     * Preconditions: None
     * Postconditions: None
     * @return A string saying whatw was added
     */
	public String addCar(String boxCarType, int maxNumberOfElements) throws TrainMovingException{
		String command = "ADDCAR";
		if(moving == true){
			throw new TrainMovingException(moving, command, boxCarType, maxNumberOfElements);
		}
		if(boxCarType.equals("PERSON")){
			boxcars.add(new Boxcar<Person>(maxNumberOfElements));
		}
		else if(boxCarType.equals("CARGO")){
			boxcars.add(new Boxcar<Cargo>(maxNumberOfElements));
		}
		return "ADDCAR " + boxCarType + " " + maxNumberOfElements;
	}

	/**
     * Removes a car from the train
     * Preconditions: None
     * Postconditions: None
     * @return String saying what was removed
     */
	public String removeCar(int boxcarID) {
		String logString = "REMOVECAR " + boxcarID +"\n";
		if(moving == true){
			logString += "	ERROR: Train is still moving.";
			return logString;
		}
		else if(boxcars.get(boxcarID).getSize() != 0){
			logString += "	ERROR: Boxcar " + boxcarID + " is not empty.";
			return logString;
		}

		boxcars.remove(boxcarID);
		return logString;
	}
	
	/**
     * loads something into a boxcar in the train
     * Preconditions: None
     * Postconditions: None
     * @return String saying what was loaded on
     */
	public String load(int boxcarIndex, Comparable thing) throws TrainMovingException, BoxcarException{
		String command = "LOAD";
		if(moving == true){
			throw new TrainMovingException(moving, command, thing.getClass().toString(), boxcars.get(boxcarIndex).getCapacity());
		}
		else if(boxcars.get(boxcarIndex).getSize() == boxcars.get(boxcarIndex).getCapacity()){
			throw new BoxcarException(boxcars.get(boxcarIndex).getSize(), boxcars.get(boxcarIndex).getCapacity(), boxcarIndex);
		}
		
		boxcars.get(boxcarIndex).load(thing);
		return "LOAD: " + boxcarIndex + thing.toString();
	}
	
	/**
     * Unloads something from the train
     * Preconditions: None
     * Postconditions: None
     * @return String saying what was unloaded
     */
	public String unload(int boxcarIndex, String item) throws BoxcarException{
		String command = "UNLOAD";
		if(moving == true){
			return "CANNOT UNLOAD WHILE TRAIN IS MOVING";
		}
		return boxcars.get(boxcarIndex).unload(item);
		
		
	}
	//*************************************************************************************************************************
}
