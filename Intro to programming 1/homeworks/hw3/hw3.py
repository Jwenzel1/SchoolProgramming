#File: hw3.py
#Author: Joseph Wenzel
#Date: 2/21/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program will take any english sentence the user gives it without 
#punctuation and will turn it into Pig Latin.

def main():

#I defined a list of vowels to check each letter of any stiring to know where
#to cut off my word and attach it to the end and add ay. I also defined a list for the split up sentence and a list for making the sentence lowercase. I have a boolean watching for when i hit the first vowel in a word and i have a boolean for capitalization as well 
    vowels = ["A", "a", "E", "e", "I", "i", "O", "o", "U", "u"]
    sentenceList = []
    sentenceLowerList = []
    firstVowel = False
    capitalization = False

#here is the greeting for the user
    print "This program will take any english sentence you type without",
    print "punctuation and turn it into pig latin. "

#This is where i ask the user for their sentence without any punctuation
    sentence = raw_input("Enter an english sentence with no punctuation: ")
    sentenceList = sentence.split()

#This for loop makes the entire sentence the user put in lowercase
    for i in range(len(sentenceList)):
        sentenceLowerList.append(sentenceList[i].lower())

#This large for loop begins checking each word in my lowercase sentence list 
    for element in sentenceLowerList:

#This for loop find each letter in each word of the inputed sentence
        for letter in element:

#This if statement Is checking to see if each letter is a vowel
            if letter in vowels:

#This if statement executes when the previous if statement finds a vowel. It capitalizes the first word and finds where the vowel is and then chops off everything before it and then sticks it onto the end to make pig latin. once it does this it changes the firstvowel boolean to true to make sure it doesnt repeat this step for the next vowel it encounters in the same word. The capitalization boolean changes to true so it doesnt capitalize anything else.
              if firstVowel == False and capitalization == False:
                  letterPosition = element.find(letter)
                  print element[letterPosition:].capitalize()+\
                      element[:letterPosition]+"ay",
                  firstVowel = True
                  capitalization = True
              if firstVowel == False and capitalization == True:
                  letterPosition = element.find(letter)
                  print element[letterPosition:]+element[:letterPosition]+"ay",
                  firstVowel = True

#This statement is used for when there is a word with only a Y for a vowel in it. If the program cant find a vowel that i have assigned it will just print the word out regularly into the sentence
        if firstVowel == False and capitalization == False:
            print element.capitalize(),
            capitalization = True
        if firstVowel == False and capitalization == True:
            print element,
        firstVowel = False
    
main()
