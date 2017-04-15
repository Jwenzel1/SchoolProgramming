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

public class Node {
	
	private Node nextNode;
	private ResultVehicle resultVehicle;
	
	/**
	 * Constructor - Creates an instance of a Node.
	 * @param resultVehicle
	 */
	public Node(ResultVehicle resultVehicle){
		this.resultVehicle = resultVehicle;
		this.nextNode = null;
	}

	/**
	 * Setters and Getters
	 * @return
	 */
	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public ResultVehicle getResultVehicle() {
		return resultVehicle;
	}

	public void setResultVehicle(ResultVehicle resultVehicle) {
		this.resultVehicle = resultVehicle;
	}
	
	/**
	 * This returns the very last node in the list
	 * needed for adding a node to the list
	 */
	public Node getLastNode(){
		if(nextNode == null){
			return this;
		}
		else{
			return nextNode.getLastNode();
		}
	}
	
	public String toString(){
		return "Node\n";
	}
}
