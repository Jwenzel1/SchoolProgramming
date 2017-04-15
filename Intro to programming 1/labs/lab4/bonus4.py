def main():
    x,y = input("Please enter your x value: "),input("Please enter your y\
 value: ")
    if x == 0 and y == 0:
        print ("Origin")
    elif x > 0 and y > 0:
        print ("Quadrant 1")
    elif x < 0 and y > 0:
        print ("Quadrant 2")
    elif x < 0 and y < 0:
        print ("Quadrant 3")
    elif x > 0 and y < 0:
        print ("Quadrant 4")
    elif x == 0 and y > 0 or y < 0:
        print ("y-axis")
    elif y == 0 and x > 0 or x < 0:
        print ("x-axis")
main()
