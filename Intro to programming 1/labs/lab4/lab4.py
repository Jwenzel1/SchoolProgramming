import sys

def main():
    total = 0
    number = input("Please enter a positive integer: ")
    if type(number) == str or number <= 0 or type(number) == float: 
        sys.exit("The program asked for a positive integer.")
    for i in range (1,number):
        value = number % i
        if value == 0:
            total += i
    if total == number:
        print ("you have entered a perfect number")
    else:
        print ("you have not entered a perfect number")
main()
