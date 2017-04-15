package Project1;

/**
 * This class is the Linked list used in the TrafficSim class
 * Class Invariants:
 *   - None
 * @version 02/24/14
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 341 - Spring 2014 - Project 1
 * @section 02
 */

public class LinkedList{
	
	private Node head = null;
	private Node cursor = null;
	
	/**
	 * Adds a node to the linked list 
	 * @param vehicle
	 */
	public void addNode(ResultVehicle vehicle){
		if(head == null){
			head = new Node(vehicle);
			cursor = head;
		}
		else{
			head.getLastNode().setNextNode(new Node(vehicle));
		}
	}
	
	/**
	 * Calculates the average wait time of all the vehicles in the linked list
	 */
	public double getAverageWaitTime(){
		double waitTime = 0;
		while(cursor != null){
			waitTime += cursor.getResultVehicle().waitTime();
			cursor = cursor.getNextNode();
		}
		cursor = head;
		return waitTime/length();
	}
	
	/**
	 * Returns how many elements are in the linked list 
	 */
	public int length(){
		int length = 0;
		while(cursor != null){
			length++;
			cursor = cursor.getNextNode();
		}
		cursor = head;
		return length;
	}
	
	/**
	 * Returns how many cars are in the linked list 
	 */
	public int getCars(){
		int cars = 0;
		while(cursor != null){
			if(cursor.getResultVehicle().getType() == 'c'){
			 cars++;
			}
			cursor = cursor.getNextNode();
		}
		cursor = head;
		return cars;
	}
	
	/**
	 * Returns how many trucks are in the linked list 
	 */
	public int getTrucks(){
		int trucks = 0;
		while(cursor != null){
			if(cursor.getResultVehicle().getType() == 't'){
			 trucks++;
			}
			cursor = cursor.getNextNode();
		}
		cursor = head;
		return trucks;
	}
	
	public String toString(){
		String nodeString = "";
		while(cursor != null){
			nodeString += cursor.toString();
			cursor = cursor.getNextNode();
		}
		cursor = head;
		return nodeString;
	}

}
