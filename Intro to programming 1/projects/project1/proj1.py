from Book import *

LAST_NAME = 0
FIRST_NAME = 1
TITLE = 2
QUANTITY = 3
PRICE = 4

def printGreeting():
    print "Welcome to the library program."

def cmpBook(book1, book2):
#def cmpBook() sorts the book objects within their lists in the dictionary
#input: two book objects
#output: the list sorted
    return cmp(book1.getTitle(), book2.getTitle())

def readDatabase(Inventory):
#def readDatabase() Reads in the database text file to populate the dictionary
#input: The dictionary named Inventory
#output: The inventory dictionary with all the books in it
    #variable for the name of the database file
    database = raw_input("Enter the name of your database file: ")
    valid = False
    while(valid == False):
        valid = True
        try:
            #trying to open the database
            openDatabase = open(database, "r")
        except IOError:
            #if it cant then ask the user again
            valid = False
            database = raw_input("Cannot find file please try again: ")

    for line in openDatabase:
        #getting every line in the data base and pulling all the essential
        #information out of it
        line = line.strip().split("$")
        #getting the authors name
        author = line[LAST_NAME]+", "+line[FIRST_NAME]
        #creating the book object
        book = Book(line[TITLE], line[QUANTITY], line[PRICE])
        #putting it into a list to put into the dictionary
        bookList = [book]
        if author not in Inventory.keys():
            #if the author isnt in the dictionary make a new key and
            #and insert the book into that key
            Inventory[author] = bookList
        else:
            #if the author is in the dictionary then put the book under
            #that author
            Inventory[author] = Inventory.get(author)+bookList
    #close the database
    openDatabase.close()
    return Inventory

def printMenu():
#def printMenu() prints out a menu so the user knows what choices to make
#Inputs: none
#outputs: The users choice
    flag = False
    print "-------------------------------------------------------------------"
    print "Enter 1 to display the inventory of books"
    print "Enter 2 to add a book to the inventory"
    print "Enter 3 to change the quantity of a book you have"
    print "Enter 4 to view the total amount of money your inventory is worth"
    print "Enter 5 to exit"
    print "-------------------------------------------------------------------"

    while(flag == False):
        #making sure the user puts in the correct input
        flag = True
        try:
            choice = input("Enter your choice: ")
            #validation for the input checking if its an int and correct
            if isinstance(choice, int) != True or choice >5 or choice < 1:
                flag = False
                print "Invalid option please try again"
        except:
            flag = False
            print "Invalid option please try again"
    return choice

def displayInventory(Inventory):
#def displayInventory() displays all of the books in the Inventory dictionary
#                       sorted by author and all alphabetized
#Inputs: The inventory dictionary
#outputs: a display of all the books in the dictionary
    #creating a list of keys and alphabetizing them
    alphabetize = Inventory.keys()
    alphabetize.sort()
    #displaying all the books in alphabetical order for the user with prices
    #and quantity included and titles of the book
    for element in alphabetize:
        print "The Author is: %s" %element
        Inventory[element].sort(cmpBook)
        for book in Inventory[element]:
            book.displayInfo()

def addBook(Inventory):
#addBook() Allows the user to add a book into the Inventory dictionary
#inputs: The inventory dictionary
#outputs: the inventory dictionary with a new book in it

############################################################################
    #This block of code is getting the authors last and first name and 
    #concatonating them together to form the key used in the dictionary
    #this is also getting a book title from the user and capitalizing all the
    #first letters in all the words
    lastName = raw_input("Enter the author's last name: ")
    lastName = lastName.strip().lower().capitalize()

    firstName = raw_input("Enter the author's first name: ")
    firstName = firstName.strip().lower().capitalize()
    
    fullName = lastName+", "+firstName

    bookTitle = raw_input("Enter the title: ")
    bookTitle = bookTitle.lower()
    bookTitle = ' '.join(word[LAST_NAME].upper()+word[FIRST_NAME:] for \
                                 word in bookTitle.split())
