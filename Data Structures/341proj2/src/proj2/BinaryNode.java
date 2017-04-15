package proj2;

/**
* @version 3/21/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 2
* Section 02
*/

public class BinaryNode<AnyType> {
	
	private AnyType data;
	private BinaryNode<AnyType> left; 
	private BinaryNode<AnyType> right;
	
	/**
	 * Creates a BinaryNode that by default makes the left and 
	 * right child null
	 * @param data to be held by the node
	 */
	public BinaryNode(AnyType data){
		this(data, null, null);
	}
	
	/**
	 * Creates a BinaryNode that has its left and right child defined
	 * @param data to be held by the node
	 * @param leftChild of the node
	 * @param rightChild of the node
	 */
	public BinaryNode(AnyType data, BinaryNode<AnyType> leftChild, BinaryNode<AnyType> rightChild){
		this.data = data;
		this.left = leftChild;
		this.right = rightChild;
	}

	/**
	 * Gets the data out of the BinaryNode
	 * @return the data being held by the node
	 */
	public AnyType getData() {
		return data;
	}

	/**
	 * Sets the data in the node
	 * @param new data to replace the old data in the node
	 */
	public void setData(AnyType data) {
		this.data = data;
	}

	/**
	 * Gets the left child of the node
	 * @return the left child of the node
	 */
	public BinaryNode<AnyType> getLeft() {
		return left;
	}

	/**
	 * Sets the left child of the node
	 * @param new left child to replace the old left child
	 */
	public void setLeft(BinaryNode<AnyType> left) {
		this.left = left;
	}

	/**
	 * Gets the right child of the node
	 * @return the right child of the node
	 */
	public BinaryNode<AnyType> getRight() {
		return right;
	}

	/**
	 * Sets the right child of the node
	 * @param new right child to replace the old right child
	 */
	public void setRight(BinaryNode<AnyType> right) {
		this.right = right;
	}
	
	/**
	 * Gives the string representation of the data in the binary node
	 * @return the string representation of the data in the node
	 */
	public String toString(){
		return data.toString();
	}
}
