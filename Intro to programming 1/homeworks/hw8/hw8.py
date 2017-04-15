#File: hw8.py
#Author: Joseph Wenzel
#Date: 4/14/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program allows the user to play hangman given the word file they put in

MAX_GUESSES = 100

from random import randrange
import re

def printGreeting():
#printGreeting() prints a greeting for the user
#Input: none
#output: none
    print "-------------------------------------------------------------------"
    print "                       H A N G M A N"
    print "-------------------------------------------------------------------"
    print ""

def readWords(filename):
#readWords() reads in the file of words given by the user
#input: the name of the file
#output: a list of all the words in the file
    #initializing the list of words
    wordlist = []
    valid = False
    #while loop for testing if the file exists
    while(valid == False):
        valid = True
        #try statement to catch mistakes
        try:
            file = open(filename, "r")
        except IOError:
            valid = False
            filename = raw_input("Cannot find file please try again: ")
    #goes through all the lines in the file and strips splits and makes
    #the words lowercase
    for line in file:
        line = line.lower().strip().split()
        #compiles a list of the words gotten from the file
        wordlist = wordlist + line
    #closes the file
    file.close()
    return wordlist

def getRandomWord(wordlist):
#getRandomWord() picks a random word out of the list of words
#input: list of all the words in the file
#output: the secret word used in the game
    #gets a random number to select a word
    number = randrange(len(wordlist))
    #uses that random number as an index number to pick out a word to use
    secretWord = wordlist[number]
    return secretWord

def playGame(wordlist):
#playGame() runs all the functions necessary for playing the game
#input: list of all the words used in the file
#output: none
    gameDone = False
    #initializing variable for use
    numMissedLetters = 0
    correctLetters = []
    allGuessedLetters = []
    #setting the secret word to a variable
    secretWord = getRandomWord(wordlist)

    while(gameDone == False):
        #boolean to check if the guess is correct or not
        goodGuess = False
        #call to the function to make the board the user sees
        displayTurn(numMissedLetters, correctLetters, secretWord)
        #gets what the user wants to guess
        guess = getGuess(allGuessedLetters)
        #a list of all letters guessed by the user
        allGuessedLetters.append(guess)
        for i in range(len(secretWord)):
            #if statement checking to see if the guess is correct
            if guess in secretWord[i]:
                print "Yes, there is a %s" %(guess)
                correctLetters.append(guess)
                goodGuess = True
            elif i == (len(secretWord) - 1) and goodGuess != True:
                print "Sorry, no %s" %(guess)
       
        if goodGuess == False:
            #if the guess was not correct check to see if they have lost
            numMissedLetters += 1
            gameDone = lostGame(numMissedLetters)
        else:
            #check to see if they have won the game with a good guess
            gameDone = wonGame(correctLetters, secretWord)

def displayTurn(numMissedLetters, correctLetters, secretWord):
#displayTurn() prints the number of guesses left and the blank/guessed letters
#inputs: the number of wrong guesses, a list of correct guesses, and the
#        word the user is trying to guess
#outputs: prints out the board and guesses left
    for i in range(len(secretWord)):
        #prints the letters and underscores the player sees
        if secretWord[i] in correctLetters:
            print secretWord[i],
        else:
            print "_",
    print "   incorrect guesses left: %d\n" %(MAX_GUESSES - numMissedLetters)

def getGuess(allGuessedLetters):
#getGuess() Takes in the users guess
#inputs: All the letters the user has guessed so far
#outputs: the users guess
    flag = False
    while(flag == False):
        #asks the user to guess a letter for use in the program
        guess = raw_input("guess a letter: ")   
        #checks to see if the guess is valid and in range
        valid = False
        while(valid == False):

            if not re.match("^[a-z]*$", guess) or len(guess) != 1:
                guess = raw_input("Single letters only. Try again: ")
            else:
                valid = True
        #make the guess lowercase
        guess.lower()
        print ""
        #checks to see if the guess has been guessed already or not
        if guess not in allGuessedLetters:
            flag = True
            return guess
        else:
            print "You already guessed that letter. Choose again."

def wonGame(correctLetters, secretWord):
#wonGame() checks to see if the user has won the game
#inputs: the correct letters the user has guessed and the secret word
#outputs: Boolean of true if they won or false if they lost
   #Checks if the correct letters are in secretWord
    for element in secretWord:
        if element not in correctLetters:
            return False
    print"You won! the word is %s" %(secretWord.upper())
    return True

def lostGame(numMissedLetters):
#lostGame() checks to see if the user has lost the game
#inputs: the number of failed attempts the user has done
#outputs: boolean of true if they have lost or false if they have not
    #checks if the player has used all of their guesses
    if numMissedLetters == MAX_GUESSES:
        print "Sorry, you're out of guesses."
        return True
    else:
        return False

def playAgain():
#playAgain() asks the user if they want to play again
#inputs: none
#outputs: Boolean of true if they want to play again and false if they dont
    flag = False
    while(flag == False):
        #asks the user if they want to play again
        answer = raw_input("Play again (y or n)? ")
        answer.lower()
        #if they do return true if not return false
        if answer == "y":
            flag = True
            return True
        elif answer == "n":
            flag = True
            return False
        else:
            print "Please answer y (yes) or n (no)"
    
def main():
    #boolean for if the person wants to replay
    replay = True
    #if replay is true it plays the game
    printGreeting()
    #gets the name of the file
    filename = raw_input("Enter the name of a file with words. ")
    #creats a list of words from the file
    wordlist = readWords(filename)
    while(replay == True):
        print ""
        #starts the game
        playGame(wordlist)
        #asks if the user wants to play again
        replay = playAgain()
    
main()
