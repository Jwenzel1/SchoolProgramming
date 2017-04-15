package proj5;

import java.util.Random;

/**
* @version 5/13/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 5
* Section 02
*/

public class Satellite implements Runnable{
	
	private Buffer buffer;
	private boolean keepRunning = true;
	private Random random = new Random();
	private int numbersAdded = 0;
	
	/**
	 * Creates an object that will continually add random integers
	 * to the given buffer
	 * @param buffer
	 */
	public Satellite(Buffer buffer){
		this.buffer = buffer;
	}

	/**
	 * Begins adding integers to the given buffer
	 * continues until the stopRunning() method is called
	 */
	public void run(){
		while(keepRunning){
			if(!buffer.isFull()){
				buffer.add(random.nextInt(4097));
				numbersAdded++;
			}
//			if(numbersAdded%500000 == 0){
//				System.out.println("hit the if.");
//			}
		}
	}
	
	/**
	 * stops the run method from running
	 */
	synchronized public void stopRunning(){
		keepRunning = false;
	}
	
}
