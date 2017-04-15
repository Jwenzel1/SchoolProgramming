/*
Name: Joseph Wenzel
Date: 11/4/2014
Class: Sadeghian Tu/Thur 11:30 to 12:45
Description: This program demos the basic capabilities of the
             c programming language. It taks in 3 command line args.
             These args are of the form <word> <word> <integer>.
             The program takes these arguments and demos the basic functions
	     of c with them.
*/
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

//Calculates the length of the given string. Does not include the 
//null termination in that count.
int myStrlen(char *s)
{
  int count = 0;
  while(*(s + count) != '\0'){ //end of string is denoted by null term char
    count++;}
  return count;
}

//Checks if two strings are the same string. This does not check
//case sensitivity so CaT is the same as cat.
bool sameString(char *s1, char * s2)
{
  int count = 0;
  int tempS1 = 0;
  int tempS2 = 0;
  //check if the strings are the same length. if not they cant possibly be the
  //same string.
  if(myStrlen(s1) != myStrlen(s2)){
    return false;
  }
  while(count <= myStrlen(s1)){
    tempS1 = ((int)*(s1 + count)); //stroe the ascii values in temp variables
    tempS2 = ((int)*(s2 + count));
    //make each ascii value lowercase to ignore case
    if(tempS1 < 97){
      tempS1 += 32;
    }
    if(tempS2 < 97){
      tempS2 += 32;
    }
    //comapre the values and if theyre not the same its not the same string
    if(tempS1 != tempS2){
      return false;
    }
    count++;
  }
  return true;
}

//Makes a copy of the given string and returns the pointer
//to the copied string. The copy will be exact including case.
char * makeCopy(char * s)
{
  int count = 0;
  char * copyArrayPtr;
  int length = myStrlen(s);
  //Allocate a new array of size length for the copy
  copyArrayPtr = malloc(length * sizeof(char));
  while(count <= length){
    *(copyArrayPtr + count) = *(s + count);
    count++;
  }
  return copyArrayPtr;
}

//Sorts the characters in a given string by ascii value. This does check case
//so uppercase characters will come before lowercase characters.
//This sort uses probably the most famous of them all, BUBBLESORT!!!!!!
void mySort(char * s)
{
  int i = 0;
  int length = myStrlen(s);
  int newi = 0;
  char temp;
  //Begin bubble sort
  while(length != 0){
    newi = 0;
    for(i = 1; i < length; i++){
      //if the first char is bigger than the second character
      if(((int)*(s+i-1)) > ((int)*(s+i))){
	//perform a swap
	temp = *(s+i-1);
	*(s+i-1) = *(s+i);
	*(s+i) = temp;
	newi = i; //keeps track of the last place we swapped.
      }
    }
    length = newi; //this makes bubble sort more efficient because were not
                   //looping to the end of the array every time just to where
                   //we swapped last
  }
}

//Takes in an integer x and it populates the array with doubles of x/2.
//This also takes in a size so it knows how long to make the array.
double * makeArray(int x, int size)
{
  int i = 0;
  double number  = (double)x;
  //USE SIZE TIMES 8 BECAUSE MALLOC ONLY ALLOCATES 1 BYTE
  double * doubleArray = malloc(size * sizeof(double));
  for(i = 0; i < size; i++){
    number /= 2.0;
    *(doubleArray + i) = number;
  }
  return doubleArray;
}

//Prints out the value and memory address of each element in the given array.
//Requires the size of te array or else it will seg fault
void showArray(double * ptr, int size)
{
  int i = 0;
  for(i = 0; i < size; i++){
    printf("The value is %f at address %p\n", *(ptr + i), (ptr + i)); 
  }
}

//takes in an integer and two bytes to swap. This function will swap those
//bytes in the integer then print out the number in decimal and hex.
void swapHex(int x, int byte1, int byte2)
{
  //create a char pointer to the integer so c wont automatically scale
  //4 bytes. a char pointer will scale 1 byte
  char * xPtr = (char *)&x;
  char temp = 'a';
  printf("%d in hex is %x\n", x, x);
  printf("swapping byte %d with byte %d\n", byte1, byte2);
  //store the byte in a temp variable and perform the swap
  temp = *(xPtr + byte1);
  *(xPtr + byte1) = *(xPtr + byte2);
  *(xPtr + byte2) = temp;
  printf("%d in hex is %x\n", x, x);
}

int main(int argc, char * argv[])
{

  char * copyOfArg1;
  char * copyOfArg2;
  double * myArr[2];

  printf("myStrlen Function\n");
  printf("The number of characters in %s is %d\n", argv[1], myStrlen(argv[1]));
  printf("The number of characters in %s is %d\n", argv[2], myStrlen(argv[2]));
  printf("--------------------------\n");
  printf("sameString Function\n");
  if (sameString(argv[1], argv[2]))
    printf("%s and %s are the same string\n", argv[1], argv[2]);
  else
    printf("%s and %s are not the same string\n", argv[1], argv[2]);
  printf("--------------------------\n");
  printf("makeCopy Function\n");
  copyOfArg1 = makeCopy(argv[1]);
  printf("argv[1] is %s and copy is %s\n", argv[1], copyOfArg1);
  copyOfArg2 = makeCopy(argv[2]);
  printf("argv[2] is %s and copy is %s\n", argv[2], copyOfArg2);
  printf("--------------------------\n");
  printf("mySort Function--Based on ASCII Codes\n");
  mySort(copyOfArg1);
  printf("argv[1] is %s and copy is %s\n", argv[1], copyOfArg1);
  mySort(copyOfArg2);
  printf("argv[2] is %s and copy is %s\n", argv[2], copyOfArg2);
  printf("--------------------------\n");
  printf("swapHex Function\n");
  swapHex(atoi(argv[3]),0,1);
  swapHex(atoi(argv[3]),0,2);
  swapHex(atoi(argv[3]),0,3);
  swapHex(atoi(argv[3]),1,2);
  swapHex(atoi(argv[3]),1,3);
  swapHex(atoi(argv[3]),2,3);
  printf("--------------------------\n");
  printf("makeArray Function\n");
  myArr[0] = makeArray(atoi(argv[3]), myStrlen(argv[1]));
  myArr[1] = makeArray(atoi(argv[3]), myStrlen(argv[2]));
  printf("--------------------------\n");
  printf("showArray Function for argv[1]\n");
  showArray(myArr[0], myStrlen(argv[1]));
  printf("--------------------------\n");
  printf("showArray Function for argv[2]\n");
  showArray(myArr[1], myStrlen(argv[2]));
  printf("--------------------------\n");
  printf("End of Demo\n");
  free(copyOfArg1);
  free(copyOfArg2);
  free(myArr[0]);
  free(myArr[1]);
  return 0;
}
