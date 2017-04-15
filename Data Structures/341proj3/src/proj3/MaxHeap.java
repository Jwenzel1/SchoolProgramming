package proj3;

import java.util.ArrayList;

/**
* @version 4/8/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 3
* Section 02
*/
public class MaxHeap<AnyType extends Comparable<? super AnyType>>{
	
	ArrayList<AnyType> heap;
	/**
	 * Constructs the max heap
	 */
	public MaxHeap(){
		heap = new ArrayList<AnyType>();
	}
	
	/**
	 * inserts an item into the max heap
	 * @param x
	 */
	public void insert(AnyType x){
		if (has(x)){
			return;
		}
		heap.add(null);
		int things = heap.size() - 1;
		for (heap.set(0, x); x.compareTo(heap.get(things / 2)) > 0; things /= 2){
			heap.set(things, heap.get(things / 2));
		}
		heap.set(things, x);
		heap.set(0, null);
	}
	
	/**
	 * prints the first few things in the heap
	 */
	public void printImmediateOptions(){
		for(int i = 0; i < heap.size() && i < 3; i++){
			if(heap.get(i) != null){
				System.out.println(heap.get(i));
			}
		}
	}
	
	/**
	 * checks if the heap is empty returns true if it is
	 * @return
	 */
	public boolean isEmpty(){
		if (heap.get(1) != null){
			return true;
		}
		return false;
	}
	
	/**
	 * returns a string representation of the heap
	 */
	public String toString(){
		String string = " --> The heaps contains:\n";
		for (int i = 1; i < heap.size(); i++){
			string += "[" + i + "] " + heap.get(i) + "\n";
		}
		return string;
	}
	
	/**
	 * prints the root of the heap
	 */
	public void printHeapRoot(){
		if(heap.get(1) != null){
			System.out.println(heap.get(1));
		}
		else{
			System.out.println("Empty Heap.");
		}
	}
	
	/**
	 * checks to see if the heap contains the given thing
	 * returns true if it does and false otherwise
	 * @param x
	 * @return
	 */
	public boolean has(AnyType x){
		if (heap.size() > 1){
			for (int i = 1; i < heap.size(); i++){
				if (x.compareTo(heap.get(i)) == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * gets the heap
	 * @return
	 */
	public ArrayList<AnyType> getHeap(){
		return heap;
	}
	
}
