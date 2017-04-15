/*
Name: Joseph Wenzel
E-mail: jwenzel1@umbc.edu
Date: 11/7/2014
Class: Sadeghian CMSC 313 Tu/Thur 11:30 - 12:45
Description: This program will create what we are calling a "vector". We will
             add "Fractions" to it and it will grow the vector to accomodate
	     more elements as need be. the initial capacity is 2 and every
	     time the vector grows the capacity increases by two. We can
	     also sort the vector which we do in the demo.
*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>

typedef struct fraction{
  int num;   //numerator
  int denom; //denominator
} FRACTION;

typedef struct myVector{
  FRACTION * ptr;
  int numAllocated;  //number of elements actually allocated
  int numUsed;  //the logical length
} MYVECTOR;

//Frees the vector in the given MYVECTOR
void disposeVector(MYVECTOR * v){
  free(v -> ptr);
}

//Creates a vector with an initial capacity of 2
void makeVector(MYVECTOR * v){
  //The arrow notation (->) is shorthand for (*v).numUsed = 2 for example
  v -> numAllocated = 2;
  v -> numUsed = 0;
  v -> ptr = malloc(2*sizeof(FRACTION));
}

//Prints out the given fraction
void printFraction(FRACTION f){
  printf("%d/%d, ", f.num, f.denom);
}

//Returns the current number of used elements in the vector
int size(MYVECTOR * v){
  return v -> numUsed;
}

//Returns the maximum capacity of the vector
int capacity(MYVECTOR * v){
  return v -> numAllocated;
}

/*Allocates 2 more spaces to add new things to the vector.
It accomplishes this by using malloc to make a bigger array, then
copying the array over to the new one then freeing the old one 
and setting the new array to the array in the vector struct.
*/
void grow(MYVECTOR * v){
  int i;
  FRACTION * temp = malloc(((*v).numAllocated+2)*sizeof(FRACTION));
  for(i = 0; i < (v -> numAllocated); i++){
    temp[i] = (v -> ptr)[i]; //copy over the elements in the old array
  }
  disposeVector(v); //free the old vector
  v -> ptr = temp; //set the new vector as the one in the struct
  v -> numAllocated += 2; //add two to the allocated number
}

//puts the given fraction on the end of the given vector
void append(MYVECTOR * v, FRACTION f){
  if(size(v) == capacity(v)){
    grow(v); //we may need to grow the array when adding elements
  }
  (v -> ptr)[v -> numUsed] = f;
  v -> numUsed++;
}

//Print out all the values of the given vector
void displayVector(MYVECTOR * v){
  int i;
  for(i = 0; i < v -> numUsed; i++){
    printFraction(v -> ptr[i]); //print out the fraction at i
    if((i+1) % 5 == 0){
      printf("\n"); //print a new line after 5 fractions have been printed out
    }
  }
  printf("\n");
}

//Tests if two given fractions are larger or not. If theyre larger this
//returns true. If theyre less than or equal each other then return false 
bool isLarger(FRACTION f1, FRACTION f2){
  //turn the values into doubles because they're fractions
  double value1 = (double)(f1.num) / (double)(f1.denom);
  double value2 = (double)(f2.num) / (double)(f2.denom);
  if(value1 > value2){
    return true;
  }
  return false;
}

//Insert the given fraction into the given index of the given vector.
void insertAt(MYVECTOR * v, int place, FRACTION f){
  int i;
  if(place < 0 || place >= size(v)){ //Check if we can insert into bounds
    printf("Error, no element at %d\n", place);
    return;
  }
  else if(size(v) == capacity(v)){
    grow(v); //Grow the vector if the insert makes it go out of bounds
  }
  for(i = size(v)-1; i >= place; i--){ //Use size -1 because the size starst at 1 and the index starts at zero
    (v -> ptr)[i+1] = (v -> ptr)[i]; //Perform the swap
  }
  (v -> ptr)[place] = f;
  v -> numUsed++;
}

//Creates and returns a copy of a given vector
MYVECTOR * copyVector(MYVECTOR * v, MYVECTOR * copyV){
  int i;
  //Initilaize a new vector
  copyV -> numAllocated = capacity(v);
  copyV -> numUsed = size(v);
  copyV -> ptr = malloc(capacity(v)*sizeof(FRACTION));
  for(i = 0; i < size(v); i++){
    (copyV -> ptr)[i] = ((v -> ptr)[i]); //copy the elements of the old vector to the new vector
  }
}

//Removes an element in the given vector by overwriting it
void removeAt(MYVECTOR *v, int place){
  int i;
  if(place < 0 || place >= size(v)){
    printf("Error, no element at %d\n", place); //check if the insert is within the bounds of the vector
    return;
  }
  v -> numUsed--;
  for(i = place; i < size(v)-1; i++){
    (v -> ptr)[i] = (v -> ptr)[i+1]; //swap the two elements 
  }
}

