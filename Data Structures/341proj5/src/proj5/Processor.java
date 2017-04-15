package proj5;

/**
* @version 5/13/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 5
* Section 02
*/

public class Processor implements Runnable{

	private boolean keepRunning = true;
	private Buffer buffer;
	private int[] dataFromBuffer;
	private boolean hasDataFromBuffer = false;
	
	/**
	 * creates the processor which will remove elements from the buffer
	 * @param buffer
	 */
	public Processor(Buffer buffer){
		this.buffer = buffer;
	}
	
	/**
	 * runs the thread
	 */
	public void run(){
		while(keepRunning){
			if(buffer.isFullEnough()){
				dataFromBuffer = buffer.getDataFromBuffer();
				hasDataFromBuffer = true;
			}
		}
	}
	
	/**
	 * stops the thread from running
	 */
	public void stopRunning(){
		keepRunning = false;
	}
	
	/**
	 * checks if the processor thread has the data from the buffer
	 * @return
	 */
	synchronized public boolean hasDataFromBuffer(){
		return hasDataFromBuffer;
	}
	
	/**
	 * gets the data that the processor took from the buffer, from the processor
	 * @return
	 */
	synchronized public int[] getDataFromProcessor(){
		hasDataFromBuffer = false;
		return dataFromBuffer;
	}
}
