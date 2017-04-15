package lab5;
import java.util.Scanner;

/**
 *	BoundedArray implements an array of integers with values 
 *		between a specified minimum and maximum range.
 *
 *	Class Invariants:
 * 	'minVal' must be less than or equal to 'maxVal'
 *	All values of the array must be in the range
 *		'minVal' and 'maxVal' INCLUSIVE
 *	@author
 *	@version Oct 7th, 2012
 *	@project CMSC 202 - Fall 2012 - Lab #5
 *	@section #
 *
 */
public class BoundedArray {
	private int array[];
	private int minVal;
	private int maxVal;
	
	/**
	 * Assigns the instance variable array reference to 
	 * point to the incoming array reference. Also assigns the 
	 * range values.
	 * Precondition: The input array to the constructor cannot be null.
	 * Precondition: 'minVal' must be less than or equal to 'maxVal'
	 * Precondition: All values of the array must be in the range
	 *               'minVal' and 'maxVal' INCLUSIVE
	 * @param array The input array to be searched on.
	 * @param minVal The minimum allowed value in the array.
	 * @param maxVal The maximum allowed value in the array.
	 */
	public BoundedArray(int[] array, int minVal, int maxVal) {
		
		//Precondition #1 The input array to the constructor cannot be null.
		if(array==null){
			throw new NullPointerException("Constructor precondition " +
					"not met: Input array cannot be null");
		}
		
		//Precondition #2 'minVal' must be less than or equal to 'maxVal'
		if(maxVal<minVal){
			throw new RuntimeException("Constructor precondition " +
					"not met: 'minVal' IS NOT less than or equal to 'maxVal'.");
		}
		
		//
		// Precondition #3 All values of the array must be in the range
		//                  'minVal' and 'maxVal' INCLUSIVE

		// INSERT CODE HERE
		for(int i = 0; i < array.length; i++){
			if(array[i] > maxVal || array[i] < minVal){
				throw new RuntimeException("Constructor precondition " +
				"not met: there is an element in the array greater than or less than the provided max or min");
			}
		
		
		// Initialize the "member instance variables"
		this.array=array;
		this.minVal=minVal;
		this.maxVal=maxVal;}
	}
	
	
	/**
	 * Method to determine if element 'x' resides within the array.
	 * Precondition: value of 'x' must be in range 'minVal' and 'maxVal' INCLUSIVE
	 * Postcondition: returns true:   if the array contains 'x'
	 *                returns false:  otherwise
	 * @param x The number to be searched
	 * @return  True if the array contains 'x', false otherwise
	 */
	public boolean contains(int x){
	
		// Check for the 'contains()' preconditions
		// INSERT CODE HERE
		if(x < minVal || x > maxVal){
			throw new RuntimeException("Contains precondition " +
			"not met:  the number put in is greater than or less than the boundaries specified.");
		
		}
			
		boolean found=false;
		int i=0;
		while(found==false){
			if(array[i]==x && i < array.length){
				// We found the element at position i
				return true;
			}
			i++;
		}
		// We did not find the element
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);		
		
		System.out.println("Welcome to the BoundedArray Unit Tester");
		System.out.println("What would you like to test?");
		System.out.println("Case 1:  Constructor array=null");
		System.out.println("Case 2:  Constructor minVal>maxVal");
		System.out.println("Case 3:  Constructor array elements < minVal");
		System.out.println("Case 4:  Constructor array elements > maxVal");
		System.out.println("Case 5:  Constructor General Case (happy path #1)");
		System.out.println("Case 6:  Constructor Edge Case (happy path #2)");
		System.out.println("Case 7:   contains(x), where x < minVal");
		System.out.println("Case 8:   contains(x), where x > maxVal");
		System.out.println("Case 9:   contains(x), where 'x' is found");
		System.out.println("Case 10:  contains(x), where 'x' is not found");
		System.out.println("Case 11:  contains(x), where 'x' equal to 'minVal'");
		System.out.println("Case 12:  contains(x), where x equal to 'maxVal'");
		System.out.print("Please type which test case to run: ");
		int button = input.nextInt();

