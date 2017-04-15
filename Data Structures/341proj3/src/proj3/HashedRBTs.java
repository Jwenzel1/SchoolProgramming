package proj3;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
* @version 4/8/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 3
* Section 02
*/
public class HashedRBTs<AnyType> {

	private int size;
	private ArrayList<RedBlackTree<Partial>> table;
	
	/**
	 * Creates an arraylist of of red black trees. 
	 * The arraylist will hold as many redblack trees as the inputted sized.  
	 * @param size of the array list
	 */
	public HashedRBTs(int size){
		this.size = size;
		table = new ArrayList<RedBlackTree<Partial>>(size);
		for(int i = 0; i < size; i++){
			table.add(new RedBlackTree<Partial>());
		}
	}
	
	/**
	 * Prints out all the red black trees in the arraylist.
	 * This prints out what the root node is how many nodes are in that tree.
	 */
	public void printHashCountResults(){
		for(int i = 0; i < size; i++){
			System.out.print("This tree starts ");
			if(table.get(i).numberOfNodes() != 0){
				System.out.print("with ");
				table.get(i).printRoot();
			}
			else{
				System.out.println("has no nodes");
				
			}	
		}
	}
	
	/**
	 * Reads in the file with the given file name the parses all the words in that file
	 * and inserts the parsed words in the respective tree.
	 * @param filename - name of the file
	 */
	public void fileReader(String filename){
		try{
			Scanner scanner = new Scanner(new File(filename));

			while(scanner.hasNext()){

				String word = scanner.nextLine();
				String[] wordArray = word.split(" ");
				if(!wordArray[0].equals("Empty")){
					String text = wordArray[1].replace("[", "").replace("word=", "").replace(",", "");
					int number = Integer.parseInt(wordArray[2].replace("frequency=", "").replace("]", ""));
					char leadingLetter = text.charAt(0);
					//convert the character to ascii
					int asciiValue = (int)leadingLetter;
					if(asciiValue < '{' && asciiValue > '`'){
						asciiValue -= 'a';
						asciiValue += 26;
					}
					if(asciiValue < '[' && asciiValue > '@'){
						asciiValue -= 'A';
					}
					Node node = new Node(text, number);
					Partial data = new Partial(node);
					table.get(asciiValue).add(data);
					if(table.get(asciiValue).retreiveIfItContains(data) == null){
						table.get(asciiValue).add(data);
					}
					Partial thing = table.get(asciiValue).retreiveIfItContains(data);
					thing.insertNodeIntoHeap(node);
				}
			}
			scanner.close();
		}
		catch(Exception e){
			System.out.println("File was not found. Program will now end.");
			System.exit(1);
		}
	}
	
	
	/**
	 * Gets a tree out of the arraylist
	 * @param i - index of the tree in the arraylist
	 * @return the binary tree in the arraylist
	 */
	public RedBlackTree<Partial> retreiveHashedRBTat(int i) {
		return table.get(i);
	}
	
}
