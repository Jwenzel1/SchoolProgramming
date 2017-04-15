package proj5;

/**
* @version 5/13/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 5
* Section 02
*/

public class Buffer {

	private int[] list;
	private int maxSize;
	private int N;
	private int begining = 0;
	private int end = 0;
	private boolean isFullEnough = false;
	private int numberOfElements = 0;
	
	/**
	 * Creates a new buffer object to store ints.
	 * In this case the buffer is a circular array
	 * @param N
	 */
	public Buffer(int N){
		this.N = N;
		maxSize = (N*N)*2;
		list = new int[maxSize];
	}
	
	/**
	 * Adds a number to the buffer
	 * @param number
	 */
	public void add(int number){
		list[end] = number;
		end++;
		numberOfElements++;
		if(end >= maxSize){
			end = 0;
		}
	}
	
	/**
	 * Checks if the buffer is full or not
	 * @return
	 */
	public boolean isFull(){
		return numberOfElements == maxSize;
		}
	
	/**
	 * Checks if the buffer is full enough for for the processor thread
	 * to take N squared elements out of it
	 * @return
	 */
	synchronized public boolean isFullEnough(){
		if(numberOfElements >= (N*N)){
			isFullEnough = true;
			return isFullEnough;
		}
		isFullEnough = false;
		return isFullEnough;
	}
	
	/**
	 * Gets the data out of the buffer for the processor thread
	 * @return
	 */
	synchronized public int[] getDataFromBuffer(){
		int[] returnArray = new int[(N*N)];
		for(int i = 0; i< (N*N); i++){
			returnArray[i] = list[begining++];
			numberOfElements--;
			if(begining == maxSize){
				begining = 0;
			}
		}
		return returnArray;
	}
	
	/**
	 * returns how many elements are in the buffer
	 * @return
	 */
	synchronized public int length(){
		 return numberOfElements++;
	 }
}

