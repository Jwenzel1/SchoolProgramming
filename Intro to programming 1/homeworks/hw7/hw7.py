#File: hw7.py
#Author: Joseph Wenzel
#Date: 4/7/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program brings up a graphics window to find a magic key that allows the
#user to click where they want to go to find the key 

from graphics import *

MAX_WIN_SIZE = 600

BOARD_SIZE = 10
BEGINING_BOARD = 0
END_BOARD = 9
BOARD_SPACING = 60

PLAYER_START_X = 150
PLAYER_START_Y = 450
PLAYER_SIZE = 30

KEY_START_X = 510
KEY_START_Y = 90

def printGreeting():
#printGreeting() prints the greeting for the user
#inputs: none
#outputs: prints a statement but other than that no return
    print "Get to the magic key by clicking where you want to go."

def drawBoard(win):
#def drawBoard() draws the board in the graphics window and places the key
#Input: the window it draws in (win)
#outputs: The filled in graphics window but no physical returns

    #specifying the y axis with this for loop
    for i in range (BOARD_SIZE):

        #this for loop prints things in the x direction
        for j in range (BOARD_SIZE):

            #if the first for loop is at the begining or end of its run make a
            #row of red boxes for the top and bottom border
            if i == BEGINING_BOARD or i == END_BOARD:
                
                x = Point(j * BOARD_SPACING, i * BOARD_SPACING + BOARD_SPACING)
                y = Point(j * BOARD_SPACING + BOARD_SPACING, i * BOARD_SPACING)

                square = Rectangle(x, y)
                square.setFill("red")
                square.draw(win)
            #if the loop is not on the top or bottome rows then it makes
            #horizontal red boxes for the left and right sides
            elif (i != BEGINING_BOARD and i != END_BOARD) and \
                 (j == BEGINING_BOARD or j == END_BOARD):
                
                x = Point(j * BOARD_SPACING, i * BOARD_SPACING + BOARD_SPACING)
                y = Point(j * BOARD_SPACING + BOARD_SPACING, i * BOARD_SPACING)

                square = Rectangle(x, y)
                square.setFill("red")
                square.draw(win)

            #If those conditions arent met in the other loops makes white boxes
            #for the inside of the board
            else:
                
                x = Point(j * BOARD_SPACING, i * BOARD_SPACING + BOARD_SPACING)
                y = Point(j * BOARD_SPACING + BOARD_SPACING, i * BOARD_SPACING)

                square = Rectangle(x, y)
                square.setFill("white")
                square.draw(win)

    #After the board is made generate the key onto the board
    key = Text(Point(KEY_START_X, KEY_START_Y), "KEY")
    key.setStyle("bold")
    key.draw(win)
    
def getMove(win, playerX, playerY):
#getMove() gets where the person clicks and then calculates how far to move
#   the player
#inputs: the graphics window, the X and Y position of the player
#outputs: how far to move the player on the board
    
    #variable used to get the mouse
    move = win.getMouse()

    #big if statement analyzing where the mouse is clicked and then returning
    #how the player should be moved
    if move.getX() > playerX and move.getY() > playerY - PLAYER_SIZE and\
       move.getY() < playerY+PLAYER_SIZE:
        moveX, moveY = BOARD_SPACING, BEGINING_BOARD
        return moveX, moveY

    elif move.getX() < playerX and move.getY() > playerY - PLAYER_SIZE and\
         move.getY() < playerY+PLAYER_SIZE:
        moveX, moveY = -BOARD_SPACING, BEGINING_BOARD
        return moveX, moveY

    elif move.getY() < playerY and move.getX() > playerX - PLAYER_SIZE and\
         move.getX() < playerX+PLAYER_SIZE:
        moveX, moveY = BEGINING_BOARD, -BOARD_SPACING
        return moveX, moveY

    elif move.getY() > playerY and move.getX() > playerX - PLAYER_SIZE and\
         move.getX() < playerX+PLAYER_SIZE:
        moveX, moveY = BEGINING_BOARD, BOARD_SPACING
        return moveX, moveY

    elif move.getX() > playerX and move.getY() < playerY - PLAYER_SIZE:
        moveX, moveY = BOARD_SPACING, -BOARD_SPACING
        return moveX, moveY

    elif move.getX() > playerX and move.getY() > playerY + PLAYER_SIZE:
        moveX, moveY = BOARD_SPACING, BOARD_SPACING
        return moveX, moveY

    elif move.getX() < playerX and move.getY() < playerY - PLAYER_SIZE:
        moveX, moveY = -BOARD_SPACING, -BOARD_SPACING
        return moveX, moveY

    elif move.getX() < playerX and move.getY() > playerY + PLAYER_SIZE:
        moveX, moveY = -BOARD_SPACING, BOARD_SPACING
        return moveX, moveY

def offBoard(pX, pY):
#offBoard() checks if the player has moved off the board
#inputs: the players current X and Y coordinates
#outputs: Boolean true or false

    #if the person has moved off the board return false
    if pX < BOARD_SPACING or pX > MAX_WIN_SIZE - BOARD_SPACING or\
       pY > MAX_WIN_SIZE - BOARD_SPACING or pY < BOARD_SPACING:
        return False

    #if theyre still on the board return true
    else:
        return True
def main():

    #boolean for ending the game
    foundKey = False

    #players initial start position
    playerX = PLAYER_START_X
    playerY = PLAYER_START_Y

    printGreeting()

    #window variable and the drawing of the window variable
    win = GraphWin("Magic Key Game", MAX_WIN_SIZE, MAX_WIN_SIZE)
    drawBoard(win)

    #variable for generating the player on the board
    player = Circle(Point(playerX, playerY), PLAYER_SIZE)
    player.setFill("purple")
    player.draw(win)

    
    while(foundKey == False):
        
        #variables for how much the player needs to move in 2 directions
        moveX, moveY = getMove(win, playerX, playerY)
        
        #after getMove is used the move return is added to the current position
        playerX = playerX + moveX
        playerY = playerY + moveY

        #checks to see if the person moves off the board
        if offBoard(playerX, playerY) == False:
            #if they do it returns them to the position they were at
            #before moving
            playerX = playerX - moveX
            playerY = playerY - moveY
            
        #if they are on the board then allow the move to happen    
        else:
            player.move(moveX, moveY)
            
        #if the player moves on top of the key break the while loop    
        if playerY == KEY_START_Y and playerX == KEY_START_X:
            foundKey = True
    
    print "Congratulations! You've found the magic key."

    #close the game
    win.close()
main()
