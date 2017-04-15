#File: hw5.py
#Author: Joseph Wenzel
#Date: 3/6/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description: This program will convert a decimal number to binary or
#hexadecimal and it will also convert an 8 bit binary number back to a decimal
#number

import sys

HEXBASE = 16
DECBASE = 10
BINBASE = 2
MAXVALUE = 1
MINVALUE = 0
MAXBINARYSTRING = 7
MAXDECIMALNUMBER = 255
MINDECIMALNUMBER = 0
MAXBINARYBITS = 8

def printGreeting():
    
    print("\nThis program will convert values.")

def printMenu():
    print ("[A] - Convert from Decimal to Binary")
    print ("[B] - Convert from Decimal to Hexadecimal")
    print ("[C] - Convert from Binary to Decimal")
    print ("[D] - Quit\n")

def decToHex(getUserNumber):
#Function name: decToHex() converts a decimal number into a hexadecimal number
#input: the decimal number (up to 255)  the user wants to convert
#output: the number converted into hexadecimal
    
    #This list is the begining of the hexadecimal number the two values go here
    hexString = [0,0]

    #This code calculates the first number for the hex number
    hexString[MINVALUE] = getUserNumber / HEXBASE

    #This code calculates the second number
    hexString[MAXVALUE] = getUserNumber % HEXBASE

    #This embedded if statement is checking to see if the first value needs 
    #to be changed into a letter if it does it does it using ascii values
    if hexString[MINVALUE] != MINVALUE:
        if hexString[MINVALUE] < DECBASE:
            print hexString[MINVALUE],
        else:
            letter = (hexString[MINVALUE] - DECBASE)+ord('A')
            print chr(letter),

    #This if statement block is checking if the second value needs to be a
    #letter if it does this statement will change it
    if hexString[MAXVALUE] < DECBASE:
        print hexString[MAXVALUE],
    else:
        letter = (hexString[MAXVALUE] - DECBASE)+ord("A")
        print chr(letter),
        
def decToBin(getUserNumber):
#Function name: decToBin() converts a decimal number into binary
#input: decimal number (up to 255)
#output: binary number (up to 8 bit)

    #Since the binary numbers will always be 8 bit i have a list of 8 falses
    #which will be replaced by one or left as is to represent 0
    binaryString = [False, False, False, False, False, False, False, False]

    #This while loop is converting the number to binary
    i = MAXBINARYSTRING
    while i >= MINVALUE:
        binaryString[i] = ((getUserNumber % BINBASE) == MAXVALUE)
        getUserNumber = getUserNumber / BINBASE
        i -= MAXVALUE

        #This while loop is printing the number for the user to see
    i = MINVALUE
    while i <= MAXBINARYSTRING:
       if binaryString[i]:
           print '1',
       else:
           print '0',
       i += MAXVALUE

def binToDec(getUserNumber):
#Function name: binToDec() converts an 8 bit binary number to a decimal value
#input: binary number
#output: decimal number

    #setting up variables i use in this function
    outputInteger = MINVALUE
    inputString = getUserNumber

    #This forloop is checking every number in the string of binary given by 
    #the user and adding it all up to make a decimal number again
    for i in range(len(inputString)):
        outputInteger = outputInteger * BINBASE
        if inputString[i] == '1':
            outputInteger += MAXVALUE
       
    print outputInteger

def binCheck(getUserNumber):
#Function name: binCheck() This little function is checking the user inputed
#                          binary number to make sure it only has 0's and 1's
#input: user inputted number from the main
#output: True or false boolean

    #Checks the binary string for all ones and zeroes
    for i in range(MAXBINARYSTRING):

        #If its not all ones and zeroes returns false
        if getUserNumber[i] != "0" and getUserNumber[i] != "1":
            return False

    #if it is then it returns true
    return True

def main():

    #setting a boolean for my while loop
    flag = True

    #Call to the printGreeting function to print the greeting for the user
    printGreeting()
    
    #while loop used to ask for the input from the menu 
    while (flag):

        #Try loop used to catch user mistakes
        try:
            printMenu()
            answer = raw_input("What's your choice? ")
            answer = answer.upper() 
            if answer == 'A' or answer == 'B' or answer == 'C' or\
                    answer == 'D':
                flag = False
            else:
                print "Invalid option, please try again:\n "
        except:
            print "Invalid option, please try again:\n "
    
    #resetting my boolean for use with the next while loop
    flag = True
    
    while (flag):

        #try loop for catching user error on the second ask for information
        try:
            
            #What happens if the user enters A for their menu choice
            #Similar for B and C
            if answer == "A":

                #prompts the user for an input
                userNumber = raw_input("Enter the number (up to 255): ")
                userNumber = int(userNumber)
               #checks to make sure the number is correct
                if userNumber >= MINDECIMALNUMBER and userNumber\
                        <= MAXDECIMALNUMBER and isinstance(userNumber,int) ==\
                        True:

                    #if it is runs the if statement and print the number
                    flag = False
                    print"Your number converted:",
                    decToBin(userNumber)

                #if the number is not correct re asks for a number
                else:
                    print "Invalid number, please try again"
            
            if answer == "B":
                userNumber = raw_input("Enter the number (up to 255): ")
                userNumber = int(userNumber)
                if userNumber >= MINDECIMALNUMBER and userNumber\
                        <= MAXDECIMALNUMBER and isinstance(userNumber,int) ==\
                        True:
                    flag = False
                    print"Your number converted:",
                    decToHex(userNumber)
                else:
                    print "Invalid number, please try again: "

            if answer == "C":
                userNumber = raw_input("Enter the 8 bit binary number: ")
                if len(userNumber) == MAXBINARYBITS and binCheck(userNumber) == True:
                    flag = False
                    print"Your number converted:",
                    binToDec(userNumber)
                else:
                    print "Number must be an 8 bit binary number, try again: "
        except SyntaxError:
            print "Input must be a number, please try again"
        except NameError:
            print "Input must be a number, please try again"
        except ValueError:
            print "Input must be a number, please try again"

        #If the user chooses D exits the program
        if answer == "D":
            sys.exit("Exiting")
main()
