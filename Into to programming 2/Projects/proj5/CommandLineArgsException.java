package proj5;

/**
 * This class models the CommandLineexception used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */
public class CommandLineArgsException extends Exception{
	
	public CommandLineArgsException(){}
	

	/**
     * Creates a string For the problem detected
     * Preconditions: None
     * Postconditions: None
     * @return aString syaing whats wrong
     */
	public String getMessage(){
		return "Not enough command line args detected. Now Exiting.";
	}
}
