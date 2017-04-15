package proj2;

/**
* @version 3/21/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 2
* Section 02
*/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{

	private BinaryNode<AnyType> root = null;

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x ) { 
    	root = insert( x, root ); 
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.getData() );
            
        if( compareResult < 0 ) // value in CURRENT root 't' > new value
            t.setLeft(insert( x, t.getLeft() ));
        else if( compareResult > 0 ) // value in CURRENT root 't' < new value
            t.setRight(insert( x, t.getRight() ));
        else
        	((Node)t.getData()).incrementFrequency();
        return t;
    }
    
    /**
     * Bootstrapper for the print tree function.
     * Prints out every element in the given tree based on the 
     * toString() of the elements in the tree
     */
    public void printTree(){
    	printTree(root);
    }
    
    /**
     * Prints out every element in the tree in infix order
     * @param root - the root of the current subtree in the tree
     */
    private void printTree(BinaryNode<AnyType> root){
    	if(root.getLeft() != null){
    		printTree(root.getLeft());
    	}
    	
    	System.out.println(root.getData());
		
    	if(root.getRight() != null){
    		printTree(root.getRight());
    	}

    }

    /**
     * Bootstrapper for the printRoot function.
     * Prints out the root of the tree
     */
    public void printRoot(){
    	printRoot(root);
    }
    
    /**
     * Prints out the root of the tree
     * @param t - the root of the tree
     */
    private void printRoot(BinaryNode<AnyType> t){
    	System.out.print(root.toString());
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
    private int numberOfNodes(BinaryNode<AnyType> node){
    	int numNodes = 1;
    	
    	//if theres no nodes in the tree return zero
    	if(node == null){
    		return 0;
    	}
    	//sum up all the nodes to the left
    	if(node.getLeft() != null){
    		numNodes += numberOfNodes(node.getLeft());
    	}
    	//sum up all the nodes to the right
    	if(node.getRight() != null){
    		numNodes += numberOfNodes(node.getRight());
    	}
    	return numNodes;
    }
    
    /**
     * Getter for the root of the tree
     * @return the root of the tree
     */
    public BinaryNode<AnyType> getRoot(){
    	return root;
    }
    
    /**
     * Bootstrapper for the findCommonAncestor function.
     * finds the common ancestor of any two given things in the tree.
     * @param x - the first thing to find the common ancestor of
     * @param y - the second thing to find the common ancestor of
     */
    public void findCommonAncestor(AnyType x, AnyType y){
    	System.out.printf("The results of the common ancestor is:\n%s and %s\n is: %s\n",x,y,findCommonAncestor(root, x, y));
    }
    
    /**
     * Finds the least common ancestor of the two things given in the tree
     * @param node - the current node the function is at
     * @param x - the first thing to compare to the current node
     * @param y - the second thing to comapre to
     * @return the node of the common ancestor
     */
    private BinaryNode<AnyType> findCommonAncestor(BinaryNode<AnyType> node, AnyType x, AnyType y){
    	//if x and y are both less than the current node then go to the left child
    	if(x.compareTo(node.getData()) < 0 && y.compareTo(node.getData()) < 0){
    		return findCommonAncestor(node.getLeft(), x, y);
    	}
    	//else if x and y are both greater than the current node go to the right child
    	else if(x.compareTo(node.getData()) > 0 && y.compareTo(node.getData()) > 0){
    		return findCommonAncestor(node.getRight(), x, y);
    	}
    	//otherwise x is less than the current node and y is greater than the current node, or vice versa, 
    	//so the common ancestor has been found
    	return node;
    	
    }
    public static void main( String [] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
        t.insert(8);
        t.insert(3);
        t.insert(10);
        t.insert(1);
        t.insert(6);
        t.insert(14);
        t.insert(4);
        t.insert(7);
        t.insert(13);
        t.printTree();
    }
}