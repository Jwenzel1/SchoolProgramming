package proj2;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
* @version 3/21/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 2
* Section 02
*/
public class HashedBSTs<AnyType> {

	private int size;
	private ArrayList<BinarySearchTree<Node>> table;
	
	/**
	 * Creates an arraylist of of binary search trees. 
	 * The arraylist will hold as many binary search trees as the inputted sized.  
	 * @param size of the array list
	 */
	public HashedBSTs(int size){
		this.size = size;
		table = new ArrayList<BinarySearchTree<Node>>(size);
		for(int i = 0; i < size; i++){
			table.add(new BinarySearchTree<Node>());
		}
	}
	
	/**
	 * Prints out all the binary search trees in the arraylist.
	 * This prints out what the root node is how many nodes are in that tree.
	 */
	public void printHashCountResults(){
		for(int i = 0; i < size; i++){
			System.out.print("This tree starts ");
			if(table.get(i).numberOfNodes() != 0){
				System.out.print("with ");
				table.get(i).printRoot();
				System.out.printf(" and has %d nodes\n", table.get(i).numberOfNodes());
			}
			else{
				System.out.println("has no nodes");
				
			}	
		}
	}
	
	/**
	 * Reads in the file with the given file name the parses all the words in that file
	 * and inserts the parsed words in the respective binary tree.
	 * @param filename - name of the file
	 */
	public void fileReader(String filename){
		try{
			Scanner scanner = new Scanner(new File(filename));
			while(scanner.hasNext()){
				String word = scanner.next();
				//this regular expression removes anything from the given word that is not A-Z or a-z
				word = word.replaceAll("[^a-zA-Z ]", "");
				if(word.equals("") == false){
					//make the character lowercase for easier comparison
					char leadingLetter = Character.toLowerCase(word.charAt(0));
					//convert the character to ascii
					int asciiValue = (int)leadingLetter;
					if(asciiValue < '{' && asciiValue > '`'){
						asciiValue -= 'a';
						Node data = new Node(word);
						table.get(asciiValue).insert(data);
					}
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
	 * takes in a binary tree and then prints out all of the
	 * entries in it that start with a given string
	 * @param retrieved - the binary tree to look through
	 * @param sample - the sample string to check if the data in the node starts with that sting
	 */
	public void findAll(BinarySearchTree<Node> retrieved, Node sample){
		System.out.printf("Printing the results of the nodes that STARTWITH: '%s'\n", sample.getWord());
		findAll(retrieved.getRoot(), sample);
	}

	/**
	 * Does the actual comparison to see if the current node is on that starts with the sample string.
	 * @param node - the node currently being checked
	 * @param sample - the string used to see if the node data starts with the string
	 */
	private void findAll(BinaryNode<Node> node, Node sample){
		//if the node is null this wont run because there is nothing to find
		if(node != null){
    		int comparison = sample.getWord().compareTo(((Node)node.getData()).getWord());
    		String wordInNode = ((Node)node.getData()).getWord();
    		//check if the node starts with the given sample string
    		//if it does check both children to see if they also start with the sample string
    		if(wordInNode.startsWith(sample.getWord())){
	    		findAll(node.getLeft(), sample);
	    		System.out.println(node.toString());
	    		findAll(node.getRight(), sample);
    		}
    		//else if the comparison is less than zero then go to the left child
    		else if(comparison < 0){
	    		if(wordInNode.startsWith(sample.getWord())){
	    			System.out.println(node.toString());
	    			findAll(node.getLeft(), sample);
	    		}
	    		else findAll(node.getLeft(), sample);
	    	}
    		//otherwise go to the right child and perform the same check
	    	else if(comparison > 0){
	    		if(wordInNode.startsWith(sample.getWord())){
	    			System.out.println(node.toString());
	    			findAll(node.getRight(), sample);
	    		}
	    		else findAll(node.getRight(), sample);
	    	}
    	}
	}
	
	/**
	 * Gets a binary tree out of the arraylist
	 * @param i - index of the tree in the arraylist
	 * @return the binary tree in the arraylist
	 */
	public BinarySearchTree<Node> retreiveHashedBSTat(int i) {
		return table.get(i);
	}
	
}
