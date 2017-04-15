package Project1;

/**
 * This class is the Traffic simulator. 
 * It runs all the other classes.
 * Class Invariants:
 *   - None
 * @version 02/24/14
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 341 - Spring 2014 - Project 1
 * @section 02
 */

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.*;

public class TrafficSim {
	
	private Queue<Vehicle> eastBound = new ArrayDeque<Vehicle>();
	private Queue<Vehicle> westBound = new ArrayDeque<Vehicle>();
	private Queue<Vehicle> northBound = new ArrayDeque<Vehicle>();
	private Queue<Vehicle> southBound = new ArrayDeque<Vehicle>();
	private LinkedList results = new LinkedList();
	private IntersectionFlowRate flowrate;
	private boolean northSouthGreen = true;
	private boolean eastWestGreen = false;
	private int northSouthMinDuration = 30;
	private int eastWestMinDuration = 10;
	private int eastWestMaxDuration = 30;
	private int timeNorthSouthHasBeenGreen = 0;
	private int timeEastWestHasBeenGreen = 0;
	private int currentTime = 0;
	
	/**
	 * Constructor - Creates an instance of the traffic simulator.
	 * @param the name of the input file
	 */
	public TrafficSim(String input){
		
		readFromFile(input);

		startQueues();
		printBoard();
		currentTime++;
		
		boolean northTruckWait = false;
		boolean southTruckWait = false;
		boolean eastTruckWait = false;
		boolean westTruckWait = false;
		
		while(currentTime <= 120){

			lightCheck();

			if(northSouthGreen){
				if(northBound.peek() != null && northBound.peek().getType() == 'c'){
					results.addNode(new ResultVehicle(northBound.peek(), currentTime));
					northBound.remove();
				}
				else if(northBound.peek() != null && northBound.peek().getType() == 't' && northTruckWait == false){
					northTruckWait = true;
				}
				else if(northBound.peek() != null && northBound.peek().getType() == 't' && northTruckWait == true){
					northTruckWait = false; 
					results.addNode(new ResultVehicle(northBound.peek(), currentTime));
					northBound.remove();
				}
				
				if(southBound.peek() != null && southBound.peek().getType() == 'c'){
					results.addNode(new ResultVehicle(southBound.peek(), currentTime));
					southBound.remove();
				}
				else if(southBound.peek() != null && southBound.peek().getType() == 't' && southTruckWait == false){
					southTruckWait = true;
				}
				else if(southBound.peek() != null && southBound.peek().getType() == 't' && southTruckWait == true){
					southTruckWait = false; 
					results.addNode(new ResultVehicle(southBound.peek(), currentTime));
					southBound.remove();
				}
				timeNorthSouthHasBeenGreen++;
			}

			else if(eastWestGreen){
				if(eastBound.peek() != null && eastBound.peek().getType() == 'c'){
					results.addNode(new ResultVehicle(eastBound.peek(), currentTime));
					eastBound.remove();
				}
				else if(eastBound.peek() != null && eastBound.peek().getType() == 't' && eastTruckWait == false){
					eastTruckWait = true;
				}
				else if(eastBound.peek() != null && eastBound.peek().getType() == 't' && eastTruckWait == true){
					eastTruckWait = false; 
					results.addNode(new ResultVehicle(eastBound.peek(), currentTime));
					eastBound.remove();
				}
				
				if(westBound.peek() != null && westBound.peek().getType() == 'c'){
					results.addNode(new ResultVehicle(westBound.peek(), currentTime));
					westBound.remove();
				}
				else if(westBound.peek() != null && westBound.peek().getType() == 't' && westTruckWait == false){
					westTruckWait = true;
				}
				else if(westBound.peek() != null && westBound.peek().getType() == 't' && westTruckWait == true){
					westTruckWait = false; 
					results.addNode(new ResultVehicle(westBound.peek(), currentTime));
					westBound.remove();
				}
				timeEastWestHasBeenGreen++;
			}
			addCarsToQueues(currentTime);
			printBoard();
			currentTime++;
		}
		System.out.print("The final results are:\n");
		System.out.printf("The number of vehicles that passed through the intersection is: %d\n", results.length());
		System.out.printf("The number of cars that passed through the intersection is: %d\n", results.getCars());
		System.out.printf("The number of trucks that passed through the intersection is: %d\n", results.getTrucks());
		System.out.printf("The average wait time for this intersection is: %f", results.getAverageWaitTime());
	}
	
