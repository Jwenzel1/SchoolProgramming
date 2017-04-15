def printGreeting():
    print "I hate you"
def readFile(fileName):
    scores = {}
    readFile = open(fileName, "r")
    for line in readFile:
        student = line.split()
        scores[student[0]] = student[1]
    readFile.close()
    return scores
def findMean(examScores):
    total = 0
    for i in range(len(examScores)):
        examScores[i] = float(examScores[i])
        total += examScores[i]
    mean = total/len(examScores)
    return mean
def main():
    printGreeting()
    fileName = raw_input("Enter a file name: ")
    scores = readFile(fileName)
    examScores = scores.values()
    examScores = sorted(examScores)
    mean = findMean(examScores)
    minimum = examScores[0]
    maximum = examScores[-1]
    print "Mean: %.2f"%(mean)
    print "Minimum: %.2f"%(minimum)
    print "Maximum: %.2f"%(maximum)
    flag = False
    while(flag == False):
        print "Enter the name of a student to see how they compare to the class"
        name = raw_input("average: ")
        if int(scores[name]) > mean:
            print "Above average"
        else:
            print "Below average"
main()
