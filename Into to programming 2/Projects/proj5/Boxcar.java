package proj5;

import java.util.ArrayList;

/**
 * This class models the Boxcar used in Project5.java
 * Class Invariants:
 *   - None
 * @version 12/10/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 5
 * @section 06
 */

public class Boxcar<T extends Comparable> {

	private ArrayList<T> contents = new ArrayList<T>();
	private int maxCapacity;

	public Boxcar(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	/**
     * Sorts the contents of the boxcar
     * Preconditions: None
     * Postconditions: Contents are Sorted
     */
	private void sort(){
		for(int i = 0; i < contents.size(); i++){
			for(int j = 0; j < contents.size()-1; j++){
				if(contents.get(j).compareTo(contents.get(j+1)) > 0){
					T temp = contents.remove(j);
					contents.add(j+1, temp);
				}
			}
		}
	}

	/**
     * Puts a thing in a boxcar
     * Preconditions: None
     * Postconditions: AA thing is in the boxcar
     */
	public void load(T thing) throws BoxcarException{
		for(int i = 0; i < contents.size(); i++){
			if(contents.get(i).compareTo(thing) == 0){
				throw new BoxcarException();
			}
		}
		contents.add(thing);
		sort();
	}
	
	/**
     * Takes a thing out of a boxcar
     * Preconditions: None
     * Postconditions: AA thing is taken out of a boxcar
     * @return String of what was removed
     */
	public String unload(String item) throws BoxcarException{
		for(int i = 0; i<contents.size(); i++){
			if(contents.get(i).compareTo(item) == 0){
				contents.remove(i);
				return "UNLOAD\n	Item Removed";
			}
		}
		throw new BoxcarException();
	}
	
	/**
	 * general getters
	 * 
	 */
	public int getSize(){
		return contents.size();
	}
	
	public ArrayList<T> getContents(){
		return contents;
	}
	
	public int getCapacity(){
		return maxCapacity;
	}
}
