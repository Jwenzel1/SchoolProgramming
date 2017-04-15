package proj5;

/**
* @version 5/13/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 5
* Section 02
*/

public class MergeSort {

	/**
	 * Merges the given array depending on the value of T and the length of the array.
	 * If the length of the array is less than T it will use insertion sort
	 * otherwise it will use mergesort
	 * @param array
	 * @param T
	 * @return sorted array
	 */
	public static int[] merge(int[] array, int T){
		if(array.length < T){
			return insertionSort(array);
		}
		return merge(array);
	}

	/**
	 * Uses mergesort to sort the given array
	 * @param array
	 * @return sorted array
	 */
	 private static int[] merge(int[] array) {
        if (array.length==0 || array.length==1) {
            return array;
        }
        int[] leftArray = subarray(array,0,array.length/2);
        int[] rightArray = subarray(array,array.length/2,array.length);
        leftArray = merge(leftArray);
        rightArray = merge(rightArray);
        int[] newArray = new int[leftArray.length+rightArray.length];
        int leftIndex=0, rightIndex=0;
        for (int i=0;i<newArray.length;i++) {
            if (leftIndex>=leftArray.length) {
            	newArray[i]=rightArray[rightIndex];
                rightIndex++;
            } 
            else if (rightIndex>=rightArray.length) {
                newArray[i]=leftArray[leftIndex];
                leftIndex++;
            }
            else if (leftArray[leftIndex]<rightArray[rightIndex]) {
                newArray[i]=leftArray[leftIndex];
                leftIndex++;
            } 
            else {
                newArray[i]=rightArray[rightIndex];
                rightIndex++;
            }
        }
        return newArray;
}

	private static int[] subarray(int[] array,int b,int e) {
	        int[] arrayR = new int[e-b];
	        for (int i=b;i<e;i++) {
	                arrayR[i-b]=array[i];
	        }
	        return arrayR;
	}
	
	/**
	 * Uses insertion sort to sort the array
	 * @param array
	 * @return sorted array
	 */
	private static int[] insertionSort(int[] array){
		for(int i = 0; i < array.length; i++){
			int j = i;
			while(j >= 1 && array[j] < array[j-1]){
				int temp = array[j];
				array[j] = array[j-1];
				array[j-1] = temp;
				j--;
			}
		}
		return array;
	}
}