	/**
	 * Checks to see if the traffic light needs to change.
	 */
	private void lightCheck(){
		
//		if(northSouthGreen && timeNorthSouthHasBeenGreen >= northSouthMinDuration && northBound.isEmpty() && southBound.isEmpty()){
//			timeNorthSouthHasBeenGreen = 0;
//			northSouthGreen = false;
//			eastWestGreen = true;
//			System.out.print("Light has to change. NS is empty, other side (EW) is waiting.\n");
//		}
		//if the north/south light is green and it has been green for 30 mins then change the light
		if(northSouthGreen && timeNorthSouthHasBeenGreen >= northSouthMinDuration){
			timeNorthSouthHasBeenGreen = 0;
			northSouthGreen = false;
			eastWestGreen = true;
			System.out.print("Light has to change. Met minimum length green, other side (EW) is waiting.\n");
		}
		//if east and westbound lanes are empty and the light has been on for its minimum duration then change the light
		else if(eastWestGreen && timeEastWestHasBeenGreen > eastWestMinDuration && eastBound.isEmpty() && westBound.isEmpty()){
			timeEastWestHasBeenGreen = 0;
			eastWestGreen = false;
			northSouthGreen = true;
			System.out.print("Light has to change. EW is empty, other side (NS) is primary, try to keep green.\n");
		}
		//if east and westbound light has been on for too long switch to the other light
		else if(eastWestGreen && timeEastWestHasBeenGreen > eastWestMaxDuration){
			timeEastWestHasBeenGreen = 0;
			eastWestGreen = false;
			northSouthGreen = true;
			System.out.print("Light has to change. Met maximum length green, other side (NS) is waiting.\n");
		}
	}
	
	/**
	 * Adds cars to queues given the current flowrates
	 * @param currentTime
	 */
	private void addCarsToQueues(int currentTime){
		int timeToAddCarNorth = 60/flowrate.getNorthFlowRateCars();
		int timeToAddTruckNorth = 60/flowrate.getNorthFlowRateTrucks();
		int timeToAddCarSouth = 60/flowrate.getSouthFlowRateCars();
		int timeToAddTruckSouth = 60/flowrate.getSouthFlowRateTrucks();
		int timeToAddCarEast = 60/flowrate.getEastFlowRateCars();
		int timeToAddTruckEast = 60/flowrate.getEastFlowRateTrucks();
		int timeToAddCarWest = 60/flowrate.getWestFlowRateCars();
		int timeToAddTruckWest = 60/flowrate.getWestFlowRateTrucks();
		
		if(currentTime % timeToAddCarNorth == 0 && currentTime % timeToAddTruckNorth == 0){
			addVehicle('n', new Vehicle('c', currentTime));
			addVehicle('n', new Vehicle('t', currentTime));
		}
		else if(currentTime % timeToAddCarNorth == 0){
			addVehicle('n', new Vehicle('c', currentTime));
		}
		else if(currentTime % timeToAddTruckNorth == 0){
			addVehicle('n', new Vehicle('t', currentTime));
		}
		
		if(currentTime % timeToAddCarSouth == 0 && currentTime % timeToAddTruckSouth == 0){
			addVehicle('s', new Vehicle('c', currentTime));
			addVehicle('s', new Vehicle('t', currentTime));
		}
		else if(currentTime % timeToAddCarSouth == 0){
			addVehicle('s', new Vehicle('c', currentTime));
		}
		else if(currentTime % timeToAddTruckSouth == 0){
			addVehicle('s', new Vehicle('t', currentTime));
		}
		
		if(currentTime % timeToAddCarEast == 0 && currentTime % timeToAddTruckEast == 0){
			addVehicle('e', new Vehicle('c', currentTime));
			addVehicle('e', new Vehicle('t', currentTime));
		}
		else if(currentTime % timeToAddCarEast == 0){
			addVehicle('e', new Vehicle('c', currentTime));
		}
		else if(currentTime % timeToAddTruckEast == 0){
			addVehicle('e', new Vehicle('t', currentTime));
		}
		
		if(currentTime % timeToAddCarWest == 0 && currentTime % timeToAddTruckWest == 0){
			addVehicle('w', new Vehicle('c', currentTime));
			addVehicle('w', new Vehicle('t', currentTime));
		}
		else if(currentTime % timeToAddCarWest == 0){
			addVehicle('w', new Vehicle('c', currentTime));
		}
		else if(currentTime % timeToAddTruckWest == 0){
			addVehicle('w', new Vehicle('t', currentTime));
		}
		
	}
	
	/**
	 * adds 2 cars to each vehicle to start out the innersection
	 */
	private void startQueues(){
		
		for(int i = 0; i < 2; i++){
			addVehicle('n', new Vehicle('c', 0));
			addVehicle('s', new Vehicle('c', 0));
			addVehicle('e', new Vehicle('c', 0));
			addVehicle('w', new Vehicle('c', 0));
		}
		
	}
	
