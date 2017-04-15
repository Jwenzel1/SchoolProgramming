#File: proj2.py
#Name: Joseph Wenzel
#Date: 5/13/13
#Discussion: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This file takes in a fully parenthesized expression and evaluates it using
#stacks. It evaluates it first by converting it to an infix expression then
#evaluates it a second time by converting the expression to postfix. 
from Stack import *

NUMBERS = "1234567890"
OPERATORS = "+-/*"
INFIX_POPS = 4

def printGreeting():
#printGreeting() prints a friendly greeting for the user
#takes no inputs
#gives no outputs
    print "\nEnter a fully parenthesized expression that has"
    print "non-negative integer operands and uses only + - * / and ()\n"

def getInput():
#getinput() gets the expression the user wants from the user and validates it
#takes no inputs
#returns the user given expression

    #initialize all the boolean variables used for my error checking
    valid = False
    while(valid == False):
        check1, check2, check3 = False, False, False
        #ask the user for the expression
        expression = raw_input("Please enter the expression: ")
        #pass the expression through the check to see if they put in words
        check1 = wordCheck(expression)
        #if it passes the word check
        if check1 == True:
            #pass the expression into check2 to check for any misused
            #positives or negatives
            check2 = signCheck(expression)
        #if the sign check is passed
        if check2 == True:
            #check to make sure the expression is fully parenthesized
            check3 = parenthesizedCheck(expression)
        #if the third check is passed
        if check3 == True:
            #the expression is valid so break the while loop
            valid = True
    #return the users expression for processing in the program
    return expression

def wordCheck(expression):
#wordcheck() checks the user entered expression for invalid characters
#takes the expression as an input
#returns a boolean of true or false
    #Scans the whole expression put in by the user
    for char in expression:
        #if it finds a character it does not recognize in this if statement
        if char not in OPERATORS and char not in NUMBERS and char != "("\
                and char != ")" and char != " ":
            #print a message and return a False boolean
            print "Sorry. This program cannot work with this input."
            return False
    #otherwise return a true boolean saying it passed this check
    return True

def signCheck(expression):
#signCheck() checks the expression to see if the user denoted a number was 
#positive or negative using a + or - sign
#takes the expression as input
#returns a boolean
    #initializing lastchar as "(" so that it can check the begining as well.
    #originally had it as " " but it was skipping the begining check
    lastChar = "("
    #scan the expression
    for char in expression:
        #if the character isnt a space
        if char != " ":
            #and if the character is a plus sign
            if char == "+":
                #check if the last character was not a number and not a )
                if lastChar not in NUMBERS and lastChar != ")":
                    print "Please dont use + for positive numbers."
                    #return a false boolean saying it did not pass this check
                    return False
            #otherwise if the character is a minus sign
            elif char == "-":
                #check if the last character was not a number or ) 
                if lastChar not in NUMBERS and lastChar != ")":
                    print "Please avoid using negative numbers."
                    #return a false boolean saying it failed this check
                    return False
            #set the last character as the character that was used
            lastChar = char
    #return true if the expression passes all checks
    return True

def parenthesizedCheck(expression):
#parenthesizedCheck() checks the expression to make sure it is a fully 
#parenthesized expression
#takes the user given expression as an input
#outputs a boolean of true or false
    #initializing the string variable
    string = " "
    #scan the whole expression
    for char in expression:
        #if the scan gets to a ( or )
        if char == "(" or char == ")":
            #concatonate the ( or ) into the string variable
            string = string + char
        #if it finds a number and the previous addition to the string was not
        #a number then concatonate an X into the temporary string and 
        elif char in NUMBERS and string[-1] != "X":
            string = string + "X"
        #if the scan finds an operator it does the same thing as with numbers
        #but it replaces it with an O instead of an X
        elif char in OPERATORS:
            string = string + "O"
    #the length of the original string is stored
    strLength = len(string)
    #replacing every instance of (XOX) that we made with just an X
    string = string.replace("(XOX)", "X")
    #this while loop repeats the previous two lines until it the string has
    #been condensed into hopefully just one " X"
    while(strLength != len(string)):
        strLength = len(string)
        string = string.replace("(XOX)", "X")
    #if we do only have one " X" then we passed the test return true    
    if string == " X":
        return True
    #otherwise failed and return false
    else:
        print "Please write a fully parenthesized expression."
        return False