		System.out.println("--------------------");
		System.out.println("- Case "+button+" Result:");
		System.out.println("--------------------");
		
		/*
		 *Enter test case to check class invariant #2 
		 */
		switch (button)
		{
			case 1:
			{
				// 1:  Constructor array=null
				System.out.println("Passing null array. Expecting error statement.");
				int[] testArray=null;
				BoundedArray b=new BoundedArray(testArray, 0, 100);
			}
			break;
			case 2:
			{
				// 2:  Constructor minVal>maxVal
				System.out.println("Passing minVal>maxVal. Expecting error statement.");
				int[] testArray={1,2,3,4,5};
				BoundedArray b=new BoundedArray(testArray, 10, 9);
			}
			break;
			case 3:
			{
				// 3:  Constructor array elements < minVal
				System.out.println("Checking to see if array elements is less than minVal");
				int[] testArray={1,2,3,4,5};
				BoundedArray b=new BoundedArray(testArray, 2, 5);
			}
			break;
			case 4:
			{
				// 4:  Constructor array elements > maxVal
				System.out.println("Checking to see if array elements is greater than maxVal");
				int[] testArray={1,2,3,4,5};
				BoundedArray b=new BoundedArray(testArray, 1, 4);
				
			}
			break;
			case 5:
			{
				// 5: Constructor General Case (happy path #1)
				System.out.println("General test case for constructor (happy path)");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -9, 10);
				System.out.println("\tBoundedArray object successfully initialized");
			}
			break;
			case 6:
			{
				// 6: Constructor Edge Case (happy path #2)
				System.out.println("Edge case for constructor. Array values " +
					"exactly on range limits");
				int[] testArray={-5,-9,0,-1,3,6,7,-2,2};
				BoundedArray b=new BoundedArray(testArray,-9,7);
				System.out.println("\tBoundedArray object successfully initialized");
			}
			break;
			case 7:
			{
				// 7:  contains(x), where x < minVal
				// INSERT CODE HERE
				System.out.println("contains(x), where x < minVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -9, 10);
				for(int i = 0; i<testArray.length; i++){
					if(b.contains(-900) == true){
						System.out.println("found a place where x was less than the minValue");
						break;
					}
					
				}
				
			}
			break;
			case 8:
			{
				// 8:  contains(x), where x > maxVal
				// INSERT CODE HERE
				System.out.println("contains(x), where x > minVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -9, 10);
				for(int i = 0; i<testArray.length; i++){
					if(b.contains(15) == true){
						System.out.println("found a place where x was less than the minValue");
						break;
					}
					
				}
			}
			break;
			case 9:
			{
				// 9:  contains(x), where 'x' is found
				// INSERT CODE HERE
				System.out.println("contains(x), where x < minVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -9, 10);
				if(b.contains(0) == true){
					System.out.println("found x");
					break;
					
				}
			}
			break;
			case 10:
			{
				// 10:  contains(x), where 'x' is not found
				// INSERT CODE HERE
				System.out.println("contains(x), where x not found minVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -9, 10);
				if(b.contains(1) == false){
					System.out.println("x was not found.");
					break;
					
				}
			}
			break;
			case 11:
			{
				// 11:  contains(x), where 'x' equal to 'minVal'
				// INSERT CODE HERE
				System.out.println("contains(x), where x = minVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -8, 10);{
				if(b.contains(-8) == true){
					System.out.println("found a place where x was equal to min value.");
					break;
					
				}
			}
			}
			break;
			case 12:
			{
				// 12:  contains(x), where x equal to 'maxVal'
				// INSERT CODE HERE
				System.out.println("contains(x), where x = maxVal");
				int[] testArray={-5,-6,0,-1,3,6,7,-2,2,3,4,7,-8};
				BoundedArray b=new BoundedArray(testArray, -8, 7);
				if(b.contains(7) == true){
					System.out.println("found where x was equal to the maxValue");
					break;
				}
			}
			break;
			default:
				System.out.println("case #"+button+" does not exist"+
					", please type a better number.");
			break;
		}
	}
}