##############################################################################

    flag = True
    #checking if the name is in the dictionary
    if fullName in Inventory.keys(): 
        #looking for books under that name
        for book in Inventory[fullName]:
            #check if that book is already in the database
            if book.getTitle() == bookTitle:
                flag = False
                print "That book already exists in the database."
    #if it isnt in then ask for how many of the book there are 
    if flag == True:
        quantity = 0
        valid = False
        while(valid == False):
            try:
                quantity = input("Enter the quantity: ")
            except:
                valid = False
                print "Invalid input for quantity please try again"
            valid = True
      
        valid = False
        while(valid == False):
            #then ask for the price of the book and validate it
            firstcheck = False
            while (firstcheck == False):
                try:
                    firstcheck = True
                    price = input("Enter the price: $")
                except:
                    firstcheck = False
                    print "Invalid input"
            if isinstance(price, float) == True or isinstance(price, int)\
                    == True:
                valid = True
            else:
                print "Invalid input for price please try again."
        #create the instance of the book with given information
        newBook = [Book(bookTitle, quantity, price)]

        #add it to the dictionary
        if fullName in Inventory.keys():
            Inventory[fullName] = Inventory[fullName]+newBook
        elif fullName not in Inventory.keys():
            Inventory[fullName] = newBook

def changeQty(Inventory):
#changeQty() changes the quantity of a book in the dictionary
#inputs: The inventory dictionary
#outputs: the Inventory dictionary with the quantity changed of a book
    lastName = raw_input("Enter the author's last name: ")
    lastName = lastName.strip().lower().capitalize()

    firstName = raw_input("Enter the author's first name: ")
    firstName = firstName.strip().lower().capitalize()

    #Creating the full name of the person and the dictionary key
    fullName = lastName+", "+firstName

    if fullName in Inventory.keys():
        bookTitle = raw_input("Enter the title: ")
        bookTitle = bookTitle.lower()
        #This is making the book title all capitalized. This allows 
        # punctuation to be used in the middle of words
        bookTitle = ' '.join(word[LAST_NAME].upper()+word[FIRST_NAME:]\
                                 for word in bookTitle.split())

        done = False
        for book in Inventory[fullName]:
            #Checks if the book title is one of the books
            if bookTitle == book.getTitle() and done == False:
                valid = False
                while(valid == False):
                    #if it is then get a new input for how many you have
                    try:
                        valid = True
                        #much validation for this input
                        newAmount = (input("How many do you now have: "))
                        if newAmount < 0:
                            valid = False
                            print "You can't have negative books."
                    except:
                        #if the input is wrong prompt the user
                        valid = False
                        print "Invalid input for the new amount."
                    done = True
                print "The book quantity has been changed from %d to %d"\
                        %(book.getQuantity(),newAmount)
                    #Set the book quantity to the new amount
                book.setQuantity(newAmount)

        if done == False:
            print "That book is not in your library."
    else:
        print "That author is not in your library."
    
def calculateTotal(Inventory):
#calculateTotal() Calculates the total amount of money in the library
#inputs: Inventory dictionary
#outputs: the total worth of all the books combined
    #initializing the total variable
    total = LAST_NAME
    #checks every key
    for element in Inventory:
        #checks every book in the key
        for book in Inventory[element]:
            #gets the price from every book and multiplies it by how many there
            #are and then adds it to the total
            total += book.getPrice()*book.getQuantity()
    print "The total value of the inventory is $%.2f"%total

def main():
    #initializing the inventory dictionary
    Inventory = {}
    #populating the dictionary
    readDatabase(Inventory)
    #setting a flag for the while loop
    flag = True
    #while loop that keeps allowing the user to pick choices from the menu
    while(flag == True):
        choice = printMenu()
        if choice == 1:
            displayInventory(Inventory)
        elif choice == 2:
            addBook(Inventory)
        elif choice == 3:
            changeQty(Inventory)
        elif choice == 4:
            calculateTotal(Inventory)
        elif choice == 5:
            print "Thanks for using this program"
            flag = False
main()