def evaluateInfix(expression):
#evaluateInfix() evaluates the user given expression using a stack
#takes the user given expression as an input
#has no returns it just prints the answer
    print "\n\n-------------Using a Stack To Evaluate Infix-------------------"
    #initialize the stack and wholeNumber variable for use in this function
    stack = Stack()
    wholeNumber = ""
    #scan each character in the expression
    for char in expression:

        #if the character is a number then concatonate it to the wholeNumber
        #variable. i am quite literally building numbers out of strings in
        #this variable
        if char in NUMBERS:
            wholeNumber = wholeNumber + char

        #if the character is an operator then:
        elif char in OPERATORS:

            #check to see if the wholeNumber varaible is not empty
            if wholeNumber != "":
                #if there is something in it the push the contents onto the 
                #stack for later use
                stack.push(wholeNumber)
                print "Pushing %s into the stack."%wholeNumber
                #reset the wholeNumber variable to use it again
                wholeNumber = ""

            #push the operator we scanned onto the stack
            stack.push(char)
            print "Pushing %s into the stack."%char

        #if the character we scan is a "("
        elif char == "(":
            #just push it onto the stack and thats it
            stack.push(char)
            print "Pushing %s into the stack."%char

        #if the scanned character it a ")"
        elif char ==")":

            #and the whole number variable is not empty
            if wholeNumber != "":
                #push the contents of the variable onto the stack for later use
                stack.push(wholeNumber)
                print "Pushing %s into the stack."%wholeNumber
                #reset the wholeNumber variable so it can be used again
                wholeNumber = ""

            #create a temporary string to concatonate all the values to it
            temp = ""

            #pop the list 4 times
            for i in range(INFIX_POPS):
                #concatonate the top of the stack with the temporary string
                #that was created earlier
                temp = str(stack.top()) + temp
                print " Popping %s from the stack."%stack.top()
                #then pop off the top of the stack. repeat this 3 more times
                stack.pop()

            #evaluate the newly created string expression using eval
            evaluate = eval(temp + char)
            #push the newly evaluated number into the stack
            stack.push(evaluate)
            print "Pushing %s into the stack."%evaluate
    print "The final answer is %s."%stack.top()

def infixToPostfix(expression):
#infixToPostfix() takes the user given expression and turns it into a postfix
#                 expression.
#takes the user given expression as an input
#returns the expression now converted into postfix

    print "\n----------------Infix to Postfix---------------------"
    #initialize the stack and variable to store numbers in
    stack = Stack()
    wholeNumber = ""

    #initialize a variable that i will store the postfix expression in
    output = ""

    #scan the expression
    for char in expression:

        #if it hits a number store it in the wholeNumber variable
        if char in NUMBERS:
            wholeNumber = wholeNumber + char

        #if it hits an operator 
        elif char in OPERATORS:
            #push the character onto the stack
            stack.push(char)
            #add the wholeNumber to the output
            output = output + wholeNumber
            #reset the wholeNumber for reuse
            wholeNumber = ""

        #if the scan hits an ")" operator
        elif char == ")":
            #Then concatonate the current output with the wholeNumber variable
            #and put spaces inbetween the two then concatonate the current 
            #operator that is on the stack to the output expression
            output = output + " " + wholeNumber + " " + stack.top()
            #reset the wholeNumber variable for later use
            wholeNumber = ""
            #pop the current operator from the stack since we used it in the 
            #output
            stack.pop()

    #reutn the newly formed postfix expression
    print output
    return output

def evaluatePostfix(postfix):
#evaluatePostfix() evaluates the postfix expression using a stack
#takes the postfix expression as an input
#returns nothing it just prints the answer

    print "\n-------------Using A Stack to Evaluate Postfix-------------------"

    #here I initialize the stack and the variable i use to store digits in to
    #combine and make a whole number
    stack = Stack()
    wholeNumber = ""

    #This for loop scans every character in the postfix expression 
    for char in postfix:

        #if the character is in the NUMBERS variable
        if char in NUMBERS:
            #concatonate it onto whatever else is in the wholeNumber variable
            #this will make a whole number everytime i hit something that 
            #isnt a number
            wholeNumber = wholeNumber + char

        #if the character is a space and the wholeNumber variable isnt empty
        elif char == " " and wholeNumber != "":
            #that means I have a whole number now so push it onto the stack
            stack.push(wholeNumber)
            print "Pushing %s into the stack."%wholeNumber
            #reset the wholeNumber variable to make a new number
            wholeNumber = ""

        #if the scan runs into an operator
        elif char in OPERATORS:

            #copy the last number put on the stack into a variable
            value2 = stack.top()
            print " Popping operand 2: %s from the stack."%value2
            #then remove it from the stack
            stack.pop()
            #same thing here with the next value on top
            value1 = stack.top()
            print " Popping operand 1: %s from the stack."%value1
            #then remove it from the stack
            stack.pop()

            #then take the values and the operator i ran into and do the math
            #i also have this cast back into a string for later use
            evaluate = str(eval(value1 + char + value2))
            #push the new number onto the stack
            stack.push(evaluate)
            print "Pushing %s into the stack."%evaluate
    print "The final answer is %s."%stack.top()

def main():
    #calls to printGreeting() to print the greeting for the user
    printGreeting()
    #gets and validates the expression from the user with this call to 
    #getInput()
    expression = getInput()
    #uses stacks to evaluate the validated infix expression
    evaluateInfix(expression)
    #converts the infix expression to postfix and stores it in a variable
    postfix = infixToPostfix(expression)
    #the postfix variable is passed into evaluatePostfix() which then uses
    #the same method of stacks to evaluate the postfix expression
    evaluatePostfix(postfix)
main()
