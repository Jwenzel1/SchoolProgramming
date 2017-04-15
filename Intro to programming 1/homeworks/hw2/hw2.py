#File: hw2.py
#Author: Joseph Wenzel
#Date: 2/13/13
#Section: 12
#E-mail: jwenzel1@umbc.edu
#Description:
#This program will allow a user to enter four quarterly sales figures for
#two divisions of a company and then will display the companies sales by
#quarter in a list and it will compute total and average sales and also
#display that in a list
LIST_LENGTH=4
NUMBER_OF_QUARTERS = 2.0
CURRENT_QUARTER = 1
def main():

    #Initializing the empty lists used as variables for the program
    division1 = []
    division2 = []
    total = []
    average = []

    print "This program will calculate various sales data."
    
    #These for loops allow the user to input their data into division 1 and 2
    for i in range(LIST_LENGTH):
        i+=CURRENT_QUARTER
        division1.append(input("Enter Division 1's sales for quarter %d: "%i))

    for i in range(LIST_LENGTH):
        i+=CURRENT_QUARTER
        division2.append(input("Enter Division 2's sales for quarter %d: "%i))

    #These for loops are calulating the total and average of the two lists and putting it into new lists called "total" for the total and "average" for the average of the quarters
    for i in range(LIST_LENGTH):
        total.append(division1[i] + division2[i])

    for i in range(LIST_LENGTH):
        average.append((division1[i] + division2[i])/NUMBER_OF_QUARTERS)

    #This series of print statements are showing the user what data is being displayed in each row they will see after using the program
    print "\nDivision 1 sales by quarter:    ",
      
    #This series of for loops are printing out the lists into the respective rows that they belong in
    for i in range(LIST_LENGTH):
        print "%8.2f"%division1[1],

    print "\nDivision 2 sales by quarter:    ",
 
    for i in range(LIST_LENGTH):
        print "%8.2f"%division2[2],

    print "\nCompany total sales by quarter: ",

    for i in range(LIST_LENGTH):
        print "%8.2f"%total[i],

    print "\nAverage sales by quarter:       ",

    for i in range(LIST_LENGTH):
        print "%8.2f"%average[i],

main()
