package proj5;

/**
 * This class models the Person used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class Person implements Comparable{

	private String govID;
	private String name;
	private int age;

	public Person(String govID, String name, int age) {
		this.govID = govID;
		this.name = name;
		this.age = age;
	}
	
	/**
     * Compares ID's
     * Preconditions: None
     * Postconditions: None
     * @return Difference between th strings
     */
	public int compareTo(Object o) {
		if(o instanceof String){
			return govID.compareTo((String)o);
		}
		return govID.compareTo((((Person)o).getID()));
	}

	/**
     * Gets the ID
     * Preconditions: None
     * Postconditions: None
     * @return String of the ID
     */
	public String getID() {
		return govID;
	}


	/**
     * Creates a string for the person
     * Preconditions: None
     * Postconditions: None
     * @return String of the persons info
     */
	public String toString() {
		return "	" + govID + ": Name: " + name + ": Age: " + age + "\n";
	}




}
