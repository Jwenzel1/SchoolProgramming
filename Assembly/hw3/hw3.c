/*
Name: Joseph Wenzel
Email: jwenzel1@umbc.edu
Date Created: 10/12/2014
Class: Dr Sadeghian Tues/Thur 11:30 - 12:45
Description: This program will prompt a user for a positive odd integer.
The program will error check and ask the user again if they input the wrong
number. It will then ask if they want their output on the screen or in a 
file. If they choose a file they will be prompted for a file name.
Afterwards, the program will then proceed to calculate the summation of pi
up to and including the given integer. The results will be output in the way
the user chose. 
*/
#include <stdio.h>
#include <stdbool.h>
#define SUBTRACT 0  //This is used in the calculation to decide if i need to 
#define ADD 1       //add or subtract. Used in writeToScreen and writeToFile

/*function prototypes*/
bool checkNegative(int input);
bool checkOdd(int input);
void writeToScreen(int input);
void writeToFile(int input, char fileName[]);

int main(){
  char fileName[10];
  int input;
  int writeChoice;
  bool valid;
  do{
     printf("Enter a positive odd integer: ");
     scanf("%d", &input); //take the user input and assign it to input
     valid = checkNegative(input); //check if the number is negative
     if(valid == true){ //if it isnt negative then check if it is odd
       valid = checkOdd(input);
     }
  }
  while(valid == false); //prompt for input again if the input is either 
                         //negative or even

  do{
    printf("\nEnter 1 to write to a file\n"); //get whether to print to file
    printf("Enter 2 to write to the screen: "); //or print to screen
    scanf("%d", &writeChoice); //assign the choice to the variable writeChoice
    if(writeChoice == 1 || writeChoice == 2){ //Check if a valid choice is
                                              //enetered
      valid = true;
    }
    else{
      printf("You did not enter 1 or 2\n");//Tell the user they put in a wrong input
      valid = false;
    }
  }
  while(valid == false); //repeat if the input is invalid

  if(writeChoice == 2){
    writeToScreen(input); //do the calculation and output it to the screen
  }
  else{
    printf("Enter the name of a file (9 char or less): ");
    scanf("%9s", &fileName); //the filename specified by the user
    writeToFile(input, fileName);//do the calculation and write it to a file
  }

  return 0; //return 0 because why the heck not
}

/*
This function takes in an integer and returns true if it is positive
and false if it is negative
 */
bool checkNegative(int input){
  if(input < 1){ //if the input is negative return false
    return false;
  }
  else{ //otherwise its positive so return true
    return true;
  }
}

/*
This function takes in any integer and returns false if it is even and 
true if it is odd
*/
bool checkOdd(int input){
  if(input % 2 == 0){ //mod the input by 2. if the remainder is 0 its even
    return false; //return false because we cant use evens
  }
  else{ //return true because if it isnt even its odd
    return true;
  }
}

/*
This function takes the given input, applies the pi summation as many times
as the input tells it to and prints the results out to the screen
*/
void writeToScreen(int input){
  int i; //loop counting variable
  double total; //total amount added together
  int operator; // This uses subtract and add
  total = 0.0;
  operator = SUBTRACT; //SUBTRACT is equal to 0
  printf("OK, I will calculate ...\n");
  printf(" 4 X ( ");
  for(i = 1; i <= input; i += 2){
    if(i == 1){ //base case if i is one
      printf("1 ");
      total = 1.0; //initialize the total to 1.0 so the double math works
    }
    else{
      switch(operator){ //if i is not one then begin the switch statement
      case SUBTRACT: //switch on 0 
	printf("- 1/%d ",i);
	total = total - (1.0/i); //subtract the fraction from the total
	operator = ADD; // change the swith variable to 1 which means add
	if(i % 10 == 9 && i != input){ //this is for formatting
	  printf("\n         ");
	}
	break;
      case ADD:
	printf("+ 1/%d ", i); //do the same in this case except add instead of subtracting
	total = total + (1.0/i);
	operator = SUBTRACT;
	if(i % 10 == 9 && i != input){
          printf("\n         ");
        }
	break;
      }
    }
  }
  total = 4*total; //do 4 times the total because thats part of the equation
  printf(")\n");
  printf(" ...which is equal to %f\n",total);
} 

/*
This function takes in an integer and a filename and performs the pi summation.
It will then write the output to a file given by the user
*/      
void writeToFile(int input, char fileName[]){
  /*
THIS FUNCTION IS LITERALLY THE EXACT SAME AS THE FUNCTION CALLED writeToScreen
THAT IS PREVIOUS TO THIS FUNCTION. I AM NOT GOING TO COMMENT IT AS A RESULT. 
THE ONLY DIFFERENCE BETWEEN THIS FUNCTION AND WRITETOSCREEN IS THAT ALL THE 
PRINT STATEMENTS AND FPRINTF INSTEAD OF PRINTF
   */
  FILE *f = fopen(fileName, "w"); //open a file and call it f
  int i;
  double total;
  int operator; // This uses subtract and add
  total = 0.0;
  operator = SUBTRACT;
  fprintf(f, "OK, I will calculate ...\n");
  fprintf(f, " 4 X ( ");
  for(i = 1; i <= input; i += 2){
    if(i == 1){
      fprintf(f, "1 ");
      total = 1.0;
    }
    else{
      switch(operator){
      case SUBTRACT:
        fprintf(f, "- 1/%d ",i);
        total = total - (1.0/i);
        operator = ADD;
        if(i % 10 == 9 && i != input){
	  fprintf(f, "\n         ");
        }
        break;
      case ADD:
        fprintf(f, "+ 1/%d ", i);
        total = total + (1.0/i);
        operator = SUBTRACT;
        if(i % 10 == 9 && i != input){
          fprintf(f, "\n         ");
        }
        break;
      }
    }
  }
  total = 4*total;
  fprintf(f, ")\n");
  fprintf(f, " ...which is equal to %f\n",total);
  fclose(f);//close the file
}

