#File: hw4.py
#Author: Joseph Wenzel
#Date: 2/28/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program will determine a persons chinese and western zodiac signs
#after being given that person's birthday birthmonth and birthyear

import sys

#Constants of the months they have all been assigned numbers that are 2 less
#than their calender date to accompany the mod i am using in my if statement
NUMBEROFMONTHS = 12
FEBRUARY = 0
MARCH = 1
APRIL = 2
MAY = 3
JUNE = 4
JULY = 5
AUGUST = 6
SEPTEMBER = 7
OCTOBER = 8
NOVEMBER = 9
DECEMBER = 10
JANUARY = 11

#A list of all the months for use with both zodiacs
MONTHS=["january", "february", "march", "april", "may", "june", "july",\
            "august", "september", "october", "november", "december"]

#These are all the dates the western zodiacs start and end
ARIESSTART = 21
ARIESEND = 20
TAURUSSTART = 21
TAURUSEND = 21
GEMINISTART = 22
GEMINIEND = 21
CANCERSTART = 22
CANCEREND = 22
LEOSTART = 23
LEOEND = 22
VIRGOSTART = 23
VIRGOEND = 23
LIBRASTART = 24
LIBRAEND = 23
SCORPIOSTART = 24
SCORPIOEND = 22
SAGITTARIUSSTART = 23
SAGITTARIUSEND = 21
CAPRICORNSTART = 22
CAPRICORNEND = 20
AQUARIUSSTART = 21
AQUARIUSEND = 19
PISCESSTART = 20
PISCESEND = 20  

def printGreeting():
#Function name: printGreeting
#This function prints the greeting that the user sees when the program begins
#This function has no input parameters
#This function returns two print statements

    print "This program will take your birthday, month, and year and tell you"
    print "your chinese and western zodiac signs!"

def westernZodiac(getBirthDate, getBirthMonth):
#Function name: westernZodiac
#This function determines the western zodiac of the user by comparing the 
    #month and day given by the user to if statements
#This function takes the birth date that the user gives and the birth month
    #that the user gives in the def main as input here
#This funtion returns a string that tells the user what their sign is


#starting with aquarius this block of if/else statements determines the 
#western zodiac by comparing the given month and day with the statements
    if((getBirthMonth == "january") and (getBirthDate >= AQUARIUSSTART)):
        print "Aquarius";
    elif((getBirthMonth == "february") and (getBirthDate <= AQUARIUSEND)):
        print "Aquarius";
    elif((getBirthMonth == "february") and (getBirthDate >= PISCESSTART)):
        print "Pisces";
    elif((getBirthMonth == "march") and (getBirthDate <= PISCESEND)):
        print "Pisces";
    elif((getBirthMonth == "march") and (getBirthdate >= ARIESSTART)):
        print "Aries";
    elif ((getBirthMonth == "april") and (getBirthDate <= ARIESEND)):
        print "Aries";
    elif ((getBirthMonth == "april") and (getBirthDate >= TAURUSSTART)):
        print "Taurus";
    elif ((getBirthMonth == "may") and (getBirthDate <= TAURUSEND)):
        print "Taurus";
    elif ((getBirthMonth == "may") and (getBirthDate >= GEMINISTART)):
        print "Gemini";
    elif ((getBirthMonth == "june") and (getBirthDate <= GEMINIEND)):
        print "Gemini";
    elif ((getBirthMonth == "june") and (getBirthDate >= CANCERSTART)):
        print "Cancer";
    elif ((getBirthMonth == "july") and (getBirthDate <= CANCEREND)):
        print "Cancer";
    elif ((getBirthMonth == "july") and (getBirthDate >= LEOSTART)):
        print "Leo";
    elif ((getBirthMonth == "august") and (getBirthDate <= LEOEND)):
        print "Leo";
    elif ((getBirthMonth == "august") and (getBirthDate >= VIRGOSTART)):
        print "Virgo";
    elif ((getBirthMonth == "september") and (getBirthDate <= VIRGOEND)):
        print "Virgo";
    elif ((getBirthMonth == "september") and (getBirthDate >= LIBRASTART)):
        print "Libra";
    elif ((getBirthMonth == "october") and (getBirthDate <= LIBRAEND)):
        print "Libra";
    elif ((getBirthMonth == "october") and (getBirthDate >= SCORPIOSTART)):
        print "Scorpio";
    elif ((getBirthMonth == "november") and (getBirthDate <= SCORPIOEND)):
        print "Scorpio";
    elif ((getBirthMonth == "november") and (getBirthDate >= SAGITTARIUSSTART)\
              ):
        print "Sagittarius";
    elif ((getBirthMonth == "december") and (getBirthDate <= SAGITTARIUSEND)):
        print "Sagittarius";
    elif ((getBirthMonth == "december") and (getBirthDate >= CAPRICORNSTART)):
        print "Capricorn";
    elif ((getBirthMonth == "january") and (getBirthDate <= CAPRICORNEND)):
        print "Capricorn";
    return;