//sorts the vector using bubblesort cus bubblesort is awesome
void sortVector(MYVECTOR *v, bool (*compFunc)(FRACTION, FRACTION)){
  int i;
  int newn;
  int n = size(v);
  FRACTION temp;
  while(n != 0){ //while there are still elements
    newn = 0;
    for(i = 1; i <= n-1; i++){
      if((*compFunc)((v -> ptr)[i-1], (v -> ptr)[i])){
	temp = (v -> ptr)[i-1]; //save what to swap in a temp
	(v -> ptr)[i-1] = (v -> ptr)[i]; //swap two eleemtns
	(v -> ptr)[i] = temp; //make the other element temp
	newn = i;
      }
    }
    n = newn;
  }
}

int main()
{

  FRACTION fracArray [10] ={ {1,2},{2,3},{3,4},{7,8},{22,7},
                             {1,3},{1,4},{1,8},{2,5},{3,5}};

  int n, d;
  printf("Enter n/d: ");
  scanf("%d/%d", &n, &d);

  FRACTION f1= {n,d};
  FRACTION f2 = {d,n};


  int i =0;
  MYVECTOR myV;
  MYVECTOR copyMyV;


  makeVector(&myV);
  for (i =0; i<10; i++)
    {
      append(&myV, fracArray[i]);
      printf("%d/%d appended to the vector.  ", fracArray[i].num,
	     fracArray[i].denom);
      printf("Size is: %d, ", size(&myV));
      printf("Cap is: %d\n", capacity(&myV));
    }

  printf("----------------------\n");
  printf("Here are the values: \n");
  displayVector(&myV);
  
  printf("----------------------\n");
  sortVector(&myV, &isLarger);
  printf("Here are the values after sort: \n");
  displayVector(&myV);
  
  printf("----------------------\n");
  printf("Inserting %d/%d at index %d\n", n,d,n % 10);
  insertAt(&myV, n % 10 , f1);
  printf("After inserts, values are: \n");
  displayVector(&myV);
  printf("Size is: %d, and Cap is: %d\n ", size(&myV), capacity(&myV));


  printf("----------------------\n");
  printf("Inserting %d/%d at index %d\n", d,n,d % 10);
  insertAt(&myV, d % 10 , f2);
  printf("After inserts, values are: \n");
  displayVector(&myV);
  printf("Size is: %d, and Cap is: %d\n ", size(&myV), capacity(&myV));

  printf("----------------------\n");
  printf("Inserting %d/%d at index %d\n", n , d,-(n % 10));
  insertAt(&myV, -(n % 10), f1);


  printf("----------------------\n");
  printf("Inserting %d/%d at index %d\n", d,n, size(&myV) + (d % 10));
  insertAt(&myV, size(&myV) + (d % 10), f2);

  printf("----------------------\n");
  printf("Call copyVector\n");
  copyVector(&myV, &copyMyV);

  printf("----------------------\n");
  printf("Here is the original: \n");
  displayVector(&myV);

  printf("----------------------\n");
  printf("Here is the copy: \n");
  displayVector(&copyMyV);

  printf("----------------------\n");
  printf("Adding many new values to the copy\n");

  for (i =0; i<10; i++)
    {
      append(&copyMyV, fracArray[i]);
      printf("%d/%d appended to the vector.  ", fracArray[i].num,
             fracArray[i].denom);
      printf("Size is: %d, ", size(&copyMyV));
      printf("Cap is: %d\n", capacity(&copyMyV));
    }

  printf("----------------------\n");
  printf("Here is the copy after adding 10 new fractions: \n");
  displayVector(&copyMyV);
  
  printf("----------------------\n");
  sortVector(&copyMyV, &isLarger);
  printf("Here is the copy after sorting: \n");
  displayVector(&copyMyV);

  printf("----------------------\n");
  printf("Here is the original vector: \n");
  displayVector(&myV);
 
  printf("----------------------\n");
  printf("Removing value at index %d.", n % 10);
  removeAt(&copyMyV, n % 10);
  printf("Size is: %d, ", size(&copyMyV));
  printf("Cap is: %d\n", capacity(&copyMyV));
  printf("Here is the copy after removing the value at %d: \n", n % 10);
  displayVector(&copyMyV);

  printf("----------------------\n");
  printf("Removing value at index %d.", d % 10);
  removeAt(&copyMyV, d % 10);
  printf("Size is: %d, ", size(&copyMyV));
  printf("Cap is: %d\n", capacity(&copyMyV));
  printf("Here is the copy after removing the value at %d: \n", d % 10);
  displayVector(&copyMyV);
 
  printf("----------------------\n");
  printf("Calling disposeVector\n");
  
  disposeVector(&myV);
  disposeVector(&copyMyV);
  
  return 0;
}
