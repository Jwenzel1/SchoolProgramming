FILE_LEN = 26
LAB_POINTS_POSSIBLE = 30.00
E = 60
D = 70
C = 80
B = 90
STUDENTS = 4.0
def findLabScore(rawLabScore):
    return rawLabScore * 100 / LAB_POINTS_POSSIBLE

def findCourseScore(courseDataList):
    raw = courseDataList[-1]*courseDataList[-2]
    lab = findLabScore(raw)
    weight = 0
    score = 0
    courseScore = 0

    for element in courseDataList:
       position = courseDataList.index(element)
       if position%2 == 0:
           weight = element
       else:
           score = element
       if weight == courseDataList[-2]:
           weight = 0
       if weight != 0 and score != 0:
           courseScore += weight * score
           weight = 0
           score = 0
           
    return courseScore + lab

def findLetterGrade(courseScore):
    if courseScore < E:
        return "E"
    if courseScore < D:
        return "D"
    if courseScore < C:
        return "C"
    if courseScore < B:
        return "B"
    else:
        return "A"

def main():
    total = 0
    totalA = 0
    totalB = 0
    totalC = 0
    totalD = 0
    totalF = 0

    infile = open("grades.txt","r")
    outfile = open("grade_report.txt","w")
    for line in infile:

        courseDataList = line.split()
        name = courseDataList[0]
        courseDataList.remove(name)
        
        for i in range(len(courseDataList)):
            courseDataList[i] = float(courseDataList[i])

        for i in range(FILE_LEN):
    
            courseScore = findCourseScore(courseDataList)

            letterGrade = findLetterGrade(courseScore)
            
        total += courseScore
        outfile.write("%s: %.2f - %s\n" %(name, courseScore, letterGrade))
        if letterGrade == "A":
            totalA += 1
        if letterGrade == "B":
            totalB += 1
        if letterGrade == "C":
            totalC += 1
        if letterGrade == "D":
            totalD += 1
        if letterGrade == "F":
            totalF += 1
    average = total/STUDENTS
    outfile.write("\n\nAverage %.2f\n A - %d\n B - %d\n C - %d\n D - %d\n F - %d"%(average,totalA,totalB,totalC,totalD,totalF))

    infile.close()
    outfile.close()
main()