def chineseZodiac(getBirthMonth,getBirthYear):
#Funtion Name: chineseZodiac
#This functions takes the month and year the user was born in and prints out
    #what chinese sign the user is
#This function's input parameters are the birth month and birth year the user
    #inputs in the def main
#This function returns a string telling the user what their chinese zodiac sign
    #is

    #This for loop checks to see if the inputed month is january
    for i in MONTHS:

        #If it is january the function subtracts one from the mod
        if(( i == "january") and (getBirthMonth == "january")):
           getBirthYear = getBirthYear % NUMBEROFMONTHS - MARCH

           #If it is january the subtraction will bring it to -1 so this 
           #this statement makes the value month 11 when that happens
           if getBirthYear < FEBRUARY:
               getBirthYear = JANUARY

        #If it is not january the function does the normal mod
        else:
            getBirthYear = getBirthYear % NUMBEROFMONTHS
     
    #These if statements print out the chinese zodiac the person is
    if getBirthYear == FEBRUARY:
        print "Monkey";
    elif getBirthYear == MARCH:
        print "Rooster";
    elif getBirthYear == APRIL:
        print "Dog";
    elif getBirthYear == MAY:
        print "Pig";
    elif getBirthYear == JUNE:
        print "Rat";
    elif getBirthYear == JULY:
        print "Ox";
    elif getBirthYear == AUGUST:
        print "Tiger";
    elif getBirthYear == SEPTEMBER:
        print "Rabbit";
    elif getBirthYear == OCTOBER:
        print "Dragon";
    elif getBirthYear == NOVEMBER:
        print "Snake";
    elif getBirthYear == DECEMBER:
        print "Horse";
    elif getBirthYear == JANUARY:
        print "Sheep";
    return;

def main():

    flag = True

    #Initializing my variables for year month and date
    birthYear = -1
    birthMonth = ""
    birthDate = -1

    #Calling the print greeting function
    printGreeting()

    while(flag):

        try:

            #Variables to contain the users input for the month day and year
            birthYear = input("Enter your birth year: ")
            if isinstance(birthYear, int) == False:
                sys.exit("Invalid input, exiting")
            birthMonth = raw_input("Enter your birth month: ")
            birthDate=input("Enter your birth date: ")
            if isinstance(birthDate,int) == False:
                sys.exit("Invalid input, exiting")
            birthMonth.lower()
            
            #if statements testing if the days are right for the months


            if((birthMonth == "april" or birthMonth == "june" or\
                    birthMonth == "september" or birthMonth == "november")\
                   and (birthDate >= 1) and (birthDate <= 30)):
                flag = False
            elif((birthMonth == "february") and (birthDate >= 1) and\
                     (birthDate <= 29)):
                flag = False
            elif((birthMonth == "january" or birthMonth == "march" or\
                      birthMonth == "may" or birthMonth == "july" or \
                      birthMonth == "august" or birthMonth == "october" or \
                      birthMonth == "december") and (birthDate >= 1)and \
                      (birthDate<= 31)):
                flag = False
            else:
                sys.exit("Invalid input, exiting")

        except SyntaxError:
            sys.exit("Invalid input, exiting")
        except NameError:
            sys.exit("Invalid input, exiting")

    print "Your western zodiac sign is",
    westernZodiac(birthDate,birthMonth),
    print "and your chinese zodiac sign is",
    chineseZodiac(birthMonth,birthYear)
main()
