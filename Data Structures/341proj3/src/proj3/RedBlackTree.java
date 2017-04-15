package proj3;

/**
* @version 4/8/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 3
* Section 02
*/

public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {
	
    private static final int BLACK = 1;
    private static final int RED = 0;
    
    private Node<AnyType> root = null;

    
    /**
     * adds and item to the tree
     * @param x
     */
    public void add(AnyType x) {
    	Node<AnyType> newNode = new Node<AnyType>();
    	newNode.data = x;
    	newNode.left = null;
    	newNode.right = null;
    	if(root == null){
    		root = newNode;
    	}
    	else{
    		root.addNode(newNode);
    	}
        restructure(newNode);
    }
    
    /**
     * Bootstrapper for the numberOfNodes function
     * Counts how many nodes are in the tree
     * @return the number of nodes in the tree
     */
    public int numberOfNodes(){
    	return numberOfNodes(root);
    }
    
    /**
     * Counts the number of nodes in the tree given the root of the tree
     * @param node - the root
     * @return the number of nodes in the tree
     */
    private int numberOfNodes(Node<AnyType> node){
    	int numNodes = 1;
    	
    	//if theres no nodes in the tree return zero
    	if(node == null){
    		return 0;
    	}
    	//sum up all the nodes to the left
    	if(node.left != null){
    		numNodes += numberOfNodes(node.left);
    	}
    	//sum up all the nodes to the right
    	if(node.right != null){
    		numNodes += numberOfNodes(node.right);
    	}
    	return numNodes;
    }
    
    /**
     * prints out every item in the tree
     */
  	public void printTree(){
  		if(root == null){
  			System.out.println("Empty Tree");
  		}
  		else printTree(root);
  	}
  	
  	/**
  	 * prints out the root
  	 */
  	public void printRoot(){
  		if(root == null){
  			System.out.println("EmptyTree");
  		}
  		else printRoot(root);
  	}
  
  	private void printTree(Node<AnyType> t){
  		if(root == null){
  			System.out.println("Empty Tree");
  		}
  		else if(t != null){
  			printTree(t.left);
  			System.out.println(t.data);
  			printTree(t.right);
  		}
  	}
  	
  	/**
  	 * prints out the root of the tree
  	 * @param t
  	 */
  	private void printRoot(Node<AnyType> t){
  		if(t != null){
  			System.out.print(t.data);
  		}
  	}
  	
    private class Node<AnyType> {
        public AnyType data;
        public int color;
        public Node<AnyType> left;
        public Node<AnyType> right;
        public Node<AnyType> parent;
        
        /**
         * method for adding a node to the tree
         * @param newNode
         */
        public void addNode(Node<AnyType> newNode)
        {  
           int comp = ((Comparable<AnyType>) newNode.data).compareTo(data);
           if (comp < 0)
           {  
              if (left == null) 
              {
                 left = newNode;
                 left.parent = this;
              }
              else { left.addNode(newNode); }
           }
           else if (comp > 0)
           {  
              if (right == null) 
              {
                 right = newNode;
                 right.parent = this;
              }
              else { right.addNode(newNode); }
           }
        }

        /**
         * sets the left child of the current node
         * @param child
         */
        public void setLeftChild(Node<AnyType> child){
        	left = child;
        	if(child != null){
        		child.parent = this;
        	}
        }
        
        /**
         * Sets the right child of the current node
         * @param child
         */
        public void setRightChild(Node<AnyType> child){
        	right = child;
        	if(child != null){
        		child.parent = this;
        	}
        }
        
        /**
         * replaces the current node with this one
         * @param replacement
         */
        public void replaceNode(Node<AnyType> replacement){
        	if(parent == null){
        		return;
        	}
        	if(this == parent.left){
        		parent.setLeftChild(replacement);
        	}
        	else{
        		parent.setRightChild(replacement);
        	}
        }

        
    }
    
    /**
     * fixes the tree if adding a node has caused a double red violation
     * @param newNode
     */
    private void restructure(Node<AnyType> newNode){
    	if(newNode.parent == null){
    		newNode.color = BLACK;
    	}
    	else{
    		newNode.color = RED;
    		if(newNode.parent.color == RED){
    			fixReds(newNode);
    		}
    	}
    }
    
    /**
    Fixes when a child and parent are both red
    @param child with red parent
    */
    private void fixReds(Node<AnyType> child)
    {
    	Node<AnyType> parent = child.parent;      
    	Node<AnyType> grandParent = parent.parent;
    	if (grandParent == null) { 
    		parent.color = BLACK; return; 
    	}
    	Node<AnyType> node1;
    	Node<AnyType> node2;
    	Node<AnyType> node3;
    	Node<AnyType> target1;
    	Node<AnyType> target2;
    	Node<AnyType> target3;
    	Node<AnyType> target4;
    	
    	if (parent == grandParent.left)
    	{
    		node3 = grandParent; target4 = grandParent.right;
    		if (child == parent.left){
    			node1 = child;
    			node2 = parent;
    			target1 = child.left; 
    			target2 = child.right; 
    			target3 = parent.right;
    		}
    		else
    		{
    			node1 = parent; 
    			node2 = child;
    			target1 = parent.left; 
    			target2 = child.left; 
    			target3 = child.right; 
    		}
    	}
    	else
    	{
    		node1 = grandParent; 
    		target1 = grandParent.left;
    		if (child == parent.left)
    		{
    			node2 = child; 
    			node3 = parent;
    			target2 = child.left; 
    			target3 = child.right; 
    			target4 = parent.right;
    		}
    		else
    		{
    			node2 = parent; 
    			node3 = child;
    			target2 = parent.left; 
    			target3 = child.left; 
    			target4 = child.right; 
    		}         
    	}
    
    	if (grandParent == root)
    	{
    		root = node2;
    		node2.parent = null;
    	}
    	else
    	{
    		grandParent.replaceNode(node2);
    	}
    
    	node1.setLeftChild(target1);
    	node1.setRightChild(target2);
    	node2.setLeftChild(node1);
    	node2.setRightChild(node3);
    	node3.setLeftChild(target3);
    	node3.setRightChild(target4);
    	node2.color = grandParent.color - 1; 
    	node1.color = BLACK;
    	node3.color = BLACK;

    	if (node2 == root)
    	{
    		root.color = BLACK;
    	}
    	else if (node2.color == RED && node2.parent.color == RED)
    	{
    		fixReds(node2);
    	}
    }

    /**
     * retrieves an item if it is contained in the tree
     * @param x
     * @return
     */
	public AnyType retreiveIfItContains(AnyType x) {
		try{
			AnyType found = findAnyType(x);
			return found;
		}
		catch(Exception e){
			return null;
		}
	}   
	
	/**
	 * finds a node in the tree
	 * @param x
	 * @return
	 */
	public AnyType findAnyType(AnyType x){
		return findAnyType(x, root);
	}
	
	private AnyType findAnyType(AnyType x, Node<AnyType> t){
		int compareResults = x.compareTo(t.data);
        if(compareResults < 0){
        	if(t.left != null){
        		return findAnyType(x, t.left);
        	}
        	else{
        		return null;
        	}
        }
        else if(compareResults > 0){
        	if(t.right != null){
        		return findAnyType(x, t.right);
        	}
        	else{
        		return null;
        	}
        }
        else{
        	return t.data;
        }
	}
}