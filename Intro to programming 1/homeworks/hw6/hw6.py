#File: hw6.py
#Author: Joseph Wenzel
#Date: 3/24/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program makes a game for the user to enjoy! It creates a game board
#and asks the user to find the magic key

MAXDIMENSION = 10
LOOPDIMENSION = 9
MINDIMENSION = 0
COUNTER = 1
KEYSTARTX = 4
KEYSTARTY = 7
PLAYERSTARTX = 3
PLAYERSTARTY = 5

def printGreeting():
#printGreeting() prints a friendly greeting for the user
#requires no inputs or outputs

    print "Welcome to the Loops HW. Get to the key!!!"

def printBoard(playerX, playerY, keyX, keyY):
#printBoard() prints out the game board for the user to see
#Input: X and Y coordinates of the player and the key
#Output: A board with the player's and key's positions marked on them
    
    #For loop for the y coordinates
    for i in range (MAXDIMENSION):
        #For loop for the X coordinates
        for j in range (MAXDIMENSION):
            #if i is on the first line it will print the top border. same for 
            #     the bottom border
            if i == MINDIMENSION or i == LOOPDIMENSION:
                print "- ",
                #when the loop gets to the end of a line it starts a new line
                if j == LOOPDIMENSION:
                    print "\n"

            #if the program is on a side wall it will print it using this loop
            if i != MINDIMENSION and i != LOOPDIMENSION:
                if j == MINDIMENSION or j == LOOPDIMENSION:
                    print "| ",
                    if j == LOOPDIMENSION:
                        print "\n"
                #Prints out where the P is for the player
                elif i == playerY and j == playerX:
                    print "P ",
                #Prints out where the K is for the key
                elif i == keyY and j == keyX:
                    print "K ",
                #prints out the dots that represent where the player can move
                else:
                    print ". ",
            
def updatePosition(direction, playerX, playerY):
#updatePosition() takes the direction the player wants to move in and applys it
#Inputs: the direction the player wants to move and the players position
#        before moving
#Outputs: The players new positon on the board

    #Copy of the original position to be used later
    newPlayerX = playerX
    newPlayerY = playerY    

    #Checks to see where the player wants to move then adds or subtracts 1
    if direction == "north":
        newPlayerY -= COUNTER
    elif direction == "south":
        newPlayerY += COUNTER
    elif direction == "east":
        newPlayerX += COUNTER
    elif direction == "west":
        newPlayerX -= COUNTER
   
    #Checks to see if the player has moved off the board. If they have not
    #the program returns the new position
    if offBoard(newPlayerX, newPlayerY) == False:
        return newPlayerX, newPlayerY

    #if they have then the program returns the original position and does not
    #move them
    else:
        return playerX, playerY

def reachedKey(playerX, playerY, keyX, keyY):
#reachedKey() checks if the player is on the key
#Inputs: the X and Y coordinates of both the player and the key
#Outputs: boolean depending on if the player is on the key or not

    #if the player is on the key it returns true
    if playerX == keyX and playerY == keyY:
        return True
    #Otherwise the program returns false
    else:
        return False

def offBoard(pX, pY):
#offBoard() Checks to see if the player has moved off the board or not
#Inputs: the X and Y coordinates of the player
#Outputs: Boolean if the player has moved off or not

    #Checks to see if the player has moved onto any of the boarders of
    #the game board
    if pX == MINDIMENSION or pX == LOOPDIMENSION:
        return True
    elif pY == MINDIMENSION or pY == LOOPDIMENSION:
        return True
    #if they have not moved onto a boarder return false
    else:
        return False

def main():
    #Here is the starting position of the key
    keyX, keyY = KEYSTARTX, KEYSTARTY
    #Here is the starting position of the player
    playerX, playerY = PLAYERSTARTX, PLAYERSTARTY
    
    #Call to print the greeting and the gameboard
    printGreeting()
    printBoard(playerX, playerY, keyX, keyY)

    #Set up a boolean for use in the while loop
    foundKey = False

    #Loop that keeps the game going until the key is found and validates any
    #inputs that come into it. if any invalid inputs are entered the game just
    #reprints its board it had before
    while(foundKey == False):
        
        #asks the user what direction they want to move in
        direction = raw_input("North, South, East, or West? ")
        #makes the direction lowercase for use in the program
        direction = direction.lower()

        #updating the x and y coordinates of the player
        playerX, playerY = updatePosition(direction, playerX, playerY)
        #Checking to see if the player has found the key yet
        foundKey = reachedKey(playerX, playerY, keyX, keyY)
        #Prints the board so the player can see their updated position
        printBoard(playerX, playerY, keyX, keyY)
        
    print "Congratulations you found the magic key!!!"
    
main()
