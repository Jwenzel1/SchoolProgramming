# some constants
MIN_YEAR = 1900
MAX_YEAR = 2500
 
SUNDAY = 0
MONDAY = 1
TUESDAY = 2
WEDNESDAY = 3
THURSDAY = 4
FRIDAY = 5
SATURDAY = 6
def printGreeting():
    print "AHHHHHH CALENDERS"

def getValidInt():
    number = -1
    while (number < 0):
        number = input("Enter a year (between 1900 and 2500): ")
        if number < MIN_YEAR or number > MAX_YEAR:
            number = -1
    return number

def printCalendar(year):
    for monthNumber in range(12):
        printMonth(year, monthNumber)
  
def printMonth(year, monthNumber):
    month = monthName(monthNumber),
    print month,
    print year
    print "Su Mo Tu We Th Fr Sa"
    weekday = firstDayOfMonth(year, monthNumber)
#    indentFirstLine(weekdayNumber)
    numDays = 30
    for day in range(1, numDays + 1):
        print "%2d" % (day),
        if weekday == SATURDAY:
            print ""
        weekday = (weekday + 1) % 7
    if weekday != SUNDAY:
        print ""        
def firstDayOfMonth(year, monthNumber):
    return SUNDAY
def monthName(monthNumber):
    if monthNumber == 0:
       return "January"
    elif monthNumber == 1:
       return "February"
    elif monthNumber == 2:
       print "March"
    elif monthNumber == 3:
       print "April"
    elif monthNumber == 4:
       print "May"
    elif monthNumber == 5:
       print "June"
    elif monthNumber == 6:
       print "July"
    elif monthNumber == 7:
       print "August"
    elif monthNumber == 8:
       print "September"
    elif monthNumber == 9:
       print "October"
    elif monthNumber == 10:
       print "November"
    elif monthNumber == 11:
       print "December"

def main():
 
    printGreeting()
 
    year = getValidInt()
    printCalendar(year)
 
main()
