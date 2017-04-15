thing = 6;

def changeThingToNum(num):
    global thing;
    thing = num

def main():
    number = input("please put in a number: ")
    print "Here is \"thing\" before modification %d" %thing
    changeThingToNum(number)
    print "Here is \"thing\" after modification %d" %thing

main()