	/**
	 * prints out the board to see what is happening with the innersection
	 */
	private void printBoard(){
		System.out.print("   SB   " + southBound.size() + "\n");
		
		for(int i  = 5; i > 1; i--){
			if(southBound.size() >= i + 1){
				System.out.print("      x\n");
			}
			else{
				System.out.print("\n");
			}
		}
		System.out.print("EB    ");
		if(southBound.size() > 1){
			System.out.print("x\n");
		}
		else{
			System.out.print("\n");
		}
		if(eastBound.size() > 9){
			System.out.printf("%d    ", eastBound.size());
		}
		else{
			System.out.printf(" %d    ", eastBound.size());
		}
		
		if(southBound.peek() != null){
			
			if(southBound.peek().getType() == 'c'){
				System.out.print("c\n");
			}
			else if(southBound.peek().getType() == 't'){
				System.out.print("t\n");
			}
		}
		else{
			System.out.print("\n");
		}
		
		for(int i  = 5; i >= 1; i--){
			if(eastBound.size() >= i + 1){
				System.out.print("x");
			}
			else{
				System.out.print(" ");
			}
		}
		
		if(eastBound.peek() != null){
			
			if(eastBound.peek().getType() == 'c'){
				System.out.print("c");
			}
			else if(eastBound.peek().getType() == 't'){
				System.out.print("t");
			}
		}
		
		System.out.print(" ");
		
		if(westBound.peek() != null){
			
			if(westBound.peek().getType() == 'c'){
				System.out.print("c");
			}
			else if(westBound.peek().getType() == 't'){
				System.out.print("t");
			}
		}
		
		for(int i  = 5; i >= 1; i--){
			if(westBound.size() >= i + 1){
				System.out.print("x");
			}
		}
		System.out.print("\n");
		if(northBound.peek() != null){
			
			if(northBound.peek().getType() == 'c'){
				System.out.print("      c\n");
			}
			else if(northBound.peek().getType() == 't'){
				System.out.print("      t\n");
			}
		}
		else{
			System.out.print("\n");
		}

		if(northBound.size() > 1){
			System.out.print("      x    WB\n");
		}
		else{
			System.out.print("           WB\n");
		}
		if(northBound.size() > 2){
			System.out.print("      x     " + westBound.size() + "\n");
		}
		else{
			System.out.print("            " + westBound.size() + "\n");
		}
		
		for(int i  = 5; i > 3; i--){
			if(northBound.size() >= i + 1){
				System.out.print("      x\n");
			}
			else{
				System.out.print("\n");
			}
		}
		System.out.print("    NB   " + northBound.size() + "\n");
		System.out.print("at clock:  " + currentTime + "\n");
		System.out.print("--------------------------------------\n");
		
		
	}
	
	/**
	 * adds a vehicle to the correct queue
	 * @param direction
	 * @param x
	 */
	private boolean addVehicle(char direction, Vehicle x){
		
		boolean vehicleAdded = false;
		
		if(direction == 'n'){
			northBound.add(x);
			vehicleAdded = true;
			return vehicleAdded;
		}
		else if(direction == 's'){
			southBound.add(x);
			vehicleAdded = true;
			return vehicleAdded;
		}
		else if(direction == 'e'){
			eastBound.add(x);
			vehicleAdded = true;
			return vehicleAdded;
		}
		else if(direction == 'w'){
			westBound.add(x);
			vehicleAdded = true;
			return vehicleAdded;
		}
		
		return vehicleAdded;
	}
		
	/**
	 * reads the file given in the traffic sim constructor
	 * @param inputFile
	 */
	private boolean readFromFile(String inputFile){
		
		boolean readFile = false;
		Scanner reader;
		int eastFlowRateC;
		int westFlowRateC;
		int northFlowRateC;
		int southFlowRateC;
		int eastFlowRateT;
		int westFlowRateT;
		int northFlowRateT;
		int southFlowRateT;
		
		try {
			reader = new Scanner(new FileReader(inputFile));
			} 
		
		catch (FileNotFoundException e) {
			System.out.print("The file was not found.");
			return readFile;
		}
		readFile = true;
		
		//gets all the flowrates out of the input file
		reader.next();
		northFlowRateC = reader.nextInt();
		northFlowRateT = reader.nextInt();
		reader.next();
		southFlowRateC = reader.nextInt();
		southFlowRateT = reader.nextInt();
		reader.next();
		eastFlowRateC = reader.nextInt();
		eastFlowRateT = reader.nextInt();
		reader.next();
		westFlowRateC = reader.nextInt();
		westFlowRateT = reader.nextInt();
		reader.close();
		
		 //creates the flowrate instance
		flowrate = new IntersectionFlowRate(eastFlowRateC, westFlowRateC, northFlowRateC, southFlowRateC, eastFlowRateT, westFlowRateT, northFlowRateT, southFlowRateT);
		return readFile;
	
	}
}
