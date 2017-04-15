# Filename: search.py
# Contains the code to implement binary search

# binarySearch() performs a binary search for an item in a list
# Inputs: myList, the list to search
#         item, the item to search for
# Output: the index of item in the list, or -1 if not found
def binarySearch(myList, item):

    low = 0
    high = len(myList) - 1

    while low <= high:

        mid = (low + high) / 2


        # if found return the index
        if item == myList[mid]:
            return mid

        # if item is in the 2nd half of the list
        elif item > myList[mid]:
            low = mid + 1

        # if item is in the 1st half of the list
        else:
            high = mid - 1

    # item was not in list
    return -1
