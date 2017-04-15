#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

//This functions takes in the starting and ending index of an array to search.
//It also takes in a pointer to an integer array and the actual value you want
//to find.
void binarySearch(int beginingIndex, int endIndex, int * array, int valueToSearchFor){
  pid_t pid;
  int arraySplit = (endIndex + beginingIndex)/2;

  if(endIndex != beginingIndex){
    
    pid = fork();
    
    if(pid < 0) //Error Occurred
      printf("Fork Failed. :( \n");

    else if (pid == 0) //Child  Process
      binarySearch(beginingIndex, arraySplit, array, valueToSearchFor);

    else if (pid > 0) //Parent Process
      binarySearch(arraySplit + 1, endIndex, array, valueToSearchFor);
  }

  else if(array[beginingIndex] == valueToSearchFor)
    printf("Found %d at index %d!\n", valueToSearchFor, beginingIndex);
}

int main(int argc, char **argv){

  int data[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
  int data2[] = {5,1,6,9,1,3,9,1,5,7,5,2,2,6};
  int *arrayPtr = data;

  if (argc != 2) {
    printf("Usage: hw2_search <numberToSearchFor> \n");
    exit(1);
  }

  binarySearch(0, 13, arrayPtr, atoi(argv[1]));
}
