#File: hw1.py
#Author: Joseph Wenzel
#Date: February 13, 2013
#Section: 12
#E-Mail: jwenzel1@umbc.edu
#Description: 
#This program will compute the gas mileage of a car after given
#the initial and final odometer reading, the number of gallons used, and the
#dollar amount of cost per gallon.

def main():

    #All variables are collected through user input
    #initial is the begining odometer reading of the car
    #final is the final odometer reading of the car
    #gallonsUsed is the gallons of gas used in the car
    #gallonsCost is the price per gallon of gas
    print "This program will calculate the MPG, dollar cost per mile,"
    print "and total miles driven in your car."

    initial = input("Enter the initial odometer reading of your car: ")
    final = input("Enter the final odometer reading of your car: ")
    gallonsUsed = input("Enter the number of gallons of gas used: ")
    gallonsCost = input("Enter the cost per gallon of gas: ")
    print ""

    #These equations are calculating the miles driven by the car (driven),
    #the gas mileage the car gets (mileage), and the 
    #dollar cost per mile (dollarCost)
    driven = final-initial
    mileage = driven/gallonsUsed
    dollarCost = gallonsCost/mileage

    print "You drove %d miles."%driven
    print "The gas mileage your car gets is %.1f miles per gallon."%mileage
    print "The cost per mile is $%.2f"%dollarCost

main()
