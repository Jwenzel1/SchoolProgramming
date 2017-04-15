import random
import time
from search import *

def linearSearch(numlist, randomNumber):
    for i in range(len(numlist)):
        if randomNumber == i:
            return i
    return -1

def main():
    lengthOfList = input("GIMMIE A LENGTH: ")
    numlist = []

  
    for i in range(lengthOfList):
        numlist.append(i)
    random.shuffle(numlist)

    t0 = time.time()  
    for i in range(lengthOfList):
        randomNumber = random.randint(1, lengthOfList)
        numberIndex = linearSearch(numlist, randomNumber)
    t1 = time.time()
    
    t2 = time.time()
    numlist.sort()
    for i in range(lengthOfList):
        randomNumber = random.randint(0, lengthOfList)
        numberIndex = binarySearch(numlist, randomNumber)

    t3 = time.time()
    print "Linear Search Time:",t1-t0
    print "Binary Search Time:",t3-t2
main()
